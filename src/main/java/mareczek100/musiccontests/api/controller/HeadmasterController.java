package mareczek100.musiccontests.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static mareczek100.musiccontests.api.controller.HeadmasterController.HEADMASTER_MAIN_PAGE;

@Controller
@RequestMapping(HEADMASTER_MAIN_PAGE)
@AllArgsConstructor
public class HeadmasterController {

    public static final String HEADMASTER_MAIN_PAGE = "/headmaster";
    public static final String HEADMASTER_CREATE = "/add";
    public static final String HEADMASTER_ADD_SCHOOL = "/add/school";
    public static final String HEADMASTER_ADD_COMPETITION = "/add/competition";

    @GetMapping
    public String headmasterHomePage(Model model){


        return "headmaster";
    }


}
