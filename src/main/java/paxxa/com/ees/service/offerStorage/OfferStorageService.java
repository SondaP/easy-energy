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

import java.util.Date;
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

    public OfferStorage saveOrUpdateOffer(AbstractOfferDTO abstractOfferDTO, String userName) {
        if (abstractOfferDTO instanceof ElectricityOfferRootDTO) {
            ElectricityOfferRootDTO electricityOfferRootDTO = (ElectricityOfferRootDTO) abstractOfferDTO;
            byte[] marshallOffer = utilsService.marshall(ElectricityOfferRootDTO.class, electricityOfferRootDTO);

            if (checkIfOfferAlreadyExists(abstractOfferDTO, userName)) {
                OfferStorage existingElectricityOffer = getElectricityOffer(abstractOfferDTO, userName);
                existingElectricityOffer.setLastEdition(new Date());
                existingElectricityOffer.setAbstractOfferDTO(marshallOffer);
                return existingElectricityOffer;
            } else {
                OfferStorage offerStorage = new OfferStorage();
                offerStorage.setOfferNumber(electricityOfferRootDTO.getOfferNumber());
                offerStorage.setCreationDate(electricityOfferRootDTO.getCreationDate());
                offerStorage.setLastEdition(electricityOfferRootDTO.getLastEditionDate());
                offerStorage.setProductCode(DomainConstans.PRODUCT_CODE.ELECTRICITY);
                offerStorage.setCompanyName(electricityOfferRootDTO.getCompanyDTO().getCompanyName());

                offerStorage.setAbstractOfferDTO(marshallOffer);
                offerStorage.setUser(userService.findUserByUserName(userName));
                return offerStorageRepository.save(offerStorage);
            }
        }
        throw new RuntimeException("Illegal offer type");
    }

    private boolean checkIfOfferAlreadyExists(AbstractOfferDTO abstractOfferDTO, String userName) {
        if (abstractOfferDTO instanceof ElectricityOfferRootDTO) {
            OfferStorage offer = getElectricityOffer(abstractOfferDTO, userName);
            if (offer != null) return true;
        }
        return false;
    }

    private OfferStorage getOffer(Date creationDate, String productCode, Integer userId, String offerNumber) {
        return offerStorageRepository.findByCreationDateAndProductCodeAndUser_idAndOfferNumber(creationDate, productCode,
                userId, offerNumber);
    }

    private OfferStorage getElectricityOffer(AbstractOfferDTO abstractOfferDTO, String userName) {
        String productCode = DomainConstans.PRODUCT_CODE.ELECTRICITY;
        Integer userId = userService.findUserByUserName(userName).getId();
        ElectricityOfferRootDTO electricityOfferRootDTO = (ElectricityOfferRootDTO) abstractOfferDTO;
        if (electricityOfferRootDTO.getOfferNumber() != null) {
            return offerStorageRepository.findByCreationDateAndProductCodeAndUser_idAndOfferNumber(
                    electricityOfferRootDTO.getCreationDate(),
                    productCode, userId, electricityOfferRootDTO.getOfferNumber());
        }
        return null;
    }

    /*private String getNextNumberForProductCode(String productCode,  String userName){

    }*/

    public List<OfferStorage> getUserOffers(String userName) {
        User userByUserName = userService.findUserByUserName(userName);
        return offerStorageRepository.findByUser_IdOrderByCreationDateAsc(userByUserName.getId());
    }

    public Object getOffer(final int offerStorageId) {
        OfferStorage offerStorage = offerStorageRepository.getOne(offerStorageId);
        byte[] abstractOfferDTO = offerStorage.getAbstractOfferDTO();
        if (DomainConstans.PRODUCT_CODE.ELECTRICITY.equals(offerStorage.getProductCode())) {
            return utilsService.unMarshall(ElectricityOfferRootDTO.class, abstractOfferDTO);
        }
        throw new RuntimeException("Illegal offer type");
    }

    public void removeOffer(final int offerId) {
        OfferStorage offerStorage = offerStorageRepository.getOne(offerId);
        offerStorage.setUser(null);
        offerStorageRepository.delete(offerId);
    }


}
