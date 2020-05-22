package vadim.volin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vadim.volin.model.ProjectFile;

public interface ProjectFileRepository extends JpaRepository<ProjectFile, Integer> {
}
