package vadim.volin.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vadim.volin.model.User;
import vadim.volin.services.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//@EnableTransactionManagement
//@Service
public class UserNewServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User addUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getByUserName(String username) {
        return entityManager.find(User.class, username);
    }

    @Override
    public User getByUserMail(String usermail) {
        return entityManager.find(User.class, usermail);
    }

    @Override
    public User editUser(User user) {
        return entityManager.merge(user);
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }
}
