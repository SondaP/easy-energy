package paxxa.com.ees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paxxa.com.ees.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
