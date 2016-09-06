package paxxa.com.ees.controller.offer.trader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRoot;
import paxxa.com.ees.service.utils.SampleDataService;

@RestController
public class ElectricityOfferTraderRestController {


    @Autowired
    private SampleDataService sampleDataService;


    @RequestMapping(value = "/t/electricityOffer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElectricityOfferRoot> getPersonalData() {
        return new ResponseEntity<ElectricityOfferRoot>(sampleDataService.createElectricityRootOfferDTO(), HttpStatus.OK);
    }




}























