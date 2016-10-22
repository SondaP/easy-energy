package paxxa.com.ees.service.provisionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.ees.entity.provision.ProvisionVariant;
import paxxa.com.ees.repository.provision.ProvisionRepository;

import java.util.List;

@Service
public class ProvisionService {

    @Autowired
    private ProvisionRepository provisionRepository;


    public List<ProvisionVariant> getProvisionVariants(final String userName,
                                                       final String PRODUCT_CODE,
                                                       final String SELLER_CODE) {
        return null;
    }

}
