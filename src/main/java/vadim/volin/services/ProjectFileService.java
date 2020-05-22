package vadim.volin.services;

import vadim.volin.model.ProjectFile;

import java.util.List;

public interface ProjectFileService {

    ProjectFile addProjectFile(ProjectFile projectFile);

    ProjectFile editProjectFile(ProjectFile projectFile);

    void removeProjectFile(ProjectFile projectFile);

    void removeProjectFile(int id);

    List<ProjectFile> getAll();

}
