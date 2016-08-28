package service.offerStorage;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import paxxa.com.domainConstans.DomainConstans;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRootDTO;
import paxxa.com.ees.entity.offerStorage.OfferStorage;
import paxxa.com.ees.repository.offerStorage.OfferStorageRepository;
import paxxa.com.ees.repository.offerStorage.OfferStorageRepositoryApp;
import paxxa.com.ees.service.offerStorage.OfferStorageService;
import paxxa.com.ees.service.utils.SampleDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
//@WebAppConfiguration
public class OfferStorageServiceOfferNumberTest {

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

    @Test
    public void shouldGetLastOfferNumberEqualsZero() {
        //given
        String productCode = DomainConstans.PRODUCT_CODE.ELECTRICITY;
        //when
        Integer lastOfferNumberForProductCode = offerStorageRepositoryApp.getLastOfferNumberForProductCode(productCode, USER_ID);
        //then
        Assert.assertTrue(lastOfferNumberForProductCode == 0);
    }

    @Test
    public void shouldGetProperLastOfferNumber() throws InterruptedException {
        //given
        ElectricityOfferRootDTO electricityRootOfferDTO = sampleDataService.createElectricityRootOfferDTO();
        OfferStorage savedOfferStorage = offerStorageService.createOrUpdateOffer(electricityRootOfferDTO, USER_NAME);
        ElectricityOfferRootDTO electricityRootOfferDTO2 = sampleDataService.createElectricityRootOfferDTO2();
        OfferStorage savedOfferStorage2 = offerStorageService.createOrUpdateOffer(electricityRootOfferDTO2, USER_NAME);

        String productCode = DomainConstans.PRODUCT_CODE.ELECTRICITY;
        //when
        Integer lastOfferNumberForProductCode = offerStorageRepositoryApp.getLastOfferNumberForProductCode(productCode, USER_ID);
        //then
        Assert.assertTrue(lastOfferNumberForProductCode == 2);
    }


}
