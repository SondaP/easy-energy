package paxxa.com.ees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paxxa.com.ees.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
}
