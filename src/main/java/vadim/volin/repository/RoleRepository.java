package vadim.volin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vadim.volin.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
