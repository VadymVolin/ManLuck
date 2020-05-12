import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import vadim.volin.configuration.CustomLoginSuccessHandler;
import vadim.volin.configuration.SecurityConfiguration;
import vadim.volin.model.Project;
import vadim.volin.model.Role;
import vadim.volin.model.User;
import vadim.volin.persistence.PersistenceJpaConfig;
import vadim.volin.services.UserService;
import vadim.volin.services.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)   // 1
@SpringJUnitConfig({UserServiceImpl.class,PersistenceJpaConfig.class, Project.class, User.class, Role.class, SecurityConfiguration.class, CustomLoginSuccessHandler.class})
@ContextConfiguration({"/applicationContext.xml", "/web.xml", "/dispatcher-servlet.xml"})
@ActiveProfiles("dev")
//@WebAppConfiguration   // 3
public class ResTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private UserService userService;

    @Test
    public void testManyToMany() {
        Project project = new Project("ManLuck-Project");
        List<Project> projects = new ArrayList<>();
        projects.add(project);
        System.out.println(userService);
        User user = userService.getByUserMail("vadim@gmail.com");
//        user.setProjects(projects);
        userService.editUser(user);
    }

}
