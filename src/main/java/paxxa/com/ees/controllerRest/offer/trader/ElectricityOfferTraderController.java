package paxxa.com.ees.controllerRest.offer.trader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import paxxa.com.ees.service.utils.SampleDataService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class ElectricityOfferTraderController {


    @Autowired
    private SampleDataService sampleDataService;


    @RequestMapping(value = "/t/electricityOffer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElectricityOfferRootDTO> getPersonalData() {
        return new ResponseEntity<ElectricityOfferRootDTO>(sampleDataService.createElectricityRootOfferDTO(), HttpStatus.OK);
    }




}























