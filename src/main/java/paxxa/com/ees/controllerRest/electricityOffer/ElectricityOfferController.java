package paxxa.com.ees.controllerRest.electricityOffer;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import paxxa.com.ees.dto.company.CompanyDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityRootOfferDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ReceiverPointDTO;

import java.util.Arrays;
import java.util.Date;

@RestController
public class ElectricityOfferController {


    @RequestMapping(value = "/t/electricityOffer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElectricityRootOfferDTO> getPersonalData() {
        return new ResponseEntity<ElectricityRootOfferDTO>(createElectricityRootOfferDTO(), HttpStatus.OK);
    }


    private ElectricityRootOfferDTO createElectricityRootOfferDTO() {
        ElectricityRootOfferDTO electricityRootOfferDTO = new ElectricityRootOfferDTO();
        electricityRootOfferDTO.setOfferNumber("1");
        electricityRootOfferDTO.setCreationDate(new Date());
        electricityRootOfferDTO.setLastEditionDate(new Date());
        electricityRootOfferDTO.setProposoalContractMonthLenght(24);
        electricityRootOfferDTO.setCompanyDTO(createCompany());
        electricityRootOfferDTO.setOfferNote("Estymacja może ulec zmianie w przypadku zmian cen sprzedawcy energii");
        electricityRootOfferDTO.setReceiverPointDTOList(Arrays.asList(createReceiverPoint()));

        return electricityRootOfferDTO;
    }

    private CompanyDTO createCompany() {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyName("Paxxa");
        companyDTO.setNip("5641713529");
        companyDTO.setCompanyCode("Paxxa");
        companyDTO.setStreetName("Nadbystrzycka");
        companyDTO.setStreetNumber("4");
        companyDTO.setCity("Lublin");
        return companyDTO;
    }

    private ReceiverPointDTO createReceiverPoint() {
        ReceiverPointDTO receiverPointDTO = new ReceiverPointDTO();
        receiverPointDTO.setActualTariffsNumber(2);
        return receiverPointDTO;
    }
}
