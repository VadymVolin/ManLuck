package vadim.volin.services;

import vadim.volin.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    void delete(int id);


    User getByUserName(String username);

    User getByUserMail(String usermail);

    User editUser(User user);

    List<User> getAllUser();

}
