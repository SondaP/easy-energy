package paxxa.com.ees.repository.provision;

import org.springframework.data.jpa.repository.JpaRepository;
import paxxa.com.ees.entity.Provision.ProvisionVariant;

public interface ProvisionVariantRepository extends JpaRepository<ProvisionVariant, Integer> {
}
