package offerGenerator.service.implementation;

import offerGenerator.entity.Client;
import offerGenerator.entity.User;
import offerGenerator.service.AddClient;
import org.springframework.stereotype.Service;

@Service
public class AddClientImpl implements AddClient {
    @Override
    public User addClient(User user, String clientName, String city, String addressStreet, String addressNumberOfBuilding, String postalCode, String nip) {
        Client client = Client.of(clientName,city,addressStreet,addressNumberOfBuilding,postalCode,nip);
        client.setUser(user);
        user.addClient(client);
        return user;
    }
}
