package paxxa.com.ees.repository.offerStorage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import paxxa.com.ees.entity.offerStorage.OfferStorage;

import java.util.Date;
import java.util.List;


public interface OfferStorageRepository extends JpaRepository<OfferStorage, Integer> {

    List<OfferStorage> findByUser_Id(Integer id, Pageable pageable);

    Integer countByUser_Id(Integer id);

    OfferStorage findByCreationDateAndProductCodeAndUser_idAndOfferNumber(Date creationDate, String productCode, Integer userId, Integer offerNumber);

}
