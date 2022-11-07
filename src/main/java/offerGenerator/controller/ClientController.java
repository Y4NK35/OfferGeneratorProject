package offerGenerator.controller;

import offerGenerator.entity.Client;
import offerGenerator.entity.User;
import offerGenerator.repository.ClientRepository;
import offerGenerator.repository.UserRepository;
import offerGenerator.service.AddClient;
import offerGenerator.service.EditClient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    UserRepository userRepository;
    ClientRepository clientRepository;
    AddClient addClient;
    EditClient editClient;

    public ClientController (UserRepository userRepository,ClientRepository clientRepository,AddClient addClient, EditClient editClient){
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.addClient = addClient;
        this.editClient = editClient;
    }
    @GetMapping(value = "/clients")
    public ModelAndView clients(ModelAndView mav) {
        mav.setViewName("clients");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findByUsername((String)principal);
        User user = userOptional.get();
        List<Client> clients = user.getClients();
        mav.addObject("clients",clients);
        return mav;
    }

    @GetMapping(value = "/client_add")
    public ModelAndView clientAdd(ModelAndView mav) {
        mav.setViewName("client_add");
        return mav;
    }

    @PostMapping(value = "/client_add")
    public ModelAndView clientAddPost(ModelAndView mav,
                                                @RequestParam("clientName") String clientName,
                                                @RequestParam("city") String city,
                                                @RequestParam("addressStreet") String addressStreet,
                                                @RequestParam("addressNumberOfBuilding") String addressNumberOfBuilding,
                                                @RequestParam("postalCode") String postalCode,
                                                @RequestParam("nip") String nip) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findByUsername((String)principal);
        User user = userOptional.get();
        addClient.addClient(user, clientName, city, addressStreet, addressNumberOfBuilding, postalCode, nip);
        userRepository.save(user);
        mav.setViewName("redirect:/clients");
        return mav;
    }
    @GetMapping(value = "/client_edit_{idClient}")
    public ModelAndView editClient (ModelAndView mav,
                                    @PathVariable("idClient")Long id){
        Optional<Client> clientOptional = clientRepository.findById(id);
        Client client = clientOptional.get();
        mav.addObject("client",client);
        mav.setViewName("client_edit");
        return mav;
    }
    @PostMapping(value = "/client_edit_{idClient}")
    public ModelAndView clientEditPost(ModelAndView mav,
                                      @PathVariable("idClient")Long id,
                                      @RequestParam("clientName") String clientName,
                                      @RequestParam("city") String city,
                                      @RequestParam("addressStreet") String addressStreet,
                                      @RequestParam("addressNumberOfBuilding") String addressNumberOfBuilding,
                                      @RequestParam("postalCode") String postalCode,
                                      @RequestParam("nip") String nip) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        Client client = clientOptional.get();
        editClient.editClient(client, clientName, city, addressStreet, addressNumberOfBuilding, postalCode, nip);
        clientRepository.save(client);
        mav.setViewName("redirect:/clients");
        return mav;
    }


    @GetMapping(value = "/client_delete/{idClient}")
    public ModelAndView clientDelete(ModelAndView mav,
                                     @PathVariable("idClient") Long id){
        clientRepository.deleteById(id);
        mav.setViewName("redirect:/clients");
        return mav;
    }
}
