import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import vadim.volin.model.User;
import vadim.volin.persistence.PersistenceJpaConfig;
import vadim.volin.services.UserService;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@EnableJpaRepositories(basePackages = "vadim.volin.repository", entityManagerFactoryRef = "entityManagerFactoryBean")
@ContextConfiguration(classes = PersistenceJpaConfig.class)
@WebAppConfiguration
public class TestServices {

    @Resource
    private EntityManagerFactory entityManagerFactoryBean;
    protected EntityManager entityManager;

    @Resource
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        entityManager = entityManagerFactoryBean.createEntityManager();
    }

    @Test
    public void testSaveBank() throws Exception {
        User user = new User();
        user.setUsername("dfsfs");
        user.setUsermail("dfsfs@sdfsdfsf");
        user.setPassword("sdfdsf23r3re");
        userService.addUser(user);
        System.out.println(userService.getByUserName("dfsfs"));

    }

    @Test
    public void testUpdateBank() throws Exception {
        User user = userService.getByUserName("dfsfs");
        user.setUsername("NENENENENE");
        user.setUsermail("NEW@NEW");
        user.setPassword("NEWPASS");
        userService.editUser(user);
        System.out.println(userService.getByUserName("NENENENENE"));

    }
}
