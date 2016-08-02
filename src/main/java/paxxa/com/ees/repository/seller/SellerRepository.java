package paxxa.com.ees.repository.seller;

import org.springframework.data.jpa.repository.JpaRepository;
import paxxa.com.ees.entity.seller.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer>{
}
