package paxxa.com.ees.controllerRest.offer.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import paxxa.com.ees.dto.company.CompanyDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRootDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.AllReceiverPointsDataEstimationForSellerDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.AllReceiverPointsEstimationForSellerDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.AllReceiverPointsProvisionForSellerDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.OfferSummaryDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.*;
import paxxa.com.ees.service.offerStorage.OfferStorageService;
import paxxa.com.ees.service.utils.SampleDataService;

import javax.xml.ws.Response;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class ElectricityOfferAdminController {

    @Autowired
    private SampleDataService sampleDataService;
    @Autowired
    private OfferStorageService offerStorageService;


    @RequestMapping(value = "/a/electricityOffer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElectricityOfferRootDTO> getPersonalData(@PathVariable int id) {

        Object offer = offerStorageService.getOffer(id);
        if (offer instanceof ElectricityOfferRootDTO) {
            ElectricityOfferRootDTO electricityOfferRootDTO = (ElectricityOfferRootDTO) offer;
            return new ResponseEntity<ElectricityOfferRootDTO>(electricityOfferRootDTO, HttpStatus.OK);
        }
        return null;
    }

}























