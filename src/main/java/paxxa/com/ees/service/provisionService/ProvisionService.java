package paxxa.com.ees.service.provisionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.provisionSettings.ProvisionVariantDTO;
import paxxa.com.ees.dto.provisionSettings.UserProvisionDTO;
import paxxa.com.ees.entity.provision.ProvisionVariant;
import paxxa.com.ees.repository.provision.ProvisionConditionsRepositoryApp;
import paxxa.com.ees.service.user.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvisionService {

    @Autowired
    private UserService userService;
    @Autowired
    private ProvisionConditionsRepositoryApp provisionConditionsRepositoryApp;


    public List<ProvisionVariant> getProvisionVariants(final String userName,
                                                       final String PRODUCT_CODE,
                                                       final String SELLER_NAME) {
        Integer userIdByUserName = userService.getUserIdByUserName(userName);
        return provisionConditionsRepositoryApp.getProvisionVariants(userIdByUserName, PRODUCT_CODE, SELLER_NAME);
    }

    public UserProvisionDTO getUserProvisionDTO(final String userName,
                                                final String PRODUCT_CODE,
                                                final String SELLER_NAME) {
        List<ProvisionVariantDTO> provisionVariantsDTO = getProvisionVariantsDTO(userName, PRODUCT_CODE, SELLER_NAME);
        UserProvisionDTO userProvisionDTO = new UserProvisionDTO();
        userProvisionDTO.setProvisionVariantDTOList(provisionVariantsDTO);
        userProvisionDTO.setUserName(userName);
        userProvisionDTO.setProductCode(PRODUCT_CODE);
        userProvisionDTO.setSellerCode(SELLER_NAME);
        return userProvisionDTO;
    }

    public List<ProvisionVariantDTO> getProvisionVariantsDTO(final String userName,
                                                             final String PRODUCT_CODE,
                                                             final String SELLER_NAME) {
        Integer userIdByUserName = userService.getUserIdByUserName(userName);

        List<ProvisionVariantDTO> results = new ArrayList<>();
        List<ProvisionVariant> provisionVariants = provisionConditionsRepositoryApp
                .getProvisionVariants(userIdByUserName, PRODUCT_CODE, SELLER_NAME);
        provisionVariants.forEach(x -> {
            ProvisionVariantDTO provisionVariantDTO = new ProvisionVariantDTO();
            provisionVariantDTO.setProvisionPercentageValue(x.getProvisionPercentageValue());
            provisionVariantDTO.setProvisionLevelDescription(x.getProvisionLevelDescription());
            results.add(provisionVariantDTO);
        });
        return results;
    }

}
