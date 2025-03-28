package progressive_overlords.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutinesController {

    @GetMapping("/create-routine")
    public String getCreateRoutineView (Model model) {
        return "pages/routines/create-edit-routine";
    }


}
