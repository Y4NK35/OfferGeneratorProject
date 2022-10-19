package offerGenerator.controller;

import offerGenerator.entity.User;
import offerGenerator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UserPanelController {
    private UserRepository userRepository;
    @Autowired
    public UserPanelController (UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @GetMapping(value = "/user_panel")
    public ModelAndView userPanel(ModelAndView mav) {
        mav.setViewName("user_panel");
        return mav;
    }
    @GetMapping(value = "/user_panel/set_details")
    public ModelAndView userPanelSetDetailsGet(ModelAndView mav) {
        mav.setViewName("user_panel_set_details");
        return mav;
    }
    @PostMapping(value = "/user_panel/set_details")
    public ModelAndView userPanelSetDetailsPost(ModelAndView mav,
                                                @RequestParam("companyName") String companyName,
                                                @RequestParam("addressStreet") String addressStreet,
                                                @RequestParam("addressNumberOfBuilding") String addressNumberOfBuilding,
                                                @RequestParam("postalCode") String postalCode,
                                                @RequestParam("city") String city,
                                                @RequestParam("nip") String nip) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user ;

        return mav;
    }
}
