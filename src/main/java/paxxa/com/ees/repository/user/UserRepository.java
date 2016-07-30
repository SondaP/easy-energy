package paxxa.com.ees.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import paxxa.com.ees.entity.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String userName);
}
