package vadim.volin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vadim.volin.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT id, username, password, user_img, usermail, userphone, country, city, company, position, roles, active, user_tasks_json FROM manluck.user AS u WHERE u.username = :username ", nativeQuery = true)
    User findByName(@Param("username") String username);

    @Query(value = "SELECT id, username, password, user_img, usermail, userphone, country, city, company, position, roles, active, user_tasks_json FROM manluck.user AS u WHERE u.usermail = :usermail ", nativeQuery = true)
    User finByMail(@Param("usermail") String usermail);

}
