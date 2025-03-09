package progressive_overlords.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.services.SetsService;

@Controller
@RequestMapping("${api.prefix}/sets")
@RequiredArgsConstructor
public class SetsController {

    private final SetsService setsService;

    @PostMapping
    public String uploadSet(@Valid @ModelAttribute SetDao set, Model model) {
        SetDao setDao = setsService.createSet(set);
        model.addAttribute("set", setDao);
        return "responses/sets/new-set";
    }

    @PatchMapping
    public String editSet(@Valid @ModelAttribute SetDao set, Model model) {
        SetDao setDao = setsService.editSet(set);
        model.addAttribute("set", setDao);
        return "responses/sets/new-set";
    }

    @DeleteMapping("/{setId}")
    public String deleteSet(@PathVariable int setId) {
        setsService.deleteSet(setId);
        return "responses/sets/deleted-set";
    }

}
