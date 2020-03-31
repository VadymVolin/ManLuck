package vadim.volin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vadim.volin.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
