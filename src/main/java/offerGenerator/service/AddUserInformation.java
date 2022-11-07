package offerGenerator.service;

import offerGenerator.entity.User;
import offerGenerator.entity.UserInformation;
import org.springframework.web.bind.annotation.RequestParam;

public interface AddUserInformation {
    User addUserInformation(User user,
                            String companyName,
                            String city,
                            String addressStreet,
                            String addressNumberOfBuilding,
                            String postalCode,
                            String nip);
}
