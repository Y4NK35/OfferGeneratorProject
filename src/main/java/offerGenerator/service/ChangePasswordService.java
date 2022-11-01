package offerGenerator.service;

import offerGenerator.entity.User;

public interface ChangePasswordService {
    User changePassword(User user, String newPassword, String password) throws Exception;
}
