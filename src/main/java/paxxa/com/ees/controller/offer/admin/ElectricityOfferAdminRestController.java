package paxxa.com.ees.controller.offer.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRootDTO;
import paxxa.com.ees.entity.offerStorage.OfferStorage;
import paxxa.com.ees.service.offerCalculation.electricityOffer.ElectricityOfferCalculationService;
import paxxa.com.ees.service.offerStorage.OfferStorageService;
import paxxa.com.ees.service.utils.SampleDataService;

import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<ElectricityOfferRootDTO> getOfferByOfferStorageId(@PathVariable int id) {

        Object offer = offerStorageService.getOffer(id);

        if (offer instanceof ElectricityOfferRootDTO) {
            ElectricityOfferRootDTO electricityOfferRootDTO = (ElectricityOfferRootDTO) offer;
            return new ResponseEntity<ElectricityOfferRootDTO>(electricityOfferRootDTO, HttpStatus.OK);
        }
        throw new RuntimeException("Illegal offer type");
    }


    @RequestMapping(value = "/a/electricityOffer", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<ElectricityOfferRootDTO> createOrUpdate(@RequestBody ElectricityOfferRootDTO electricityOfferRootDTO, Principal principal) {
        OfferStorage offerStorage = offerStorageService.createOrUpdateOffer(electricityOfferRootDTO, principal.getName());
        System.out.println(electricityOfferRootDTO.toString());
        return new ResponseEntity<ElectricityOfferRootDTO>(electricityOfferRootDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/a/calculateElectricityOffer", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<ElectricityOfferRootDTO> calculateElectricityOffer(@RequestBody ElectricityOfferRootDTO electricityOfferRootDTO, Principal principal) {

        ElectricityOfferRootDTO calculatedOffer = electricityOfferCalculationService.calculateElectricityOffer(
                electricityOfferRootDTO, principal.getName());
        return new ResponseEntity<ElectricityOfferRootDTO>(calculatedOffer, HttpStatus.OK);
    }





}























