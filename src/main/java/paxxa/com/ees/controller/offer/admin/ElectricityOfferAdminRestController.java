package paxxa.com.ees.controller.offer.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRootDTO;
import paxxa.com.ees.entity.offerStorage.OfferStorage;
import paxxa.com.ees.service.offerStorage.OfferStorageService;
import paxxa.com.ees.service.utils.SampleDataService;

import java.security.Principal;

@RestController
public class ElectricityOfferAdminRestController {

    @Autowired
    private SampleDataService sampleDataService;
    @Autowired
    private OfferStorageService offerStorageService;



    @RequestMapping(value = "/a/electricityOffer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElectricityOfferRootDTO> getOfferByOfferStorageId(@PathVariable int id) {

        Object offer = offerStorageService.getOffer(id);
        if (offer instanceof ElectricityOfferRootDTO) {
            ElectricityOfferRootDTO electricityOfferRootDTO = (ElectricityOfferRootDTO) offer;
            return new ResponseEntity<ElectricityOfferRootDTO>(electricityOfferRootDTO, HttpStatus.OK);
        }
        return null;
    }


    @RequestMapping(value = "/a/electricityOffer", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<ElectricityOfferRootDTO> createOrUpdate(@RequestBody ElectricityOfferRootDTO electricityOfferRootDTO, Principal principal) {
        OfferStorage offerStorage = offerStorageService.createOrUpdateOffer(electricityOfferRootDTO, principal.getName());
        System.out.println(electricityOfferRootDTO.toString());
        return new ResponseEntity<ElectricityOfferRootDTO>(electricityOfferRootDTO, HttpStatus.OK);
    }



}























