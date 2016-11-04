package paxxa.com.ees.service.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.dto.provisionSettings.SellerForProvisionDTO;
import paxxa.com.ees.entity.seller.Seller;
import paxxa.com.ees.repository.seller.SellerRepositoryApp;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SellerService {

    @Autowired
    private SellerRepositoryApp sellerRepositoryApp;


    public List<String> getActiveAvailableSellers(){
        return sellerRepositoryApp.getActiveAvailableSellers();
    }

    public List<SellerForProvisionDTO> getSellersForProvision(){
        List<SellerForProvisionDTO> results = new ArrayList<>();
        List<Seller> sellers = sellerRepositoryApp.getSellers();
        sellers.forEach(x->{
            SellerForProvisionDTO sellerForProvisionDTO = new SellerForProvisionDTO();
            sellerForProvisionDTO.setSellerCode(x.getSellerCode());
            results.add(sellerForProvisionDTO);
                }
        );
        return results;
    }

}
