package vadim.volin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vadim.volin.model.User;

@Repository("user")
public interface UserRepository extends JpaRepository<User, Integer> {



}
