package offerGenerator.controller;

import offerGenerator.entity.User;
import offerGenerator.repository.UserRepository;
import offerGenerator.service.AddUserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class UserPanelController {
    private UserRepository userRepository;
    private AddUserInformation addUserInformation;
    @Autowired
    public UserPanelController (UserRepository userRepository, AddUserInformation addUserInformation) {
        this.userRepository=userRepository;
        this.addUserInformation = addUserInformation;
    }

    @GetMapping(value = "/user_panel")
    public ModelAndView userPanel(ModelAndView mav) {
        mav.setViewName("user_panel");
        return mav;
    }
    @GetMapping(value = "/user_panel_set_details")
    public ModelAndView userPanelSetDetailsGet(ModelAndView mav) {
        mav.setViewName("user_panel_set_details");
        return mav;
    }
    @PostMapping(value = "/user_panel_set_details")
    public ModelAndView userPanelSetDetailsPost(ModelAndView mav,
                                                @RequestParam("companyName") String companyName,
                                                @RequestParam("city") String city,
                                                @RequestParam("addressStreet") String addressStreet,
                                                @RequestParam("addressNumberOfBuilding") String addressNumberOfBuilding,
                                                @RequestParam("postalCode") String postalCode,
                                                @RequestParam("nip") String nip) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findByUsername((String)principal);
        User user = userOptional.get();
        addUserInformation.addUserInformation(user,companyName, city, addressStreet, addressNumberOfBuilding,  postalCode, nip);
        mav.setViewName("redirect:/user_panel");
        return mav;
    }
}
