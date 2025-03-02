package progressive_overlords.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import progressive_overlords.services.SessionsService;

import java.io.IOException;
import java.util.*;

public class SessionCookieFilter extends OncePerRequestFilter {

    private static final String SESSION_COOKIE_NAME = "SESSION_ID";
    private static final List<String> PUBLIC_ROUTES = List.of("/sign-in", "/sign-up", "/api/v1/auth/");

    private boolean isPublicRoute(String requestPath) {
        return PUBLIC_ROUTES.stream().anyMatch(requestPath::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestPath = request.getServletPath();
        if (isPublicRoute(requestPath)) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<Cookie> sessionCookie = getSessionCookie(request);
        if (sessionCookie.isPresent() && SessionsService.isTokenValid(sessionCookie.get().getValue())) {
            UUID userId = SessionsService.getUserId(sessionCookie.get().getValue());
            if ( SecurityContextHolder.getContext().getAuthentication() == null) {
                //TODO: Replace with roles from the token
                List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));
                UsernamePasswordAuthenticationToken principal= new UsernamePasswordAuthenticationToken(
                        userId,
                        null,
                        authorities
                );
                principal.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(principal);
            }
        } else {
            response.sendRedirect("/sign-in");
        }

        filterChain.doFilter(request,response);
    }

    private Optional<Cookie> getSessionCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies())
                    .filter(cookie -> SESSION_COOKIE_NAME.equals(cookie.getName()))
                    .findFirst();
        }
        return Optional.empty();
    }
}
