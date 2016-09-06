package service.offerStorage;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRoot;
import paxxa.com.ees.entity.offerStorage.OfferStorage;
import paxxa.com.ees.repository.offerStorage.OfferStorageRepository;
import paxxa.com.ees.repository.offerStorage.OfferStorageRepositoryApp;
import paxxa.com.ees.service.offerStorage.OfferStorageService;
import paxxa.com.ees.service.utils.SampleDataService;
import paxxa.com.ees.service.utils.UtilsService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class OfferStorageServiceTest {

    public static final String USER_NAME = "d";
    public final Integer USER_ID = 2;
    @Autowired
    private OfferStorageService offerStorageService;
    @Autowired
    private SampleDataService sampleDataService;
    @Autowired
    private OfferStorageRepository offerStorageRepository;
    @Autowired
    private OfferStorageRepositoryApp offerStorageRepositoryApp;
    @Autowired
    private UtilsService utilsService;


    @Test
    public void shouldSaveOfferToUser() {
        //given
        ElectricityOfferRoot electricityRootOfferDTO = sampleDataService.createElectricityRootOfferDTO();
        //when
        OfferStorage savedOfferStorage = offerStorageService.createOrUpdateOffer(electricityRootOfferDTO, USER_NAME);
        //then
        Assert.assertNotNull(savedOfferStorage);
        Assert.assertTrue(savedOfferStorage.getId() != 0);
    }

    @Test
    public void shouldReturnListOfOfferStorage() {
        //given
        ElectricityOfferRoot electricityRootOfferDTO = sampleDataService.createElectricityRootOfferDTO();
        OfferStorage savedOfferStorage = offerStorageService.createOrUpdateOffer(electricityRootOfferDTO, USER_NAME);
        //when
        List<OfferStorage> userOffers = offerStorageService.getUserOffers(USER_NAME);
        //then
        Assert.assertNotNull(userOffers);
        Assert.assertTrue(userOffers.size() != 0);
    }

    @Test
    public void shouldReturnOffer() {
        //given
        ElectricityOfferRoot electricityRootOfferDTO = sampleDataService.createElectricityRootOfferDTO();
        OfferStorage savedOfferStorage = offerStorageService.createOrUpdateOffer(electricityRootOfferDTO, USER_NAME);
        //when
        Object offer = offerStorageService.getOffer(savedOfferStorage.getId());
        //then
        Assert.assertNotNull(offer);
        Assert.assertTrue(offer instanceof ElectricityOfferRoot);

    }

    @Test
    public void shouldCheckIfOfferExists() {
        //given
        ElectricityOfferRoot electricityRootOfferDTO = sampleDataService.createElectricityRootOfferDTO();
        OfferStorage savedOfferStorage = offerStorageService.createOrUpdateOffer(electricityRootOfferDTO, USER_NAME);
        //when
        OfferStorage foundOfferAfterSave =
                offerStorageRepository.findByCreationDateAndProductCodeAndUser_idAndOfferNumber(savedOfferStorage.getCreationDate(),
                        savedOfferStorage.getProductCode(), savedOfferStorage.getUser().getId(), savedOfferStorage.getOfferNumber());
        //then
        Assert.assertNotNull(foundOfferAfterSave);
        Assert.assertEquals(savedOfferStorage.getCompanyName(), foundOfferAfterSave.getCompanyName());
    }

    @Test
    public void shouldUpDateOffer() {
        //given
        ElectricityOfferRoot electricityRootOfferDTO = sampleDataService.createElectricityRootOfferDTO();
        OfferStorage savedOfferStorage = offerStorageService.createOrUpdateOffer(electricityRootOfferDTO, USER_NAME);
        byte[] abstractOfferDTO = savedOfferStorage.getAbstractOfferDTO();
        Object offer = utilsService.unMarshall(ElectricityOfferRoot.class, abstractOfferDTO);
        ElectricityOfferRoot electricityOfferRoot_Candidate_For_Update;
        if (offer instanceof ElectricityOfferRoot) {
            electricityOfferRoot_Candidate_For_Update = (ElectricityOfferRoot) offer;
        } else {
            throw new RuntimeException("Illegal offer type");
        }
        //when
        electricityOfferRoot_Candidate_For_Update.getCompanyDTO().setCompanyName("After upDate");
        OfferStorage offerStorage_After_UpDate = offerStorageService.createOrUpdateOffer(electricityOfferRoot_Candidate_For_Update, USER_NAME);
        //then
        Assert.assertTrue(electricityOfferRoot_Candidate_For_Update.getCompanyDTO().getCompanyName().
                equals(offerStorage_After_UpDate.getCompanyName()));

        System.err.println(new String(offerStorage_After_UpDate.getAbstractOfferDTO()));
    }


}
