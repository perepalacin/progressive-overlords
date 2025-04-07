package progressive_overlords.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.entities.dto.SetDto;
import progressive_overlords.exceptions.BadRequestException;
import progressive_overlords.services.SetsService;

@Controller
@RequiredArgsConstructor
public class SetsController {

    private final SetsService setsService;

    @PostMapping("/api/v1/sets")
    public String uploadSet(@Valid @ModelAttribute SetDao newSet, Model model) {
        SetDao createdSet = setsService.uploadWorkoutSet(newSet);
        model.addAttribute("set", createdSet);
        return "responses/sets/created-set";
    }

    @PatchMapping("/api/v1/sets")
    public String editSet(@Valid @ModelAttribute SetDao newSet, Model model) {
        SetDao createdSet = setsService.editSet(newSet);
        model.addAttribute("set", createdSet);
        return "responses/sets/created-set";
    }

    @DeleteMapping("/api/v1/sets/{setId}")
    public String deleteSet(@PathVariable int setId, Model model) {
        SetDao deletedSet = setsService.getById(setId);
        if (deletedSet == null) {
            throw new BadRequestException("Set with id " + setId + " doesn't exist");
        }
        setsService.deleteSet(setId);
        model.addAttribute("set", deletedSet);
    }

}
