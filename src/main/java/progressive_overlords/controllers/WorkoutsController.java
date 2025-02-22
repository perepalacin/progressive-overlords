package progressive_overlords.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.services.WorkoutsService;

import java.util.UUID;


@Controller
@RequestMapping("${api.prefix}/workouts")
@RequiredArgsConstructor
public class WorkoutsController {

    @Value("${master.userId}")
    private String masterUserId;
    private final WorkoutsService workoutsService;

    @PostMapping("/start")
    public String startNewWorkout (@RequestParam (required = false) Integer templateId) {
        WorkoutDao workoutDao = workoutsService.findLastByTemplateId(templateId, UUID.fromString(masterUserId));
    }
//
//    @GetMapping
//    public String getLastUserWorkouts (@RequestParam int limit, @RequestParam int offset) {
////        WorkoutDao workoutDao = workoutsService.
//    }

}
