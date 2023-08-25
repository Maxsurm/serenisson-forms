package formulairesClient.repositories;

import formulairesClient.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "from User u WHERE u.email = :email")
    User findByEmail(@Param("email")String email);
}
