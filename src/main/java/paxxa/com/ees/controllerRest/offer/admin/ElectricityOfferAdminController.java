package paxxa.com.ees.controllerRest.offer.admin;

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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class ElectricityOfferAdminController {


    @RequestMapping(value = "/a/electricityOffer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElectricityOfferRootDTO> getPersonalData() {
        return new ResponseEntity<ElectricityOfferRootDTO>(createElectricityRootOfferDTO(), HttpStatus.OK);
    }


    private ElectricityOfferRootDTO createElectricityRootOfferDTO() {
        ElectricityOfferRootDTO electricityOfferRootDTO = new ElectricityOfferRootDTO();
        electricityOfferRootDTO.setOfferNumber("1");
        electricityOfferRootDTO.setCreationDate(new Date());
        electricityOfferRootDTO.setLastEditionDate(new Date());
        electricityOfferRootDTO.setProposalContractMonthLength(24);
        electricityOfferRootDTO.setCompanyDTO(createCompany());
        electricityOfferRootDTO.setOfferNote("Estymacja może ulec zmianie w przypadku zmian cen sprzedawcy energii");
        electricityOfferRootDTO.setReceiverPointDTOList(Arrays.asList(createReceiverPoint_1(), createReceiverPoint_2() ));
        electricityOfferRootDTO.setOfferSummaryDTO(createOfferSummaryDTO());
        return electricityOfferRootDTO;
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

    // Receiver point 894837
    private ReceiverPointDTO createReceiverPoint_1() {
        ReceiverPointDTO receiverPointDTO = new ReceiverPointDTO();
        receiverPointDTO.setReceiverPointDescription("Licznik numer 894837");
        receiverPointDTO.setActualTradeFee(new BigDecimal(15));
        receiverPointDTO.setActualTariffsNumber(2);
        receiverPointDTO.setProposalTariffsNumber(2);
        receiverPointDTO.setActualTariffList(createActualTariffs_forPoint_1());
        receiverPointDTO.setReceiverPointConsumptionSummaryDTO(createReceiverPointConsumptionSummaryDTO_ForPoint_1());
        receiverPointDTO.setProposalSellerList(createProposalSellerList_ForPoint_1());
        receiverPointDTO.setReceiverPointEstimationList(createReceiverPointEstimationDTO_ForPoint_1());
        return receiverPointDTO;
    }

    private List<ActualTariff> createActualTariffs_forPoint_1() {
        // Tariff A1 consumption
        ActualTariff actualTariff_1 = new ActualTariff();
        actualTariff_1.setActualTariffCode("Taryfa A");
        actualTariff_1.setActualUnitPrice(new BigDecimal(200));
        actualTariff_1.setActualShareOfTotalReceiverPointConsumption(new BigDecimal(75));
        actualTariff_1.setTotalUnitConsumptionFromPeriods(new BigDecimal(750));

        TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO_A1_1 = new TariffPeriodConsumptionDTO();
        tariffPeriodConsumptionDTO_A1_1.setPeriodStart(new Date());
        tariffPeriodConsumptionDTO_A1_1.setPeriodEnd(new Date());
        tariffPeriodConsumptionDTO_A1_1.setUnitConsumption(new BigDecimal(250));
        tariffPeriodConsumptionDTO_A1_1.setDocumentNumber("FA 22/08/2016");

        TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO_A1_2 = new TariffPeriodConsumptionDTO();
        tariffPeriodConsumptionDTO_A1_2.setPeriodStart(new Date());
        tariffPeriodConsumptionDTO_A1_2.setPeriodEnd(new Date());
        tariffPeriodConsumptionDTO_A1_2.setUnitConsumption(new BigDecimal(300));
        tariffPeriodConsumptionDTO_A1_2.setDocumentNumber("FA 55/08/2016");

        TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO_A1_3 = new TariffPeriodConsumptionDTO();
        tariffPeriodConsumptionDTO_A1_2.setPeriodStart(new Date());
        tariffPeriodConsumptionDTO_A1_2.setPeriodEnd(new Date());
        tariffPeriodConsumptionDTO_A1_2.setUnitConsumption(new BigDecimal(200));
        tariffPeriodConsumptionDTO_A1_2.setDocumentNumber("FA 54444445/08/2016");

        actualTariff_1.setTariffPeriodConsumptionDTOList(Arrays.asList(tariffPeriodConsumptionDTO_A1_1,
                tariffPeriodConsumptionDTO_A1_2, tariffPeriodConsumptionDTO_A1_3));
        // Tariff C2 consumption
        ActualTariff actualTariff_2 = new ActualTariff();
        actualTariff_2.setActualTariffCode("Taryfa B");
        actualTariff_2.setActualUnitPrice(new BigDecimal(300));
        actualTariff_2.setActualShareOfTotalReceiverPointConsumption(new BigDecimal(25));
        actualTariff_2.setTotalUnitConsumptionFromPeriods(new BigDecimal(250));

        TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO_C2_1 = new TariffPeriodConsumptionDTO();
        tariffPeriodConsumptionDTO_C2_1.setPeriodStart(new Date());
        tariffPeriodConsumptionDTO_C2_1.setPeriodEnd(new Date());
        tariffPeriodConsumptionDTO_C2_1.setUnitConsumption(new BigDecimal(100));
        tariffPeriodConsumptionDTO_C2_1.setDocumentNumber("FA 58/07/2016");

        TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO_C2_2 = new TariffPeriodConsumptionDTO();
        tariffPeriodConsumptionDTO_C2_2.setPeriodStart(new Date());
        tariffPeriodConsumptionDTO_C2_2.setPeriodEnd(new Date());
        tariffPeriodConsumptionDTO_C2_2.setUnitConsumption(new BigDecimal(150));
        tariffPeriodConsumptionDTO_C2_2.setDocumentNumber("FA 2569/07/2016");

        actualTariff_2.setTariffPeriodConsumptionDTOList(Arrays.asList(tariffPeriodConsumptionDTO_C2_1,
                tariffPeriodConsumptionDTO_C2_2));

        return Arrays.asList(actualTariff_1, actualTariff_2);
    }

    private List<ProposalSeller> createProposalSellerList_ForPoint_1(){
        // Seller Tauron
        ProposalSeller proposalSeller_1 = new ProposalSeller();
        proposalSeller_1.setSellerCode("Tauron");
        proposalSeller_1.setProposalTradeFee(new BigDecimal(12));
        proposalSeller_1.setSellerTariffPublicationDate(new Date());

        ProposalTariff proposalTariff_A_TAURON = new ProposalTariff();
        proposalTariff_A_TAURON.setActualTariffCode("Taryfa A");
        proposalTariff_A_TAURON.setProposalUnitPrice(new BigDecimal(180));
        proposalTariff_A_TAURON.setProposalTariffCode("Taryfa A od Tauron");

        ProposalTariff proposalTariff_B_TAURON = new ProposalTariff();
        proposalTariff_B_TAURON.setActualTariffCode("Taryfa B");
        proposalTariff_B_TAURON.setProposalUnitPrice(new BigDecimal(280));
        proposalTariff_B_TAURON.setProposalTariffCode("Taryfa B od Tauron");
        proposalSeller_1.setProposalTariffList(Arrays.asList(proposalTariff_A_TAURON, proposalTariff_B_TAURON));

        // Seller CEZ
        ProposalSeller proposalSeller_2 = new ProposalSeller();
        proposalSeller_2.setSellerCode("Tauron");
        proposalSeller_2.setProposalTradeFee(new BigDecimal(13));
        proposalSeller_2.setSellerTariffPublicationDate(new Date());

        ProposalTariff proposalTariff_A_CEZ = new ProposalTariff();
        proposalTariff_A_CEZ.setActualTariffCode("Taryfa A");
        proposalTariff_A_CEZ.setProposalUnitPrice(new BigDecimal(175));
        proposalTariff_A_CEZ.setProposalTariffCode("Taryfa A od CEZ");

        ProposalTariff proposalTariff_B_CEZ = new ProposalTariff();
        proposalTariff_B_CEZ.setActualTariffCode("Taryfa B");
        proposalTariff_B_CEZ.setProposalUnitPrice(new BigDecimal(275));
        proposalTariff_B_CEZ.setProposalTariffCode("Taryfa B od CEZ");
        proposalSeller_2.setProposalTariffList(Arrays.asList(proposalTariff_A_CEZ, proposalTariff_B_CEZ));

        return Arrays.asList(proposalSeller_1, proposalSeller_2);

    }


    private ReceiverPointConsumptionSummaryDTO createReceiverPointConsumptionSummaryDTO_ForPoint_1() {
        ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO =
                new ReceiverPointConsumptionSummaryDTO();
        receiverPointConsumptionSummaryDTO.setTotalNumberOfDaysForAllPeriods(60);
        receiverPointConsumptionSummaryDTO.setTotalElectricityUnitsConsumptionInAllPeriods(new BigDecimal(1000));
        receiverPointConsumptionSummaryDTO.setPredictedElectricityUnitConsumptionPerYear(new BigDecimal(6000));
        return receiverPointConsumptionSummaryDTO;
    }

    private List<ReceiverPointEstimationDTO> createReceiverPointEstimationDTO_ForPoint_1() {
        //TAURON
        ReceiverPointEstimationDTO receiverPointEstimationDTO_TAURON =
                new ReceiverPointEstimationDTO();
        receiverPointEstimationDTO_TAURON.setSellerCode("Tauron");

        ReceiverPointDataEstimationDTO receiverPointDataEstimationDTO_TAURON = new ReceiverPointDataEstimationDTO();
        receiverPointDataEstimationDTO_TAURON.setTariffIssueDate(new Date());
        receiverPointDataEstimationDTO_TAURON.setEstimatedContractValue(new BigDecimal(5000));
        receiverPointDataEstimationDTO_TAURON.setEstimatedContractValueInYearScale(new BigDecimal(2500));
        receiverPointDataEstimationDTO_TAURON.setEstimatedSavingsInYearScale(new BigDecimal(600));
        receiverPointDataEstimationDTO_TAURON.setEstimatedSavingsInContractScale(new BigDecimal(1200));
        receiverPointDataEstimationDTO_TAURON.setEstimatedSavingsInPercentage(new BigDecimal(30));

        receiverPointEstimationDTO_TAURON.setReceiverPointDataEstimationDTO(receiverPointDataEstimationDTO_TAURON);

        ReceiverPointProvisionDTO receiverPointProvisionDTO_TAURON_1 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_TAURON_1.setLevelCode("Próg I");
        receiverPointProvisionDTO_TAURON_1.setTraderProvisionInYearScale(new BigDecimal(300));
        receiverPointProvisionDTO_TAURON_1.setTraderProvisionInContractScale(new BigDecimal(600));
        receiverPointProvisionDTO_TAURON_1.setPartnerProvisionInContractScale(new BigDecimal(150));

        ReceiverPointProvisionDTO receiverPointProvisionDTO_TAURON_2 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_TAURON_2.setLevelCode("Próg II");
        receiverPointProvisionDTO_TAURON_2.setTraderProvisionInYearScale(new BigDecimal(400));
        receiverPointProvisionDTO_TAURON_2.setTraderProvisionInContractScale(new BigDecimal(800));
        receiverPointProvisionDTO_TAURON_2.setPartnerProvisionInContractScale(new BigDecimal(200));

        ReceiverPointProvisionDTO receiverPointProvisionDTO_TAURON_3 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_TAURON_3.setLevelCode("Próg III");
        receiverPointProvisionDTO_TAURON_3.setTraderProvisionInYearScale(new BigDecimal(500));
        receiverPointProvisionDTO_TAURON_3.setTraderProvisionInContractScale(new BigDecimal(1000));
        receiverPointProvisionDTO_TAURON_3.setPartnerProvisionInContractScale(new BigDecimal(250));

        receiverPointEstimationDTO_TAURON.setReceiverPointProvisionDTOList(Arrays.asList(
                receiverPointProvisionDTO_TAURON_1, receiverPointProvisionDTO_TAURON_2, receiverPointProvisionDTO_TAURON_3
        ));
        //Cez
        ReceiverPointEstimationDTO receiverPointEstimationDTO_CEZ =
                new ReceiverPointEstimationDTO();
        receiverPointEstimationDTO_CEZ.setSellerCode("Cez");

        ReceiverPointDataEstimationDTO receiverDataEstimationDTO_CEZ = new ReceiverPointDataEstimationDTO();
        receiverDataEstimationDTO_CEZ.setTariffIssueDate(new Date());
        receiverDataEstimationDTO_CEZ.setEstimatedContractValue(new BigDecimal(5000));
        receiverDataEstimationDTO_CEZ.setEstimatedContractValueInYearScale(new BigDecimal(2500));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInYearScale(new BigDecimal(600));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInContractScale(new BigDecimal(1200));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInPercentage(new BigDecimal(30));

        receiverPointEstimationDTO_CEZ.setReceiverPointDataEstimationDTO(receiverDataEstimationDTO_CEZ);

        ReceiverPointProvisionDTO receiverPointProvisionDTO_CEZ_1 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_CEZ_1.setLevelCode("Próg I");
        receiverPointProvisionDTO_CEZ_1.setTraderProvisionInYearScale(new BigDecimal(350));
        receiverPointProvisionDTO_CEZ_1.setTraderProvisionInContractScale(new BigDecimal(700));
        receiverPointProvisionDTO_CEZ_1.setPartnerProvisionInContractScale(new BigDecimal(175));

        ReceiverPointProvisionDTO receiverPointProvisionDTO_CEZ_2 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_CEZ_2.setLevelCode("Próg II");
        receiverPointProvisionDTO_CEZ_2.setTraderProvisionInYearScale(new BigDecimal(450));
        receiverPointProvisionDTO_CEZ_2.setTraderProvisionInContractScale(new BigDecimal(900));
        receiverPointProvisionDTO_CEZ_2.setPartnerProvisionInContractScale(new BigDecimal(225));

        ReceiverPointProvisionDTO receiverPointProvisionDTO_CEZ_3 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_CEZ_3.setLevelCode("Próg III");
        receiverPointProvisionDTO_CEZ_3.setTraderProvisionInYearScale(new BigDecimal(550));
        receiverPointProvisionDTO_CEZ_3.setTraderProvisionInContractScale(new BigDecimal(1100));
        receiverPointProvisionDTO_CEZ_3.setPartnerProvisionInContractScale(new BigDecimal(275));

        receiverPointEstimationDTO_CEZ.setReceiverPointProvisionDTOList(Arrays.asList(
                receiverPointProvisionDTO_CEZ_1, receiverPointProvisionDTO_CEZ_2, receiverPointProvisionDTO_CEZ_3
        ));
        return Arrays.asList(receiverPointEstimationDTO_TAURON, receiverPointEstimationDTO_CEZ);
    }

    // Receiver point 150
    private ReceiverPointDTO createReceiverPoint_2() {
        ReceiverPointDTO receiverPointDTO = new ReceiverPointDTO();
        receiverPointDTO.setReceiverPointDescription("Licznik numer 150");
        receiverPointDTO.setActualTradeFee(new BigDecimal(20));
        receiverPointDTO.setActualTariffsNumber(2);
        receiverPointDTO.setProposalTariffsNumber(2);
        receiverPointDTO.setActualTariffList(createActualTariffs_forPoint_2());
        receiverPointDTO.setReceiverPointConsumptionSummaryDTO(createReceiverPointConsumptionSummaryDTO_ForPoint_2());
        receiverPointDTO.setProposalSellerList(createProposalSellerList_ForPoint_2());
        receiverPointDTO.setReceiverPointEstimationList(createReceiverPointEstimationDTO_ForPoint_2());
        return receiverPointDTO;
    }

    private List<ActualTariff> createActualTariffs_forPoint_2() {
        // Tariff A1 consumption
        ActualTariff actualTariff_1 = new ActualTariff();
        actualTariff_1.setActualTariffCode("Taryfa E");
        actualTariff_1.setActualUnitPrice(new BigDecimal(500));
        actualTariff_1.setActualShareOfTotalReceiverPointConsumption(new BigDecimal(30));
        actualTariff_1.setTotalUnitConsumptionFromPeriods(new BigDecimal(300));

        TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO_A1_1 = new TariffPeriodConsumptionDTO();
        tariffPeriodConsumptionDTO_A1_1.setPeriodStart(new Date());
        tariffPeriodConsumptionDTO_A1_1.setPeriodEnd(new Date());
        tariffPeriodConsumptionDTO_A1_1.setUnitConsumption(new BigDecimal(50));
        tariffPeriodConsumptionDTO_A1_1.setDocumentNumber("FA 485/08/2016");

        TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO_A1_2 = new TariffPeriodConsumptionDTO();
        tariffPeriodConsumptionDTO_A1_2.setPeriodStart(new Date());
        tariffPeriodConsumptionDTO_A1_2.setPeriodEnd(new Date());
        tariffPeriodConsumptionDTO_A1_2.setUnitConsumption(new BigDecimal(250));
        tariffPeriodConsumptionDTO_A1_2.setDocumentNumber("FA 363/07/2016");

        actualTariff_1.setTariffPeriodConsumptionDTOList(Arrays.asList(tariffPeriodConsumptionDTO_A1_1,
                tariffPeriodConsumptionDTO_A1_2));
        // Tariff C2 consumption
        ActualTariff actualTariff_2 = new ActualTariff();
        actualTariff_2.setActualTariffCode("Taryfa F");
        actualTariff_2.setActualUnitPrice(new BigDecimal(400));
        actualTariff_2.setActualShareOfTotalReceiverPointConsumption(new BigDecimal(70));
        actualTariff_2.setTotalUnitConsumptionFromPeriods(new BigDecimal(700));

        TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO_C2_1 = new TariffPeriodConsumptionDTO();
        tariffPeriodConsumptionDTO_C2_1.setPeriodStart(new Date());
        tariffPeriodConsumptionDTO_C2_1.setPeriodEnd(new Date());
        tariffPeriodConsumptionDTO_C2_1.setUnitConsumption(new BigDecimal(100));
        tariffPeriodConsumptionDTO_C2_1.setDocumentNumber("FA 512265656565222/08/2016");

        TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO_C2_2 = new TariffPeriodConsumptionDTO();
        tariffPeriodConsumptionDTO_C2_2.setPeriodStart(new Date());
        tariffPeriodConsumptionDTO_C2_2.setPeriodEnd(new Date());
        tariffPeriodConsumptionDTO_C2_2.setUnitConsumption(new BigDecimal(600));
        tariffPeriodConsumptionDTO_C2_2.setDocumentNumber("FA 566666688484848455/08/2016");

        actualTariff_2.setTariffPeriodConsumptionDTOList(Arrays.asList(tariffPeriodConsumptionDTO_C2_1,
                tariffPeriodConsumptionDTO_C2_2));

        return Arrays.asList(actualTariff_1, actualTariff_2);
    }

    private List<ProposalSeller> createProposalSellerList_ForPoint_2(){
        // Seller Tauron
        ProposalSeller proposalSeller_1 = new ProposalSeller();
        proposalSeller_1.setSellerCode("Tauron");
        proposalSeller_1.setProposalTradeFee(new BigDecimal(16));
        proposalSeller_1.setSellerTariffPublicationDate(new Date());

        ProposalTariff proposalTariff_A_TAURON = new ProposalTariff();
        proposalTariff_A_TAURON.setActualTariffCode("Taryfa E");
        proposalTariff_A_TAURON.setProposalUnitPrice(new BigDecimal(450));
        proposalTariff_A_TAURON.setProposalTariffCode("Taryfa A od Tauron");

        ProposalTariff proposalTariff_B_TAURON = new ProposalTariff();
        proposalTariff_B_TAURON.setActualTariffCode("Taryfa F");
        proposalTariff_B_TAURON.setProposalUnitPrice(new BigDecimal(380));
        proposalTariff_B_TAURON.setProposalTariffCode("Taryfa B od Tauron");
        proposalSeller_1.setProposalTariffList(Arrays.asList(proposalTariff_A_TAURON, proposalTariff_B_TAURON));

        // Seller CEZ
        ProposalSeller proposalSeller_2 = new ProposalSeller();
        proposalSeller_2.setSellerCode("Tauron");
        proposalSeller_2.setProposalTradeFee(new BigDecimal(15));
        proposalSeller_2.setSellerTariffPublicationDate(new Date());

        ProposalTariff proposalTariff_A_CEZ = new ProposalTariff();
        proposalTariff_A_CEZ.setActualTariffCode("Taryfa E");
        proposalTariff_A_CEZ.setProposalUnitPrice(new BigDecimal(410));
        proposalTariff_A_CEZ.setProposalTariffCode("Taryfa E od CEZ");

        ProposalTariff proposalTariff_B_CEZ = new ProposalTariff();
        proposalTariff_B_CEZ.setActualTariffCode("Taryfa F");
        proposalTariff_B_CEZ.setProposalUnitPrice(new BigDecimal(390));
        proposalTariff_B_CEZ.setProposalTariffCode("Taryfa F od CEZ");
        proposalSeller_2.setProposalTariffList(Arrays.asList(proposalTariff_A_CEZ, proposalTariff_B_CEZ));

        return Arrays.asList(proposalSeller_1, proposalSeller_2);

    }


    private ReceiverPointConsumptionSummaryDTO createReceiverPointConsumptionSummaryDTO_ForPoint_2() {
        ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO =
                new ReceiverPointConsumptionSummaryDTO();
        receiverPointConsumptionSummaryDTO.setTotalNumberOfDaysForAllPeriods(80);
        receiverPointConsumptionSummaryDTO.setTotalElectricityUnitsConsumptionInAllPeriods(new BigDecimal(1000));
        receiverPointConsumptionSummaryDTO.setPredictedElectricityUnitConsumptionPerYear(new BigDecimal(6000));
        return receiverPointConsumptionSummaryDTO;
    }

    private List<ReceiverPointEstimationDTO> createReceiverPointEstimationDTO_ForPoint_2() {
        //TAURON
        ReceiverPointEstimationDTO receiverPointEstimationDTO_TAURON =
                new ReceiverPointEstimationDTO();
        receiverPointEstimationDTO_TAURON.setSellerCode("Tauron");

        ReceiverPointDataEstimationDTO receiverPointDataEstimationDTO_TAURON = new ReceiverPointDataEstimationDTO();
        receiverPointDataEstimationDTO_TAURON.setTariffIssueDate(new Date());
        receiverPointDataEstimationDTO_TAURON.setEstimatedContractValue(new BigDecimal(5000));
        receiverPointDataEstimationDTO_TAURON.setEstimatedContractValueInYearScale(new BigDecimal(2500));
        receiverPointDataEstimationDTO_TAURON.setEstimatedSavingsInYearScale(new BigDecimal(600));
        receiverPointDataEstimationDTO_TAURON.setEstimatedSavingsInContractScale(new BigDecimal(1200));
        receiverPointDataEstimationDTO_TAURON.setEstimatedSavingsInPercentage(new BigDecimal(30));

        receiverPointEstimationDTO_TAURON.setReceiverPointDataEstimationDTO(receiverPointDataEstimationDTO_TAURON);

        ReceiverPointProvisionDTO receiverPointProvisionDTO_TAURON_1 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_TAURON_1.setLevelCode("Próg I");
        receiverPointProvisionDTO_TAURON_1.setTraderProvisionInYearScale(new BigDecimal(300));
        receiverPointProvisionDTO_TAURON_1.setTraderProvisionInContractScale(new BigDecimal(600));
        receiverPointProvisionDTO_TAURON_1.setPartnerProvisionInContractScale(new BigDecimal(150));

        ReceiverPointProvisionDTO receiverPointProvisionDTO_TAURON_2 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_TAURON_2.setLevelCode("Próg II");
        receiverPointProvisionDTO_TAURON_2.setTraderProvisionInYearScale(new BigDecimal(400));
        receiverPointProvisionDTO_TAURON_2.setTraderProvisionInContractScale(new BigDecimal(800));
        receiverPointProvisionDTO_TAURON_2.setPartnerProvisionInContractScale(new BigDecimal(200));

        ReceiverPointProvisionDTO receiverPointProvisionDTO_TAURON_3 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_TAURON_3.setLevelCode("Próg III");
        receiverPointProvisionDTO_TAURON_3.setTraderProvisionInYearScale(new BigDecimal(500));
        receiverPointProvisionDTO_TAURON_3.setTraderProvisionInContractScale(new BigDecimal(1000));
        receiverPointProvisionDTO_TAURON_3.setPartnerProvisionInContractScale(new BigDecimal(250));

        receiverPointEstimationDTO_TAURON.setReceiverPointProvisionDTOList(Arrays.asList(
                receiverPointProvisionDTO_TAURON_1, receiverPointProvisionDTO_TAURON_2, receiverPointProvisionDTO_TAURON_3
        ));
        //Cez
        ReceiverPointEstimationDTO receiverPointEstimationDTO_CEZ =
                new ReceiverPointEstimationDTO();
        receiverPointEstimationDTO_CEZ.setSellerCode("Cez");

        ReceiverPointDataEstimationDTO receiverDataEstimationDTO_CEZ = new ReceiverPointDataEstimationDTO();
        receiverDataEstimationDTO_CEZ.setTariffIssueDate(new Date());
        receiverDataEstimationDTO_CEZ.setEstimatedContractValue(new BigDecimal(5000));
        receiverDataEstimationDTO_CEZ.setEstimatedContractValueInYearScale(new BigDecimal(2500));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInYearScale(new BigDecimal(600));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInContractScale(new BigDecimal(1200));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInPercentage(new BigDecimal(30));

        receiverPointEstimationDTO_CEZ.setReceiverPointDataEstimationDTO(receiverDataEstimationDTO_CEZ);

        ReceiverPointProvisionDTO receiverPointProvisionDTO_CEZ_1 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_CEZ_1.setLevelCode("Próg I");
        receiverPointProvisionDTO_CEZ_1.setTraderProvisionInYearScale(new BigDecimal(350));
        receiverPointProvisionDTO_CEZ_1.setTraderProvisionInContractScale(new BigDecimal(700));
        receiverPointProvisionDTO_CEZ_1.setPartnerProvisionInContractScale(new BigDecimal(175));

        ReceiverPointProvisionDTO receiverPointProvisionDTO_CEZ_2 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_CEZ_2.setLevelCode("Próg II");
        receiverPointProvisionDTO_CEZ_2.setTraderProvisionInYearScale(new BigDecimal(450));
        receiverPointProvisionDTO_CEZ_2.setTraderProvisionInContractScale(new BigDecimal(900));
        receiverPointProvisionDTO_CEZ_2.setPartnerProvisionInContractScale(new BigDecimal(225));

        ReceiverPointProvisionDTO receiverPointProvisionDTO_CEZ_3 = new ReceiverPointProvisionDTO();
        receiverPointProvisionDTO_CEZ_3.setLevelCode("Próg III");
        receiverPointProvisionDTO_CEZ_3.setTraderProvisionInYearScale(new BigDecimal(550));
        receiverPointProvisionDTO_CEZ_3.setTraderProvisionInContractScale(new BigDecimal(1100));
        receiverPointProvisionDTO_CEZ_3.setPartnerProvisionInContractScale(new BigDecimal(275));

        receiverPointEstimationDTO_CEZ.setReceiverPointProvisionDTOList(Arrays.asList(
                receiverPointProvisionDTO_CEZ_1, receiverPointProvisionDTO_CEZ_2, receiverPointProvisionDTO_CEZ_3
        ));
        return Arrays.asList(receiverPointEstimationDTO_TAURON, receiverPointEstimationDTO_CEZ);
    }




    // Summary

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
        allReceiverPointsProvisionForSellerDTO_TAURON_1.setTraderProvisionInYearScaleForAllPoint(new BigDecimal(300));
        allReceiverPointsProvisionForSellerDTO_TAURON_1.setTraderProvisionInContractScaleForAllPoint(new BigDecimal(600));
        allReceiverPointsProvisionForSellerDTO_TAURON_1.setPartnerProvisionInContractScaleForAllPoint(new BigDecimal(150));


        AllReceiverPointsProvisionForSellerDTO allReceiverPointsProvisionForSellerDTO_TAURON_2 = new AllReceiverPointsProvisionForSellerDTO();
        allReceiverPointsProvisionForSellerDTO_TAURON_2.setLevelCode("Próg II");
        allReceiverPointsProvisionForSellerDTO_TAURON_2.setTraderProvisionInYearScaleForAllPoint(new BigDecimal(400));
        allReceiverPointsProvisionForSellerDTO_TAURON_2.setTraderProvisionInContractScaleForAllPoint(new BigDecimal(800));
        allReceiverPointsProvisionForSellerDTO_TAURON_2.setPartnerProvisionInContractScaleForAllPoint(new BigDecimal(200));

        AllReceiverPointsProvisionForSellerDTO allReceiverPointsProvisionForSellerDTO_TAURON_3 = new AllReceiverPointsProvisionForSellerDTO();
        allReceiverPointsProvisionForSellerDTO_TAURON_3.setLevelCode("Próg III");
        allReceiverPointsProvisionForSellerDTO_TAURON_3.setTraderProvisionInYearScaleForAllPoint(new BigDecimal(500));
        allReceiverPointsProvisionForSellerDTO_TAURON_3.setTraderProvisionInContractScaleForAllPoint(new BigDecimal(1000));
        allReceiverPointsProvisionForSellerDTO_TAURON_3.setPartnerProvisionInContractScaleForAllPoint(new BigDecimal(250));

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
        allReceiverPointsProvisionForSellerDTO_CEZ_1.setTraderProvisionInYearScaleForAllPoint(new BigDecimal(350));
        allReceiverPointsProvisionForSellerDTO_CEZ_1.setTraderProvisionInContractScaleForAllPoint(new BigDecimal(700));
        allReceiverPointsProvisionForSellerDTO_CEZ_1.setPartnerProvisionInContractScaleForAllPoint(new BigDecimal(175));


        AllReceiverPointsProvisionForSellerDTO allReceiverPointsProvisionForSellerDTO_CEZ_2 = new AllReceiverPointsProvisionForSellerDTO();
        allReceiverPointsProvisionForSellerDTO_CEZ_2.setLevelCode("Próg II");
        allReceiverPointsProvisionForSellerDTO_CEZ_2.setTraderProvisionInYearScaleForAllPoint(new BigDecimal(450));
        allReceiverPointsProvisionForSellerDTO_CEZ_2.setTraderProvisionInContractScaleForAllPoint(new BigDecimal(900));
        allReceiverPointsProvisionForSellerDTO_CEZ_2.setPartnerProvisionInContractScaleForAllPoint(new BigDecimal(225));

        AllReceiverPointsProvisionForSellerDTO allReceiverPointsProvisionForSellerDTO_CEZ_3 = new AllReceiverPointsProvisionForSellerDTO();
        allReceiverPointsProvisionForSellerDTO_CEZ_3.setLevelCode("Próg III");
        allReceiverPointsProvisionForSellerDTO_CEZ_3.setTraderProvisionInYearScaleForAllPoint(new BigDecimal(550));
        allReceiverPointsProvisionForSellerDTO_CEZ_3.setTraderProvisionInContractScaleForAllPoint(new BigDecimal(1100));
        allReceiverPointsProvisionForSellerDTO_CEZ_3.setPartnerProvisionInContractScaleForAllPoint(new BigDecimal(275));

        allReceiverPointsEstimationForSellerDTO_CEZ.setAllReceiverPointsProvisionForSellerDTOList(Arrays.asList(
                allReceiverPointsProvisionForSellerDTO_CEZ_1, allReceiverPointsProvisionForSellerDTO_CEZ_2, allReceiverPointsProvisionForSellerDTO_CEZ_3));

        offerSummaryDTO.setElectricityReceiverPointEstimationList(Arrays.asList(
                allReceiverPointsEstimationForSellerDTO_TAURON, allReceiverPointsEstimationForSellerDTO_CEZ));
        return offerSummaryDTO;

    }

}























