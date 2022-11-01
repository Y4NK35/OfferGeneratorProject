package offerGenerator.service;

import offerGenerator.entity.User;

public interface DeleteAccountService {
    String deleteAccount (User user, String password) throws Exception;
}
