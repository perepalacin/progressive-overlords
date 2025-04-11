package progressive_overlords.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import progressive_overlords.entities.dao.ExerciseDao;
import progressive_overlords.services.ExercisesService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("${api.prefix}/exercises")
@RequiredArgsConstructor
public class ExercisesController {

    private final ExercisesService exercisesService;

    @GetMapping("/all")
    public ResponseEntity<List<ExerciseDao>> getAllExercises(@RequestParam String secretKey) {
        if ("grantedAccess".equals(secretKey)) {
            List<ExerciseDao> exercisesDao = exercisesService.getAll();
            return ResponseEntity.status(OK).body(exercisesDao);
        }
        return null;
    }

    @GetMapping
    public String getExercises(HttpServletResponse httpServletResponse, @RequestParam int page, @RequestParam(required = false) String query, Model model) {
        List<ExerciseDao> exercisesDao = exercisesService.getExercises(page, query);
        model.addAttribute("exercises", exercisesDao);
        model.addAttribute("page", page);
        model.addAttribute("query", query);
        return "responses/exercises/exercises-dropdown";
    }

    @GetMapping("/preview/{exerciseId}")
    public String getExercisePreviewById(@PathVariable int exerciseId, Model model) {
        ExerciseDao exercise = exercisesService.getById(exerciseId);
        model.addAttribute("exercise", exercise);
        model.addAttribute("workoutExercise", null);
        model.addAttribute("workoutId", 0);
        return "responses/exercises/exercise-header";
    }

    @GetMapping("/selector")
    public String getExerciseSelector() {
        return "responses/exercises/exercises-selector";
    }
}

