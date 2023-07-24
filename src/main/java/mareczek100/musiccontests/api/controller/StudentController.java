package mareczek100.musiccontests.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static mareczek100.musiccontests.api.controller.StudentController.STUDENT_MAIN_PAGE;

@Controller
@RequestMapping(STUDENT_MAIN_PAGE)
@AllArgsConstructor
public class StudentController {

    public static final String STUDENT_MAIN_PAGE = "/student";
    public static final String HEADMASTER_CREATE = "/add";
    public static final String HEADMASTER_ADD_SCHOOL = "/add/school";
    public static final String HEADMASTER_ADD_COMPETITION = "/add/competition";

    @GetMapping
    public String studentHomePage(Model model){


        return "student";
    }


}
