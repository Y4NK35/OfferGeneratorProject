package offerGenerator.controller;

import offerGenerator.entity.User;
import offerGenerator.repository.UserRepository;
import offerGenerator.service.AddUserInformation;
import offerGenerator.service.ChangePasswordService;
import offerGenerator.service.DeleteAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class UserPanelController {
    private UserRepository userRepository;
    private AddUserInformation addUserInformation;
    private ChangePasswordService changePasswordService;
    private DeleteAccountService deleteAccountService;
    @Autowired
    public UserPanelController (UserRepository userRepository, AddUserInformation addUserInformation, ChangePasswordService changePasswordService, DeleteAccountService deleteAccountService) {
        this.userRepository=userRepository;
        this.addUserInformation = addUserInformation;
        this.changePasswordService = changePasswordService;
        this.deleteAccountService = deleteAccountService;
    }

    @GetMapping(value = "/user_panel")
    public ModelAndView userPanel(ModelAndView mav) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findByUsername((String)principal);
        User user = userOptional.get();
        mav.addObject("userInformation",user.getUserInformation());
        mav.setViewName("user_panel");
        return mav;
    }

    @GetMapping(value = "/user_panel_set_details")
    public ModelAndView userPanelSetDetailsGet(ModelAndView mav) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findByUsername((String)principal);
        User user = userOptional.get();
        mav.addObject("userInformation",user.getUserInformation());
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

    @GetMapping(value = "/user_panel_change_password")
    public ModelAndView userPanelChangePassword(ModelAndView mav) {
        mav.setViewName("user_panel_change_password");
        return mav;
    }

    @PostMapping(value = "/user_panel_change_password")
    public ModelAndView userPanelChangePasswordPost(ModelAndView mav,
                                                    @RequestParam("newpassword")String newPassword,
                                                    @RequestParam("newpasswordconfirm") String newPasswordConfirm,
                                                    @RequestParam("password")String password
                                                    ) throws Exception {
        if(!newPassword.equals(newPasswordConfirm)){
            throw new Exception("New password are not equals!");
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findByUsername((String)principal);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            changePasswordService.changePassword(user,newPassword, password);
        }
        mav.setViewName("user_panel_change_password");
        return mav;
    }

    @GetMapping(value ="/user_panel_delete_account")
    public ModelAndView userPanelDeleteAccount(ModelAndView mav) {
        mav.setViewName("user_panel_delete_account");
        return mav;
    }

    @PostMapping(value = "/user_panel_delete_account")
    public ModelAndView userPanelDeleteAccountPost(ModelAndView mav,
                                                    @RequestParam("password")String password,
                                                    @RequestParam("passwordconfirm") String passwordConfirm,
                                                    HttpServletRequest request) throws Exception {
        if(!password.equals(passwordConfirm)){
            throw new Exception("Password are not equals!");
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findByUsername((String)principal);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            deleteAccountService.deleteAccount(user,password);
            request.logout();
        }

        mav.setViewName("redirect:/login");
        return mav;
    }
}
