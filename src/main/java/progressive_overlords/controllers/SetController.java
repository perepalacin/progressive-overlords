package progressive_overlords.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.services.SetsService;

@Controller
@RequestMapping("${api.prefix}/sets")
@RequiredArgsConstructor
public class SetController {

    private final SetsService setsService;

    @PostMapping
    public String uploadSet(@Valid @ModelAttribute SetDao set) {
        SetDao setDao = setsService.createSet(set);
        return "response/new-set";
    }

}
