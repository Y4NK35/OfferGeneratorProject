package offerGenerator.controller;

import offerGenerator.entity.User;
import offerGenerator.repository.UserRepository;
import offerGenerator.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class SignUpController {
    private SignUpService signUpService;
    private UserRepository userRepository;
    @Autowired
    public SignUpController(SignUpService signUpService, UserRepository userRepository){
        this.signUpService = signUpService;
        this.userRepository = userRepository;
    }
    @GetMapping(value = "/sign_up")
    public ModelAndView signUp(ModelAndView mav){
        mav.setViewName("sign_up");
        return mav;
    }
    @PostMapping(value = "/sign_up")
    public ModelAndView signUpPost(ModelAndView mav, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email) throws Exception {
        User user = User.of(username, password,email);
        signUpService.signUpUser(user);
        mav.setViewName("redirect:/login");
        return mav;
    }
    @RequestMapping(value = "/confirm_account")
    public ModelAndView confirmAccount (@RequestParam("token")  String token, ModelAndView mav){
        Optional<User> optionalUser = userRepository.findByConfirmationToken(token);
        System.out.println(token);
        if(optionalUser.isPresent()){
            System.out.println(token);
            User user = optionalUser.get();
            user.setEnabled(true);
            userRepository.save(user);
            mav.setViewName("redirect:/login");
            return mav;
        }else
            System.out.println("to");
            mav.setViewName("account_not_confirmed");
            return mav;
    }
}
