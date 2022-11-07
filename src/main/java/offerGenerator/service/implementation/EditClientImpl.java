package offerGenerator.service.implementation;

import offerGenerator.entity.Client;
import offerGenerator.service.EditClient;
import org.springframework.stereotype.Service;

@Service
public class EditClientImpl implements EditClient {
    @Override
    public Client editClient(Client client, String clientName, String city, String addressStreet, String addressNumberOfBuilding, String postalCode, String nip) {
        client.setClientName(clientName);
        client.setCity(city);
        client.setAddressStreet(addressStreet);
        client.setAddressNumberOfBuilding(addressNumberOfBuilding);
        client.setPostalCode(postalCode);
        client.setNip(nip);
        return client;
    }
}
