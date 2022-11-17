package offerGenerator.controller;

import offerGenerator.entity.Client;
import offerGenerator.entity.Offer;
import offerGenerator.entity.User;
import offerGenerator.entity.UserInformation;
import offerGenerator.repository.OfferRepository;
import offerGenerator.repository.UserInformationRepository;
import offerGenerator.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class OfferController {
    OfferRepository offerRepository;
    UserRepository userRepository;
    UserInformationRepository userInformationRepository;

    public  OfferController (OfferRepository offerRepository, UserRepository userRepository, UserInformationRepository userInformationRepository){
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.userInformationRepository = userInformationRepository;
    }
    @GetMapping(value = "/offers")
    public ModelAndView offers(ModelAndView mav) {
        mav.setViewName("offers");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findByUsername((String)principal);
        User user = userOptional.get();
        List<Offer> offers = user.getUserInformation().getOffers();
        mav.addObject("offers",offers);
        return mav;
    }

    @GetMapping(value = "/offer_add")
    public ModelAndView addOffer(ModelAndView mav){
        mav.setViewName("offer_add");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findByUsername((String)principal);
        User user = userOptional.get();
        List <Client> clients = user.getClients();
        UserInformation userInformation = user.getUserInformation();
        mav.addObject("clients",clients);
        mav.addObject("userInformation",userInformation);
        return mav;
    }
}
