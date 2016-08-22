package service.offerStorage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import paxxa.com.ees.service.offerStorage.OfferStorageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class OfferStorageServiceTest {

    @Autowired
    private OfferStorageService offerStorageService;

    @Test
    public void test(){
        System.out.println(offerStorageService.cokolwiek());
        System.out.println("co kolwiek");
    }


}
