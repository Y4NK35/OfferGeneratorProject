package offerGenerator.service.implementation;

import offerGenerator.entity.User;
import offerGenerator.entity.UserInformation;
import offerGenerator.repository.UserInformationRepository;
import offerGenerator.repository.UserRepository;
import offerGenerator.service.AddUserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
@Service
public class UserInformationAddImplementation implements AddUserInformation {

    UserRepository userRepository;
    UserInformationRepository userInformationRepository;
    @Autowired
    public UserInformationAddImplementation  (UserRepository userRepository, UserInformationRepository userInformationRepository){
        this.userRepository=userRepository;
        this.userInformationRepository =userInformationRepository;
    }
    @Override
    public User addUserInformation(User user,
                                   String companyName,
                                   String city,
                                   String addressStreet,
                                   String addressNumberOfBuilding,
                                   String postalCode,
                                   String nip) {
    UserInformation userInformation = user.getUserInformation();
    userInformation.setCompanyName(companyName);
    userInformation.setCity(city);
    userInformation.setAddressStreet(addressStreet);
    userInformation.setAddressNumberOfBuilding(addressNumberOfBuilding);
    userInformation.setPostalCode(postalCode);
    userInformation.setNip(nip);
    user.setUserInformation(userInformation);
    userRepository.save(user);
    return user;
    }
}
