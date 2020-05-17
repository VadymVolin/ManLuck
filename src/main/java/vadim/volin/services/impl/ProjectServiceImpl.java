package vadim.volin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vadim.volin.model.User;
import vadim.volin.repository.ProjectRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl {

    @Autowired
    private ProjectRepository projectRepository;

//    public List<User> getProjectTeam() {
//        return projectRepository.f
//    }

}
