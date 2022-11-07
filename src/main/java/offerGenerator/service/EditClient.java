package offerGenerator.service;

import offerGenerator.entity.Client;

public interface EditClient {
    Client editClient(Client client,
                      String clientName,
                      String city,
                      String addressStreet,
                      String addressNumberOfBuilding,
                      String postalCode,
                      String nip);
}
