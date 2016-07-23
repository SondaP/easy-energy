package paxxa.com.ees.repository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import paxxa.com.ees.entity.client.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
