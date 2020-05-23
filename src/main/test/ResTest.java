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
import vadim.volin.util.ProjectUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResTest {


    @Test
    public void testProjectJsonUtil() throws IOException {
        String json = "{\"columns\":{\"idCounter\":2,\"title\":\"\",\"items\":[{\"id\":1,\"title\":\"manluck-c1\\n        \",\"notesId\":[1,2,6,4,5,7,9]},{\"id\":2,\"title\":\"manluck-c2\\n        \",\"notesId\":[3,8,10]}]},\"notes\":{\"idCounter\":10,\"items\":[{\"id\":1,\"content\":\"m1\"},{\"id\":2,\"content\":\"m2\"},{\"id\":6,\"content\":\"m6\"},{\"id\":4,\"content\":\"m4\"},{\"id\":5,\"content\":\"m5\"},{\"id\":7,\"content\":\"m7\"},{\"id\":9,\"content\":\"m10\"},{\"id\":3,\"content\":\"m3\"},{\"id\":8,\"content\":\"m8\"},{\"id\":10,\"content\":\"m9\"}]}}";
        System.out.println(ProjectUtils.getTasksData(json));
    }

}
