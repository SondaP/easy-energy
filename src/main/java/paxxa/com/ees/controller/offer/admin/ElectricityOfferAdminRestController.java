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
import paxxa.com.ees.service.seller.SellerService;
import paxxa.com.ees.service.utils.SampleDataService;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;


@RestController
public class ElectricityOfferAdminRestController {

    final static Logger LOG = Logger.getLogger(ElectricityOfferAdminRestController.class);

    @Autowired
    private SampleDataService sampleDataService;
    @Autowired
    private OfferStorageService offerStorageService;
    @Autowired
    private ElectricityOfferCalculationService electricityOfferCalculationService;
    @Autowired
    private SellerService sellerService;


    @RequestMapping(value = "/a/electricityOffer/{id}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElectricityOfferRoot> getOfferByOfferStorageId(@PathVariable int id) {

        Object offer = offerStorageService.getOffer(id);

        if (offer instanceof ElectricityOfferRoot) {
            ElectricityOfferRoot electricityOfferRoot = (ElectricityOfferRoot) offer;
            return new ResponseEntity<ElectricityOfferRoot>(electricityOfferRoot, HttpStatus.OK);
        }
        throw new RuntimeException("Illegal offer type");
    }


    @RequestMapping(value = "/a/electricityOffer", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<ElectricityOfferRoot> createOrUpdate(@RequestBody ElectricityOfferRoot electricityOfferRoot,
                                                               Principal principal) {
        LOG.debug("Started saving electricity offer");
        OfferStorage offerStorage = offerStorageService.createOrUpdateOffer(electricityOfferRoot, principal.getName());
        System.out.println(electricityOfferRoot.toString());
        LOG.debug("Succeed saving for electricity offer. Saved offer with number: " + offerStorage.getOfferNumber() + ", for user: "
                + principal.getName());
        return new ResponseEntity<ElectricityOfferRoot>(electricityOfferRoot, HttpStatus.OK);
    }

    @RequestMapping(value = "/a/calculateElectricityOffer", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<ElectricityOfferRoot> calculateElectricityOffer(
            @RequestBody ElectricityOfferRoot electricityOfferRoot, Principal principal) {
        LOG.debug("Started electricity offer calculation");
        ElectricityOfferRoot calculatedOffer = electricityOfferCalculationService.calculateElectricityOffer(
                electricityOfferRoot, principal.getName());
        LOG.debug("Succeed calculation for electricity offer with offer number: " + calculatedOffer.getOfferNumber() + ", for user: "
                + principal.getName());
        return new ResponseEntity<ElectricityOfferRoot>(calculatedOffer, HttpStatus.OK);
    }

    @RequestMapping(value = "/a/calculateElectricityOffer/availableSellers",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getActiveAvailableSellers() {
        LOG.debug("Get available active sellers codes");
        List<String> activeAvailableSellers = sellerService.getActiveAvailableSellers();
        return new ResponseEntity<List<String>>(activeAvailableSellers, HttpStatus.OK);
    }




}























