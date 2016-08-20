/*
package paxxa.com.ees.controllerRest.electricityOffer;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import paxxa.com.ees.dto.company.CompanyDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityRootOfferDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.AllReceiverPointsEstimationForSellerDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.AllReceiverPointsProvisionForSellerDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.AllReceiverPointsDataEstimationForSellerDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.OfferSummaryDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        electricityRootOfferDTO.setRegion("LUBELSKIE");
        electricityRootOfferDTO.setProposalContractMonthLength(24);
        electricityRootOfferDTO.setCompanyDTO(createCompany());
        electricityRootOfferDTO.setOfferNote("Estymacja może ulec zmianie w przypadku zmian cen sprzedawcy energii");
        electricityRootOfferDTO.setReceiverPointDTOList(Arrays.asList(createReceiverPoint()));
        electricityRootOfferDTO.setOfferSummaryDTO(createOfferSummaryDTO());
        return electricityRootOfferDTO;
    }

    private CompanyDTO createCompany() {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyName("Paxxa");
        companyDTO.setNip("5641713529");
        companyDTO.setCompanyCode("Paxxa");
        companyDTO.setStreetName("Nadbystrzycka");
        companyDTO.setStreetNumber("4");
        companyDTO.setZipCode("20-706");
        companyDTO.setCity("Lublin");
        return companyDTO;
    }

    private ReceiverPointDTO createReceiverPoint() {
        ReceiverPointDTO receiverPointDTO = new ReceiverPointDTO();
        receiverPointDTO.setReceiverPointDescription("Licznik numer 894837");
        receiverPointDTO.setActualTradeFee(new BigDecimal(15));
        receiverPointDTO.setProposalTradeFee(new BigDecimal(13));
        receiverPointDTO.setActualTariffsNumber(2);
        receiverPointDTO.setProposalTariffsNumber(2);
        receiverPointDTO.setTariffConsumptionDTOList(createTariffConsumption());
        receiverPointDTO.setTariffDetailsDTOList(createTariffDetails());
        receiverPointDTO.setReceiverPointConsumptionSummaryDTO(createReceiverPointConsumptionSummaryDTO());
        receiverPointDTO.setElectricityReceiverPointEstimationList(createReceiverPointEstimationDTO());

        receiverPointDTO.setActualTariffsNumber(2);
        return receiverPointDTO;
    }

    private List<TariffConsumptionDTO> createTariffConsumption() {
        // Tariff A1 consumption
        TariffConsumptionDTO tariffConsumptionDTO_1 = new TariffConsumptionDTO();
        tariffConsumptionDTO_1.setTariffCode("Taryfa A1");

        TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO_A1_1 = new TariffPeriodConsumptionDTO();
        tariffPeriodConsumptionDTO_A1_1.setPeriodStart(new Date());
        tariffPeriodConsumptionDTO_A1_1.setPeriodEnd(new Date());
        tariffPeriodConsumptionDTO_A1_1.setUnitConsumption(new BigDecimal(500));
        tariffPeriodConsumptionDTO_A1_1.setDocumentNumber("FA 28951/08/2016");

        TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO_A1_2 = new TariffPeriodConsumptionDTO();
        tariffPeriodConsumptionDTO_A1_2.setPeriodStart(new Date());
        tariffPeriodConsumptionDTO_A1_2.setPeriodEnd(new Date());
        tariffPeriodConsumptionDTO_A1_2.setUnitConsumption(new BigDecimal(250));
        tariffPeriodConsumptionDTO_A1_2.setDocumentNumber("FA 28951/08/2016");

        tariffConsumptionDTO_1.setTariffPeriodConsumptionDTOList(Arrays.asList(tariffPeriodConsumptionDTO_A1_1,
                tariffPeriodConsumptionDTO_A1_2));
        // Tariff C2 consumption
        TariffConsumptionDTO tariffConsumptionDTO_2 = new TariffConsumptionDTO();
        tariffConsumptionDTO_2.setTariffCode("Taryfa C2");

        TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO_C2_1 = new TariffPeriodConsumptionDTO();
        tariffPeriodConsumptionDTO_C2_1.setPeriodStart(new Date());
        tariffPeriodConsumptionDTO_C2_1.setPeriodEnd(new Date());
        tariffPeriodConsumptionDTO_C2_1.setUnitConsumption(new BigDecimal(100));
        tariffPeriodConsumptionDTO_C2_1.setDocumentNumber("FA 5483/08/2016");

        TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO_C2_2 = new TariffPeriodConsumptionDTO();
        tariffPeriodConsumptionDTO_C2_2.setPeriodStart(new Date());
        tariffPeriodConsumptionDTO_C2_2.setPeriodEnd(new Date());
        tariffPeriodConsumptionDTO_C2_2.setUnitConsumption(new BigDecimal(150));
        tariffPeriodConsumptionDTO_C2_2.setDocumentNumber("FA 5483/08/2016");

        tariffConsumptionDTO_2.setTariffPeriodConsumptionDTOList(Arrays.asList(tariffPeriodConsumptionDTO_C2_1,
                tariffPeriodConsumptionDTO_C2_2));

        return Arrays.asList(tariffConsumptionDTO_1, tariffConsumptionDTO_2);
    }

    private List<TariffDetailsDTO> createTariffDetails() {
        TariffDetailsDTO tariffDetailsDTO_1 = new TariffDetailsDTO();
        tariffDetailsDTO_1.setTariffCode("Taryfa A1");
        tariffDetailsDTO_1.setTotalUnitConsumptionFromPeriods(new BigDecimal(750));
        tariffDetailsDTO_1.setActualUnitPrice(new BigDecimal(250));
        tariffDetailsDTO_1.setActualShareOfTotalReceiverPointConsumption(new BigDecimal(75));
        tariffDetailsDTO_1.setProposalOfferCode("Taryfa A1");
        tariffDetailsDTO_1.setPredictedShareOfTotalReceiverPointConsumption(new BigDecimal(75));
        tariffDetailsDTO_1.setProposalUnitPrice(new BigDecimal(210));

        TariffDetailsDTO tariffDetailsDTO_2 = new TariffDetailsDTO();
        tariffDetailsDTO_2.setTariffCode("Taryfa C2");
        tariffDetailsDTO_2.setTotalUnitConsumptionFromPeriods(new BigDecimal(250));
        tariffDetailsDTO_2.setActualUnitPrice(new BigDecimal(300));
        tariffDetailsDTO_2.setActualShareOfTotalReceiverPointConsumption(new BigDecimal(25));
        tariffDetailsDTO_2.setProposalOfferCode("Taryfa C2");
        tariffDetailsDTO_2.setPredictedShareOfTotalReceiverPointConsumption(new BigDecimal(25));
        tariffDetailsDTO_2.setProposalUnitPrice(new BigDecimal(275));

        return Arrays.asList(tariffDetailsDTO_1, tariffDetailsDTO_2);
    }

    private ReceiverPointConsumptionSummaryDTO createReceiverPointConsumptionSummaryDTO() {
        ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO =
                new ReceiverPointConsumptionSummaryDTO();
        receiverPointConsumptionSummaryDTO.setTotalNumberOfDaysForAllPeriods(60);
        receiverPointConsumptionSummaryDTO.setTotalElectricityUnitsConsumptionInAllPeriods(new BigDecimal(1000));
        receiverPointConsumptionSummaryDTO.setPredictedElectricityUnitConsumptionPerYear(new BigDecimal(6000));
        return receiverPointConsumptionSummaryDTO;
    }

    private List<ReceiverPointEstimationDTO> createReceiverPointEstimationDTO() {
        //TAURON
        ReceiverPointEstimationDTO receiverPointEstimationDTO_TAURON =
                new ReceiverPointEstimationDTO();
        receiverPointEstimationDTO_TAURON.setSellerCode("Tauron");

        ReceiverDataEstimationDTO receiverDataEstimationDTO_TAURON = new ReceiverDataEstimationDTO();
        receiverDataEstimationDTO_TAURON.setTariffIssueDate(new Date());
        receiverDataEstimationDTO_TAURON.setEstimatedContractValue(new BigDecimal(5000));
        receiverDataEstimationDTO_TAURON.setEstimatedContractValueInYearScale(new BigDecimal(2500));
        receiverDataEstimationDTO_TAURON.setEstimatedSavingsInYearScale(new BigDecimal(600));
        receiverDataEstimationDTO_TAURON.setEstimatedSavingsInContractScale(new BigDecimal(1200));
        receiverDataEstimationDTO_TAURON.setEstimatedSavingsInPercentage(new BigDecimal(30));

        receiverPointEstimationDTO_TAURON.setReceiverDataEstimationDTO(receiverDataEstimationDTO_TAURON);

        ReceiverPointProvisionDTO receiverPointProvisionDTO_TAURON_1 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_TAURON_1.setLevelCode("Próg I");
        receiverPointProvisionDTO_TAURON_1.setProvisionInYearScale(new BigDecimal(300));
        receiverPointProvisionDTO_TAURON_1.setProvisionInContractScale(new BigDecimal(600));

        ReceiverPointProvisionDTO receiverPointProvisionDTO_TAURON_2 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_TAURON_2.setLevelCode("Próg II");
        receiverPointProvisionDTO_TAURON_2.setProvisionInYearScale(new BigDecimal(400));
        receiverPointProvisionDTO_TAURON_2.setProvisionInContractScale(new BigDecimal(800));

        ReceiverPointProvisionDTO receiverPointProvisionDTO_TAURON_3 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_TAURON_3.setLevelCode("Próg III");
        receiverPointProvisionDTO_TAURON_3.setProvisionInYearScale(new BigDecimal(500));
        receiverPointProvisionDTO_TAURON_3.setProvisionInContractScale(new BigDecimal(1000));

        receiverPointEstimationDTO_TAURON.setReceiverPointProvisionDTOList(Arrays.asList(
                receiverPointProvisionDTO_TAURON_1, receiverPointProvisionDTO_TAURON_2, receiverPointProvisionDTO_TAURON_3
        ));
        //Cez
        ReceiverPointEstimationDTO receiverPointEstimationDTO_CEZ =
                new ReceiverPointEstimationDTO();
        receiverPointEstimationDTO_CEZ.setSellerCode("Cez");

        ReceiverDataEstimationDTO receiverDataEstimationDTO_CEZ = new ReceiverDataEstimationDTO();
        receiverDataEstimationDTO_CEZ.setTariffIssueDate(new Date());
        receiverDataEstimationDTO_CEZ.setEstimatedContractValue(new BigDecimal(5000));
        receiverDataEstimationDTO_CEZ.setEstimatedContractValueInYearScale(new BigDecimal(2500));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInYearScale(new BigDecimal(600));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInContractScale(new BigDecimal(1200));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInPercentage(new BigDecimal(30));

        receiverPointEstimationDTO_CEZ.setReceiverDataEstimationDTO(receiverDataEstimationDTO_CEZ);

        ReceiverPointProvisionDTO receiverPointProvisionDTO_CEZ_1 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_CEZ_1.setLevelCode("Próg I");
        receiverPointProvisionDTO_CEZ_1.setProvisionInYearScale(new BigDecimal(350));
        receiverPointProvisionDTO_CEZ_1.setProvisionInContractScale(new BigDecimal(700));

        ReceiverPointProvisionDTO receiverPointProvisionDTO_CEZ_2 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_CEZ_2.setLevelCode("Próg II");
        receiverPointProvisionDTO_CEZ_2.setProvisionInYearScale(new BigDecimal(450));
        receiverPointProvisionDTO_CEZ_2.setProvisionInContractScale(new BigDecimal(900));

        ReceiverPointProvisionDTO receiverPointProvisionDTO_CEZ_3 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_CEZ_3.setLevelCode("Próg III");
        receiverPointProvisionDTO_CEZ_3.setProvisionInYearScale(new BigDecimal(550));
        receiverPointProvisionDTO_CEZ_3.setProvisionInContractScale(new BigDecimal(1100));

        receiverPointEstimationDTO_CEZ.setReceiverPointProvisionDTOList(Arrays.asList(
                receiverPointProvisionDTO_CEZ_1, receiverPointProvisionDTO_CEZ_2, receiverPointProvisionDTO_CEZ_3
        ));
        return Arrays.asList(receiverPointEstimationDTO_TAURON, receiverPointEstimationDTO_CEZ);
    }

    private OfferSummaryDTO createOfferSummaryDTO() {
        OfferSummaryDTO offerSummaryDTO = new OfferSummaryDTO();
        //TAURON
        AllReceiverPointsEstimationForSellerDTO allReceiverPointsEstimationForSellerDTO_TAURON = new AllReceiverPointsEstimationForSellerDTO();
        allReceiverPointsEstimationForSellerDTO_TAURON.setSellerCode("TAURON");

        AllReceiverPointsDataEstimationForSellerDTO allReceiverPointsDataEstimationForSellerDTO_TAURON =
                new AllReceiverPointsDataEstimationForSellerDTO();
        allReceiverPointsDataEstimationForSellerDTO_TAURON.setTariffIssueDate(new Date());
        allReceiverPointsDataEstimationForSellerDTO_TAURON.setEstimatedContractValueForAllPoint(new BigDecimal(5000));
        allReceiverPointsDataEstimationForSellerDTO_TAURON.setEstimatedContractValueInYearScaleForAllPoint(new BigDecimal(2500));
        allReceiverPointsDataEstimationForSellerDTO_TAURON.setEstimatedSavingsInYearScaleForAllPoint(new BigDecimal(600));
        allReceiverPointsDataEstimationForSellerDTO_TAURON.setEstimatedSavingsInContractScaleForAllPoint(new BigDecimal(1200));
        allReceiverPointsDataEstimationForSellerDTO_TAURON.setEstimatedSavingsInPercentageForAllPoint(new BigDecimal(30));

        allReceiverPointsEstimationForSellerDTO_TAURON.setAllReceiverPointsDataEstimationForSellerDTO(
                allReceiverPointsDataEstimationForSellerDTO_TAURON);

        AllReceiverPointsProvisionForSellerDTO allReceiverPointsProvisionForSellerDTO_TAURON_1 = new AllReceiverPointsProvisionForSellerDTO();
        allReceiverPointsProvisionForSellerDTO_TAURON_1.setLevelCode("Próg I");
        allReceiverPointsProvisionForSellerDTO_TAURON_1.setProvisionInYearScaleForAllPoint(new BigDecimal(300));
        allReceiverPointsProvisionForSellerDTO_TAURON_1.setProvisionInContractScaleForAllPoint(new BigDecimal(600));

        AllReceiverPointsProvisionForSellerDTO allReceiverPointsProvisionForSellerDTO_TAURON_2 = new AllReceiverPointsProvisionForSellerDTO();
        allReceiverPointsProvisionForSellerDTO_TAURON_2.setLevelCode("Próg II");
        allReceiverPointsProvisionForSellerDTO_TAURON_2.setProvisionInYearScaleForAllPoint(new BigDecimal(400));
        allReceiverPointsProvisionForSellerDTO_TAURON_2.setProvisionInContractScaleForAllPoint(new BigDecimal(800));

        AllReceiverPointsProvisionForSellerDTO allReceiverPointsProvisionForSellerDTO_TAURON_3 = new AllReceiverPointsProvisionForSellerDTO();
        allReceiverPointsProvisionForSellerDTO_TAURON_3.setLevelCode("Próg III");
        allReceiverPointsProvisionForSellerDTO_TAURON_3.setProvisionInYearScaleForAllPoint(new BigDecimal(500));
        allReceiverPointsProvisionForSellerDTO_TAURON_3.setProvisionInContractScaleForAllPoint(new BigDecimal(1000));

        allReceiverPointsEstimationForSellerDTO_TAURON.setAllReceiverPointsProvisionForSellerDTOList(Arrays.asList(
                allReceiverPointsProvisionForSellerDTO_TAURON_1, allReceiverPointsProvisionForSellerDTO_TAURON_2, allReceiverPointsProvisionForSellerDTO_TAURON_3));
        //CEZ
        AllReceiverPointsEstimationForSellerDTO allReceiverPointsEstimationForSellerDTO_CEZ = new AllReceiverPointsEstimationForSellerDTO();
        allReceiverPointsEstimationForSellerDTO_CEZ.setSellerCode("CEZ");

        AllReceiverPointsDataEstimationForSellerDTO allReceiverPointsDataEstimationForSellerDTO_CEZ =
                new AllReceiverPointsDataEstimationForSellerDTO();
        allReceiverPointsDataEstimationForSellerDTO_CEZ.setTariffIssueDate(new Date());
        allReceiverPointsDataEstimationForSellerDTO_CEZ.setEstimatedContractValueForAllPoint(new BigDecimal(2500));
        allReceiverPointsDataEstimationForSellerDTO_CEZ.setEstimatedContractValueInYearScaleForAllPoint(new BigDecimal(1250));
        allReceiverPointsDataEstimationForSellerDTO_CEZ.setEstimatedSavingsInYearScaleForAllPoint(new BigDecimal(300));
        allReceiverPointsDataEstimationForSellerDTO_CEZ.setEstimatedSavingsInContractScaleForAllPoint(new BigDecimal(600));
        allReceiverPointsDataEstimationForSellerDTO_CEZ.setEstimatedSavingsInPercentageForAllPoint(new BigDecimal(30));

        allReceiverPointsEstimationForSellerDTO_CEZ.setAllReceiverPointsDataEstimationForSellerDTO(
                allReceiverPointsDataEstimationForSellerDTO_CEZ);

        AllReceiverPointsProvisionForSellerDTO allReceiverPointsProvisionForSellerDTO_CEZ_1 = new AllReceiverPointsProvisionForSellerDTO();
        allReceiverPointsProvisionForSellerDTO_CEZ_1.setLevelCode("Próg I");
        allReceiverPointsProvisionForSellerDTO_CEZ_1.setProvisionInYearScaleForAllPoint(new BigDecimal(350));
        allReceiverPointsProvisionForSellerDTO_CEZ_1.setProvisionInContractScaleForAllPoint(new BigDecimal(700));

        AllReceiverPointsProvisionForSellerDTO allReceiverPointsProvisionForSellerDTO_CEZ_2 = new AllReceiverPointsProvisionForSellerDTO();
        allReceiverPointsProvisionForSellerDTO_CEZ_2.setLevelCode("Próg II");
        allReceiverPointsProvisionForSellerDTO_CEZ_2.setProvisionInYearScaleForAllPoint(new BigDecimal(450));
        allReceiverPointsProvisionForSellerDTO_CEZ_2.setProvisionInContractScaleForAllPoint(new BigDecimal(900));

        AllReceiverPointsProvisionForSellerDTO allReceiverPointsProvisionForSellerDTO_CEZ_3 = new AllReceiverPointsProvisionForSellerDTO();
        allReceiverPointsProvisionForSellerDTO_CEZ_3.setLevelCode("Próg III");
        allReceiverPointsProvisionForSellerDTO_CEZ_3.setProvisionInYearScaleForAllPoint(new BigDecimal(550));
        allReceiverPointsProvisionForSellerDTO_CEZ_3.setProvisionInContractScaleForAllPoint(new BigDecimal(1100));

        allReceiverPointsEstimationForSellerDTO_CEZ.setAllReceiverPointsProvisionForSellerDTOList(Arrays.asList(
                allReceiverPointsProvisionForSellerDTO_CEZ_1, allReceiverPointsProvisionForSellerDTO_CEZ_2, allReceiverPointsProvisionForSellerDTO_CEZ_3));


        offerSummaryDTO.setElectricityReceiverPointEstimationList(Arrays.asList(
                allReceiverPointsEstimationForSellerDTO_TAURON, allReceiverPointsEstimationForSellerDTO_CEZ));
        return offerSummaryDTO;

    }

}























*/
