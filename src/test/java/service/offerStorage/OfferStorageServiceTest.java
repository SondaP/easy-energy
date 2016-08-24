package service.offerStorage;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRootDTO;
import paxxa.com.ees.entity.offerStorage.OfferStorage;
import paxxa.com.ees.service.offerStorage.OfferStorageService;
import paxxa.com.ees.service.utils.SampleDataService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class OfferStorageServiceTest {

    public static final String USER_NAME = "d";
    @Autowired
    private OfferStorageService offerStorageService;
    @Autowired
    private SampleDataService sampleDataService;

    @Test
    public void shouldSaveOfferToUser(){
        //given
        ElectricityOfferRootDTO electricityRootOfferDTO = sampleDataService.createElectricityRootOfferDTO();
        //when
        OfferStorage savedOfferStorage = offerStorageService.saveOfferToOfferStorage(electricityRootOfferDTO, USER_NAME);
        //then
        Assert.assertNotNull(savedOfferStorage);
        Assert.assertTrue(savedOfferStorage.getId() != 0);
    }

    @Test
    public void shouldReturnListOfOfferStorage(){
        //given
        ElectricityOfferRootDTO electricityRootOfferDTO = sampleDataService.createElectricityRootOfferDTO();
        OfferStorage savedOfferStorage = offerStorageService.saveOfferToOfferStorage(electricityRootOfferDTO, USER_NAME);
        //when
        List<OfferStorage> userOffers = offerStorageService.getUserOffers(USER_NAME);
        //then
        Assert.assertNotNull(userOffers);
        Assert.assertTrue(userOffers.size() != 0);
    }

    @Test
    public void shouldReturnOffer(){
        //given
        ElectricityOfferRootDTO electricityRootOfferDTO = sampleDataService.createElectricityRootOfferDTO();
        OfferStorage savedOfferStorage = offerStorageService.saveOfferToOfferStorage(electricityRootOfferDTO, USER_NAME);
        //when
        Object offer = offerStorageService.getOffer(savedOfferStorage.getId());
        //then
        Assert.assertNotNull(offer);
        Assert.assertTrue(offer instanceof ElectricityOfferRootDTO);

    }


}
