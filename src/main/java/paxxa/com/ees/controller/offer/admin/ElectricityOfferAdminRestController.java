package paxxa.com.ees.controller.offer.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRoot;
import paxxa.com.ees.entity.offerStorage.OfferStorage;
import paxxa.com.ees.service.offerCalculation.electricityOffer.ElectricityOfferCalculationService;
import paxxa.com.ees.service.offerStorage.OfferStorageService;
import paxxa.com.ees.service.utils.SampleDataService;

import java.security.Principal;

@RestController
public class ElectricityOfferAdminRestController {

    @Autowired
    private SampleDataService sampleDataService;
    @Autowired
    private OfferStorageService offerStorageService;
    @Autowired
    private ElectricityOfferCalculationService electricityOfferCalculationService;



    @RequestMapping(value = "/a/electricityOffer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElectricityOfferRoot> getOfferByOfferStorageId(@PathVariable int id) {

        Object offer = offerStorageService.getOffer(id);

        if (offer instanceof ElectricityOfferRoot) {
            ElectricityOfferRoot electricityOfferRoot = (ElectricityOfferRoot) offer;
            return new ResponseEntity<ElectricityOfferRoot>(electricityOfferRoot, HttpStatus.OK);
        }
        throw new RuntimeException("Illegal offer type");
    }


    @RequestMapping(value = "/a/electricityOffer", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<ElectricityOfferRoot> createOrUpdate(@RequestBody ElectricityOfferRoot electricityOfferRoot, Principal principal) {
        OfferStorage offerStorage = offerStorageService.createOrUpdateOffer(electricityOfferRoot, principal.getName());
        System.out.println(electricityOfferRoot.toString());
        return new ResponseEntity<ElectricityOfferRoot>(electricityOfferRoot, HttpStatus.OK);
    }

    @RequestMapping(value = "/a/calculateElectricityOffer", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<ElectricityOfferRoot> calculateElectricityOffer(@RequestBody ElectricityOfferRoot electricityOfferRoot, Principal principal) {

        ElectricityOfferRoot calculatedOffer = electricityOfferCalculationService.calculateElectricityOffer(
                electricityOfferRoot, principal.getName());
        return new ResponseEntity<ElectricityOfferRoot>(calculatedOffer, HttpStatus.OK);
    }





}























