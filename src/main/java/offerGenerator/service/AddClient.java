package offerGenerator.service;

import offerGenerator.entity.User;

public interface AddClient {
        User addClient(User user,
                       String clientName,
                       String city,
                       String addressStreet,
                       String addressNumberOfBuilding,
                       String postalCode,
                       String nip);
    }

