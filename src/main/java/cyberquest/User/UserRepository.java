package cyberquest.User;

import cyberquest.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //Returns a user if they match username
    User findByUsername(String username);
}