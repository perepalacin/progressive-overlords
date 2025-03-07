package progressive_overlords.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.services.SetsService;

@Controller
@RequestMapping("${api.prefix}/sets")
@RequiredArgsConstructor
public class SetsController {

    private final SetsService setsService;

    @PostMapping
    public String uploadSet(@Valid @ModelAttribute SetDao set, Model model) {
        System.out.println("Endpoint reached!!");
        SetDao setDao = setsService.createSet(set);
        model.addAttribute("set", setDao);
        return "response/sets/new-set";
    }

}
