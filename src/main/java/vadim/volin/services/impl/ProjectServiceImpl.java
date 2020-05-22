package vadim.volin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vadim.volin.model.Project;
import vadim.volin.repository.ProjectRepository;
import vadim.volin.services.ProjectService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project addProject(Project project) {
        return projectRepository.saveAndFlush(project);
    }

    @Override
    public Project editProject(Project project) {
        return projectRepository.saveAndFlush(project);
    }

    @Override
    public void removeProject(Project project) {
        if (projectRepository.findById(project.getProject_id()).isPresent()) {
            projectRepository.deleteById(project.getProject_id());
        }
    }

    @Override
    public void removeProject(int id) {
        if (projectRepository.findById(id).isPresent()) {
            projectRepository.deleteById(id);
        }
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

}
