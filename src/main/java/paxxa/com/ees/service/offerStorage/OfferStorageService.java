package paxxa.com.ees.service.offerStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.domainConstans.DomainConstans;
import paxxa.com.ees.dto.offer.AbstractOfferDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRootDTO;
import paxxa.com.ees.entity.offerStorage.OfferStorage;
import paxxa.com.ees.entity.user.User;
import paxxa.com.ees.repository.offerStorage.OfferStorageRepository;
import paxxa.com.ees.service.user.UserService;
import paxxa.com.ees.service.utils.UtilsService;

import java.util.List;

@Transactional
@Service
public class OfferStorageService {

    @Autowired
    private UtilsService utilsService;
    @Autowired
    private UserService userService;
    @Autowired
    private OfferStorageRepository offerStorageRepository;

    public OfferStorage saveOfferToOfferStorage(AbstractOfferDTO abstractOfferDTO, String userName) {
        if(abstractOfferDTO instanceof ElectricityOfferRootDTO){
            ElectricityOfferRootDTO electricityOfferRootDTO = (ElectricityOfferRootDTO) abstractOfferDTO;
            OfferStorage offerStorage = new OfferStorage();
            offerStorage.setOfferNumber(electricityOfferRootDTO.getOfferNumber());
            offerStorage.setCreationDate(electricityOfferRootDTO.getCreationDate());
            offerStorage.setLastEdition(electricityOfferRootDTO.getLastEditionDate());
            offerStorage.setProductCode(DomainConstans.PRODUCT_CODE.ELECTRICITY);

            byte[] marshallOffer = utilsService.marshall(ElectricityOfferRootDTO.class, electricityOfferRootDTO);
            offerStorage.setAbstractOfferDTO(marshallOffer);
            offerStorage.setUser(userService.findUserByUserName(userName));
            return offerStorageRepository.save(offerStorage);
        }
        throw new RuntimeException("Illegal offer type");
    }

    public List<OfferStorage> getUserOffers(String userName){
        User userByUserName = userService.findUserByUserName(userName);
        return offerStorageRepository.findByUserIdOrderByCreationDateAsc(userByUserName.getId());
    }

    public Object getOffer(final int offerStorageId){
        OfferStorage offerStorage = offerStorageRepository.getOne(offerStorageId);
        byte[] abstractOfferDTO = offerStorage.getAbstractOfferDTO();
        if(DomainConstans.PRODUCT_CODE.ELECTRICITY.equals(offerStorage.getProductCode())){
            return utilsService.unMarshall(ElectricityOfferRootDTO.class, abstractOfferDTO);
        }
        throw new RuntimeException("Illegal offer type");
    }




}
