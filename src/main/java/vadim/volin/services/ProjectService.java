package vadim.volin.services;

import vadim.volin.model.Project;

import java.util.List;

public interface ProjectService {

    Project addProject(Project project);

    Project editProject(Project project);

    void removeProject(Project project);

    void removeProject(int id);

    List<Project> getAll();

}
