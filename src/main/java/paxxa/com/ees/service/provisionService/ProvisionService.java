package paxxa.com.ees.service.provisionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.dto.provisionSettings.ProvisionVariantDTO;
import paxxa.com.ees.dto.provisionSettings.UserProvisionDTO;
import paxxa.com.ees.entity.provision.ProvisionConditions;
import paxxa.com.ees.entity.provision.ProvisionVariant;
import paxxa.com.ees.repository.provision.ProvisionConditionsRepository;
import paxxa.com.ees.repository.provision.ProvisionConditionsRepositoryApp;
import paxxa.com.ees.repository.provision.ProvisionVariantRepository;
import paxxa.com.ees.service.user.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProvisionService {

    @Autowired
    private UserService userService;
    @Autowired
    private ProvisionConditionsRepository provisionConditionsRepository;
    @Autowired
    private ProvisionConditionsRepositoryApp provisionConditionsRepositoryApp;
    @Autowired
    private ProvisionVariantRepository provisionVariantRepository;



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

    private List<ProvisionVariantDTO> getProvisionVariantsDTO(final String userName,
                                                              final String PRODUCT_CODE,
                                                              final String SELLER_NAME) {
        Integer userIdByUserName = userService.getUserIdByUserName(userName);

        List<ProvisionVariantDTO> results = new ArrayList<>();
        List<ProvisionVariant> provisionVariants = provisionConditionsRepositoryApp
                .getProvisionVariants(userIdByUserName, PRODUCT_CODE, SELLER_NAME);
        provisionVariants.forEach(x -> {
            ProvisionVariantDTO provisionVariantDTO = new ProvisionVariantDTO();
            provisionVariantDTO.setId(x.getId());
            provisionVariantDTO.setProvisionPercentageValue(x.getProvisionPercentageValue());
            provisionVariantDTO.setProvisionLevelDescription(x.getProvisionLevelDescription());
            results.add(provisionVariantDTO);
        });
        return results;
    }

    public void updateUserProvisionVariants(UserProvisionDTO userProvisionDTO) {
        String sellerCode = userProvisionDTO.getSellerCode();
        String productCode = userProvisionDTO.getProductCode();
        Integer userIdByUserName = userService.getUserIdByUserName(userProvisionDTO.getUserName());
        ProvisionConditions userProvisionConditions = provisionConditionsRepositoryApp
                .getUserProvisionConditions(userIdByUserName, productCode, sellerCode);

        List<ProvisionVariant> provisionVariantList = userProvisionConditions.getProvisionVariantList();
        List<ProvisionVariantDTO> provisionVariantDTOList = userProvisionDTO.getProvisionVariantDTOList();

        deleteMissingVariants(provisionVariantList, provisionVariantDTOList);

        provisionConditionsRepository.save(userProvisionConditions);
    }

    private void updateExistingVariants() {

    }

    private void deleteMissingVariants(List<ProvisionVariant> provisionVariantList,
                                       List<ProvisionVariantDTO> provisionVariantDTOList) {
        List<Integer> missingVariantsIds = getMissingVariantsIds(provisionVariantList, provisionVariantDTOList);
        for (Integer missingVariantsId : missingVariantsIds) {
            ProvisionVariant provisionVariantForRemove = provisionVariantRepository.getOne(missingVariantsId);
            provisionVariantList.remove(provisionVariantForRemove);
        }
    }

    private void addNewVariants() {

    }

    private List<Integer> getMissingVariantsIds(List<ProvisionVariant> provisionVariantList,
                                                List<ProvisionVariantDTO> provisionVariantDTOList) {
        List<Integer> provisionVariantIdList = provisionVariantList
                .stream()
                .map(x -> x.getId())
                .collect(Collectors.toList());
        List<Integer> provisionVariantDTOIdList = provisionVariantDTOList
                .stream()
                .map(x -> x.getId())
                .collect(Collectors.toList());
        List<Integer> missingProvisionVariantsIds = new ArrayList<>();
        provisionVariantIdList.forEach(x -> {
                    if (!provisionVariantDTOIdList.contains(x)) {
                        missingProvisionVariantsIds.add(x);
                    }
                }
        );
        if (missingProvisionVariantsIds == null) {
            return Collections.EMPTY_LIST;
        } else {
            return missingProvisionVariantsIds;
        }
    }


}
