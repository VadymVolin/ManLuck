package vadim.volin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vadim.volin.model.User;
import vadim.volin.repository.UserRepository;
import vadim.volin.services.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getByUserName(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public User getByUserMail(String usermail) {
        return userRepository.finByMail(usermail);
    }

    @Override
    public User editUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
