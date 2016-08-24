package paxxa.com.ees.repository.offerStorage;

import org.springframework.data.jpa.repository.JpaRepository;
import paxxa.com.ees.entity.offerStorage.OfferStorage;

import java.util.List;


public interface OfferStorageRepository extends JpaRepository<OfferStorage, Integer> {
    List<OfferStorage> findByUser_IdOrderByCreationDateAsc(Integer id);
}
