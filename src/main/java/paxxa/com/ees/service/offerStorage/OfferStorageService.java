package paxxa.com.ees.service.offerStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.domainConstans.DomainConstans;
import paxxa.com.ees.dto.offer.AbstractOfferDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRootDTO;
import paxxa.com.ees.entity.offerStorage.OfferStorage;
import paxxa.com.ees.service.user.UserService;
import paxxa.com.ees.service.utils.UtilsService;


@Service
public class OfferStorageService {

    @Autowired
    private UtilsService utilsService;
    @Autowired
    private UserService userService;

    public void saveOfferToOfferStorage(AbstractOfferDTO abstractOfferDTO, final int userId) {
        if(abstractOfferDTO instanceof ElectricityOfferRootDTO){
            ElectricityOfferRootDTO electricityOfferRootDTO = (ElectricityOfferRootDTO) abstractOfferDTO;
            OfferStorage offerStorage = new OfferStorage();
            offerStorage.setOfferNumber(electricityOfferRootDTO.getOfferNumber());
            offerStorage.setCreationDate(electricityOfferRootDTO.getCreationDate());
            offerStorage.setLastEdition(electricityOfferRootDTO.getLastEditionDate());
            offerStorage.setProductCode(DomainConstans.PRODUCT_CODE.ELECTRICITY);

            byte[] marshallOffer = utilsService.marshall(ElectricityOfferRootDTO.class, electricityOfferRootDTO);
            offerStorage.setOffer(marshallOffer);
            offerStorage.setUser(userService.findById(userId));

        }

    }




}
