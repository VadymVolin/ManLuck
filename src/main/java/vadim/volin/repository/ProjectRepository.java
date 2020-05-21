package vadim.volin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vadim.volin.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
