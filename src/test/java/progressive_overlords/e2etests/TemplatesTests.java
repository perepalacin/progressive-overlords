package progressive_overlords.e2etests;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import progressive_overlords.entities.requests.AuthRequest;
import progressive_overlords.entities.responses.GenericResponse;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpStatus.CREATED;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TemplatesTests {

    @LocalServerPort
    private int port = 8081;

    @Autowired
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void testCreateUser() {
        String url = "http://localhost:" + port +"/api/v1/auth/sign-up?username=perepalacin&password=Test1234";
        ResponseEntity<GenericResponse> response = restTemplate.postForEntity(url, null, GenericResponse.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Successful register", response.getBody().getMessage());
    }

    @Test
    void testSignIn() {
        String url = "http://localhost:" + port +"/api/v1/auth/sign-in?username=perepalacin&password=Test1234";
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, null, Void.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<String> cookies = response.getHeaders().get("Set-Cookie");
        Assertions.assertNotNull(cookies);
        Assertions.assertTrue(cookies.stream().anyMatch(cookie -> cookie.startsWith("SESSION_ID=")));

        String location = response.getHeaders().getLocation().toString();
        Assertions.assertEquals("/", location);
    }
}
