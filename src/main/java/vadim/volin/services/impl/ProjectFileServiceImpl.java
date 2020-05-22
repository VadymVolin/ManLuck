package vadim.volin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vadim.volin.model.ProjectFile;
import vadim.volin.repository.ProjectFileRepository;
import vadim.volin.services.ProjectFileService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProjectFileServiceImpl implements ProjectFileService {

    @Autowired
    private ProjectFileRepository projectFileRepository;

    @Override
    public ProjectFile addProjectFile(ProjectFile projectFile) {
        return projectFileRepository.saveAndFlush(projectFile);
    }

    @Override
    public ProjectFile editProjectFile(ProjectFile projectFile) {
        return projectFileRepository.saveAndFlush(projectFile);
    }

    @Override
    public void removeProjectFile(ProjectFile projectFile) {
        if (projectFileRepository.findById(projectFile.getProject_id()).isPresent()) {
            projectFileRepository.deleteById(projectFile.getProject_id());
        }
    }

    @Override
    public void removeProjectFile(int id) {
        if (projectFileRepository.findById(id).isPresent()) {
            projectFileRepository.deleteById(id);
        }
    }

    @Override
    public List<ProjectFile> getAll() {
        return projectFileRepository.findAll();
    }
}
