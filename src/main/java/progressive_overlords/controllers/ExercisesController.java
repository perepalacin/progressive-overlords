package progressive_overlords.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import progressive_overlords.entities.dao.ExerciseDao;
import progressive_overlords.services.ExercisesService;

import java.util.List;

@Controller
@RequestMapping("${api.prefix}/exercises")
@RequiredArgsConstructor
public class ExercisesController {

    private final ExercisesService exercisesService;

    @GetMapping
    public String getExercises(@RequestParam int page, @RequestParam (required = false) String query, Model model) {
        List<ExerciseDao> exercisesDao = exercisesService.getExercises(page, query);
        model.addAttribute("exercises", exercisesDao);
        return "responses/exercises/exercises-dropdown-list";
    }
}
