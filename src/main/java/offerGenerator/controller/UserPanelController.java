package offerGenerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserPanelController {
    @GetMapping(value = "/user_panel")
    public ModelAndView userPanel(ModelAndView mav) {
        mav.setViewName("user_panel");
        return mav;
    }
}
