package service.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRoot;
import paxxa.com.ees.service.utils.SampleDataService;
import paxxa.com.ees.service.utils.UtilsService;

import javax.xml.bind.JAXBException;
import java.text.ParseException;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class UtilsServiceTest {

    @Autowired
    private UtilsService utilsService;
    @Autowired
    private SampleDataService sampleDataService;

    @Test
    public void shouldMarshalObject() throws JAXBException, ClassNotFoundException {
        //give
        ElectricityOfferRoot electricityRootOfferDTO = sampleDataService.createElectricityRootOfferDTO();
        //when
        byte[] serialized = utilsService.marshall(ElectricityOfferRoot.class, sampleDataService.createElectricityRootOfferDTO());
        //then
        Assert.assertNotNull(serialized);
        System.out.println(serialized.toString());
        System.err.println(new String(serialized));
    }

    @Test
    public void shouldUnMarshalObject() throws JAXBException, ClassNotFoundException {
        //given
        ElectricityOfferRoot electricityRootOfferDTO = sampleDataService.createElectricityRootOfferDTO();
        byte[] serialized = utilsService.marshall(ElectricityOfferRoot.class, sampleDataService.createElectricityRootOfferDTO());
        //when
        Object unMarshaledObject = utilsService.unMarshall(ElectricityOfferRoot.class, serialized);
        //then
        Assert.assertTrue(unMarshaledObject instanceof ElectricityOfferRoot);
    }

    @Test
    public void shouldCalculateNumberOfDaysBetweenDates() throws ParseException {
        //given
        Date startDate = utilsService.getDateObjectForPattern("2016-01-01");
        Date endDate = utilsService.getDateObjectForPattern("2016-01-26");
        //when
        Integer differenceDays = utilsService.countDaysBetweenTwoDates(startDate, endDate);
        //then
        Assert.assertTrue(differenceDays == 26);
    }

    @Test
    public void shouldCalculateNumberOfDaysBetweenSameDates() throws ParseException {
        //given
        Date startDate = utilsService.getDateObjectForPattern("2016-01-01");
        Date endDate = utilsService.getDateObjectForPattern("2016-01-01");
        //when
        Integer differenceDays = utilsService.countDaysBetweenTwoDates(startDate, endDate);
        //then
        Assert.assertTrue(differenceDays == 1);
    }

}
