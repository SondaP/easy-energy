package paxxa.com.ees.service.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.repository.seller.SellerRepositoryApp;

import java.util.List;

@Service
@Transactional
public class SellerService {

    @Autowired
    private SellerRepositoryApp sellerRepositoryApp;

    public List<String> getActiveAvailableSellers(){
        return sellerRepositoryApp.getActiveAvailableSellers();
    }

}
