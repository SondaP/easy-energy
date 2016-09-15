package paxxa.com.ees.repository.provision;

import org.springframework.data.jpa.repository.JpaRepository;
import paxxa.com.ees.entity.Provision.Provision;

public interface ProvisionRepository extends JpaRepository<Provision, Integer>{
}
