package progressive_overlords.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import progressive_overlords.services.FriendsService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class FriendsController {

    private final FriendsService friendsService;

    @PostMapping("/api/v1/friends/{userId}")
    public String followUser (@PathVariable UUID userId, Model model) {
        boolean success = friendsService.followUser(userId);
        model.addAttribute("userId", userId);
        if (success) {
            return "components/friends/unfollow-button";
        }
        //TODO: Toast
        return "components/friends/follow-button";
    }

    @PatchMapping("/api/v1/friends/{userId}")
    public String unfollowUser (@PathVariable UUID userId, Model model) {
        boolean success = friendsService.unFollowUser(userId);
        model.addAttribute("userId", userId);
        if (success) {
            return "components/friends/follow-button";
        }
        //TODO: Toast
        return "components/friends/unfollow-button";
    }
}
