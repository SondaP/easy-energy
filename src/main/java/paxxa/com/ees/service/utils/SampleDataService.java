package paxxa.com.ees.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.company.CompanyDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRoot;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.AllReceiverPointsDataEstimationForSeller;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.AllReceiverPointsEstimationForSeller;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.AllReceiverPointsProvisionForSeller;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.OfferSummaryDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.*;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class SampleDataService {

    public static final String STREFA_A1 = "Strefa A1";
    public static final String STREFA_C2 = "Strefa C2";
    @Autowired
    private UtilsService utilsService;


    public ElectricityOfferRoot createElectricityRootOfferDTO() {
        ElectricityOfferRoot electricityOfferRoot = new ElectricityOfferRoot();
        electricityOfferRoot.setCompanyDTO(createCompany());
        electricityOfferRoot.setReceiverPointList(Arrays.asList(createReceiverPoint_1()));
        electricityOfferRoot.setOfferSummaryDTO(createOfferSummaryDTO());
        electricityOfferRoot.setOfferNote("Estymacja może ulec zmianie w przypadku zmian cen sprzedawcy energii");
        return electricityOfferRoot;
    }

    public ElectricityOfferRoot createElectricityRootOfferDTO2() {
        ElectricityOfferRoot electricityOfferRoot = new ElectricityOfferRoot();

        electricityOfferRoot.setCreationDate(new Date());
        electricityOfferRoot.setLastEditionDate(new Date());
        electricityOfferRoot.setCompanyDTO(createCompany());
        electricityOfferRoot.setOfferNote("FAKE FAKE FAKE FAKE");
        electricityOfferRoot.setReceiverPointList(Arrays.asList(createReceiverPoint_1()));
        electricityOfferRoot.setOfferSummaryDTO(createOfferSummaryDTO());
        return electricityOfferRoot;
    }

    private CompanyDTO createCompany() {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyName("Hotel pod Dębami");
        companyDTO.setNip("5641713529");
        companyDTO.setCompanyCode("Hotel pod Dębami");
        companyDTO.setStreetName("Wysoki dołki");
        companyDTO.setStreetNumber("4");
        companyDTO.setZipCode("20-706");
        companyDTO.setCity("Wrzosy");
        return companyDTO;
    }

    // Receiver point 894837
    private ReceiverPoint createReceiverPoint_1() {
        ReceiverPoint receiverPoint = new ReceiverPoint();
        receiverPoint.setReceiverPointDescription("Licznik numer 894837");
        receiverPoint.setTariffCode("Taryfa Popołudniowa");
        receiverPoint.setActualNumberOfZones(2);
        receiverPoint.setActualZoneList(generateActualZoneList_forPoint_1());
        receiverPoint.setActualZoneConsumptionList(generateActualZoneConsumption_forPoint_1());
        receiverPoint.setReceiverPointOfferCalculation(generateOfferCalculation());
        return receiverPoint;
    }

    private List<ActualZone> generateActualZoneList_forPoint_1() {
        ActualZone actualZone_1 = new ActualZone();
        actualZone_1.setActualZoneCodeCode(STREFA_A1);

        ActualZone actualZone_2 = new ActualZone();
        actualZone_2.setActualZoneCodeCode(STREFA_C2);
        return Arrays.asList(actualZone_1, actualZone_2);
    }

    private List<ActualZoneConsumption> generateActualZoneConsumption_forPoint_1() {
        ActualZoneConsumption actualZoneConsumption_Zone_A1_Document1 = new ActualZoneConsumption();
        actualZoneConsumption_Zone_A1_Document1.setActualZoneCode(STREFA_A1);
        actualZoneConsumption_Zone_A1_Document1.setDocumentNumber("FA 22/08/2016");
        actualZoneConsumption_Zone_A1_Document1.setPeriodStart(utilsService.getDateObjectForPattern("2016-01-20"));
        actualZoneConsumption_Zone_A1_Document1.setGetPeriodStop(utilsService.getDateObjectForPattern("2016-05-25"));
        actualZoneConsumption_Zone_A1_Document1.setUnitConsumption(new BigDecimal(300));

        ActualZoneConsumption actualZoneConsumption_Zone_A1_Document2 = new ActualZoneConsumption();
        actualZoneConsumption_Zone_A1_Document2.setActualZoneCode(STREFA_A1);
        actualZoneConsumption_Zone_A1_Document2.setDocumentNumber("FA 55/08/2016");
        actualZoneConsumption_Zone_A1_Document2.setPeriodStart(utilsService.getDateObjectForPattern("2016-05-10"));
        actualZoneConsumption_Zone_A1_Document2.setGetPeriodStop(utilsService.getDateObjectForPattern("2016-05-20"));
        actualZoneConsumption_Zone_A1_Document2.setUnitConsumption(new BigDecimal(300));

        ActualZoneConsumption actualZoneConsumption_Zone_C2_Document1 = new ActualZoneConsumption();
        actualZoneConsumption_Zone_C2_Document1.setActualZoneCode(STREFA_C2);
        actualZoneConsumption_Zone_C2_Document1.setDocumentNumber("FA 188/10/2016");
        actualZoneConsumption_Zone_C2_Document1.setPeriodStart(utilsService.getDateObjectForPattern("2016-03-10"));
        actualZoneConsumption_Zone_C2_Document1.setGetPeriodStop(utilsService.getDateObjectForPattern("2016-05-10"));
        actualZoneConsumption_Zone_C2_Document1.setUnitConsumption(new BigDecimal(300));

        return Arrays.asList(actualZoneConsumption_Zone_A1_Document1, actualZoneConsumption_Zone_C2_Document1,
                actualZoneConsumption_Zone_C2_Document1);
    }

    private OfferCalculation generateOfferCalculation() {
        OfferCalculation offerCalculation = new OfferCalculation();
        offerCalculation.setTotalConsumptionSummary(createReceiverPointConsumptionSummaryDTO_ForPoint_1());
        offerCalculation.setActualReceiverPointFees(generateActualReceiverPointFees());
        offerCalculation.setOfferParameters(generateOfferParameters());

        offerCalculation.setProposalSellerList(generateProposalSellerList_ForPoint_1());

        return offerCalculation;
    }

    private TotalConsumptionSummary createReceiverPointConsumptionSummaryDTO_ForPoint_1() {
        TotalConsumptionSummary totalConsumptionSummary =
                new TotalConsumptionSummary();
        totalConsumptionSummary.setTotalNumberOfDaysForAllPeriods(60);
        totalConsumptionSummary.setTotalElectricityUnitsConsumptionInAllPeriods(new BigDecimal(1000));
        totalConsumptionSummary.setPredictedElectricityUnitConsumptionPerYear(new BigDecimal(6000));
        return totalConsumptionSummary;
    }

    private ActualReceiverPointFees generateActualReceiverPointFees() {
        ActualReceiverPointFees actualReceiverPointFees = new ActualReceiverPointFees();
        actualReceiverPointFees.setActualTradeFee(new BigDecimal(15));

        ActualZoneFee actualZoneFee_A1 = new ActualZoneFee();
        actualZoneFee_A1.setActualZoneCode(STREFA_A1);
        actualZoneFee_A1.setActualUnitPrice(new BigDecimal(200));

        ActualZoneFee actualZoneFee_C2 = new ActualZoneFee();
        actualZoneFee_C2.setActualZoneCode(STREFA_C2);
        actualZoneFee_C2.setActualUnitPrice(new BigDecimal(300));

        actualReceiverPointFees.setActualZoneFeeList(Arrays.asList(actualZoneFee_A1, actualZoneFee_C2));
        return actualReceiverPointFees;
    }

    private OfferParameters generateOfferParameters() {
        OfferParameters offerParameters = new OfferParameters();
        offerParameters.setProposalContractMonthLength(new BigDecimal(24));
        offerParameters.setDefaultProposalTradeFee(new BigDecimal(12));

        DefaultZoneParams defaultZoneParams_A1 = new DefaultZoneParams();
        defaultZoneParams_A1.setActualZoneCode(STREFA_A1);
        defaultZoneParams_A1.setDefaultUnitPrice(new BigDecimal(150));

        DefaultZoneParams defaultZoneParams_C2 = new DefaultZoneParams();
        defaultZoneParams_C2.setActualZoneCode(STREFA_C2);
        defaultZoneParams_C2.setDefaultUnitPrice(new BigDecimal(250));

        offerParameters.setDefaultZoneParamsList(Arrays.asList(defaultZoneParams_A1, defaultZoneParams_C2));
        return offerParameters;
    }


    private List<ProposalSeller> generateProposalSellerList_ForPoint_1() {
        // Seller Tauron
        ProposalSeller proposalSeller_1 = new ProposalSeller();
        proposalSeller_1.setSellerCode("Tauron");
        proposalSeller_1.setProposalTradeFee(new BigDecimal(12));
        proposalSeller_1.setSellerTariffPublicationDate(new Date());

        ProposalZoneDetails proposalZoneDetailsCode_A1_TAURON = new ProposalZoneDetails();
        proposalZoneDetailsCode_A1_TAURON.setActualZoneCode(STREFA_A1);
        proposalZoneDetailsCode_A1_TAURON.setSellerMinimalUnitPrice(new BigDecimal(150));
        proposalZoneDetailsCode_A1_TAURON.setProposalUnitPrice(new BigDecimal(180));
        proposalZoneDetailsCode_A1_TAURON.setProposalZoneCode("Strefa A1 od Tauron");

        ProposalZoneDetails proposalZoneDetailsCode_C2_TAURON = new ProposalZoneDetails();
        proposalZoneDetailsCode_C2_TAURON.setActualZoneCode(STREFA_C2);
        proposalZoneDetailsCode_C2_TAURON.setSellerMinimalUnitPrice(new BigDecimal(150));
        proposalZoneDetailsCode_C2_TAURON.setProposalUnitPrice(new BigDecimal(280));
        proposalZoneDetailsCode_C2_TAURON.setProposalZoneCode("Strefa C2 od Tauron");
        proposalSeller_1.setProposalZoneDetailsList(Arrays.asList(proposalZoneDetailsCode_A1_TAURON, proposalZoneDetailsCode_C2_TAURON));

        proposalSeller_1.setReceiverPointEstimation(generateReceiverPointEstimationDTO_ForTauron());

        // Seller CEZ
        ProposalSeller proposalSeller_2 = new ProposalSeller();
        proposalSeller_2.setSellerCode("Cez");
        proposalSeller_2.setProposalTradeFee(new BigDecimal(13));
        proposalSeller_2.setSellerTariffPublicationDate(new Date());

        ProposalZoneDetails proposalZoneDetailsCode_A1_CEZ = new ProposalZoneDetails();
        proposalZoneDetailsCode_A1_CEZ.setActualZoneCode(STREFA_A1);
        proposalZoneDetailsCode_A1_CEZ.setSellerMinimalUnitPrice(new BigDecimal(110));
        proposalZoneDetailsCode_A1_CEZ.setProposalUnitPrice(new BigDecimal(175));
        proposalZoneDetailsCode_A1_CEZ.setProposalZoneCode("Strefa A1 od CEZ");

        ProposalZoneDetails proposalZoneDetailsCode_C2_CEZ = new ProposalZoneDetails();
        proposalZoneDetailsCode_C2_CEZ.setActualZoneCode(STREFA_C2);
        proposalZoneDetailsCode_C2_CEZ.setSellerMinimalUnitPrice(new BigDecimal(185));
        proposalZoneDetailsCode_C2_CEZ.setProposalUnitPrice(new BigDecimal(275));
        proposalZoneDetailsCode_C2_CEZ.setProposalZoneCode("Strefa C2 od CEZ");
        proposalSeller_2.setProposalZoneDetailsList(Arrays.asList(proposalZoneDetailsCode_A1_CEZ, proposalZoneDetailsCode_C2_CEZ));

        proposalSeller_2.setReceiverPointEstimation(generateReceiverPointEstimationDTO_ForCez());

        return Arrays.asList(proposalSeller_1, proposalSeller_2);
    }


    private ReceiverPointEstimation generateReceiverPointEstimationDTO_ForTauron() {
        //TAURON
        ReceiverPointEstimation receiverPointEstimation_TAURON =
                new ReceiverPointEstimation();
        receiverPointEstimation_TAURON.setSellerCode("Tauron");

        ReceiverPointDataEstimation receiverPointDataEstimation_TAURON = new ReceiverPointDataEstimation();
        receiverPointDataEstimation_TAURON.setTariffIssueDate(new Date());
        receiverPointDataEstimation_TAURON.setEstimatedUnitConsumptionInYearScale(new BigDecimal(99999));
        receiverPointDataEstimation_TAURON.setEstimatedContractProfitValue(new BigDecimal(5000));
        receiverPointDataEstimation_TAURON.setEstimatedContractProfitValueInYearScale(new BigDecimal(2500));
        receiverPointDataEstimation_TAURON.setEstimatedSavingsInYearScale(new BigDecimal(600));
        receiverPointDataEstimation_TAURON.setEstimatedSavingsInContractScale(new BigDecimal(1200));
        receiverPointDataEstimation_TAURON.setEstimatedSavingsInPercentage(new BigDecimal(30));

        receiverPointEstimation_TAURON.setReceiverPointDataEstimation(receiverPointDataEstimation_TAURON);

        ReceiverPointProvision receiverPointProvision_TAURON_1 = new ReceiverPointProvision();
        receiverPointProvision_TAURON_1.setLevelCode("Próg I");
        receiverPointProvision_TAURON_1.setTraderProvisionInYearScale(new BigDecimal(300));
        receiverPointProvision_TAURON_1.setTraderProvisionInContractScale(new BigDecimal(600));
        receiverPointProvision_TAURON_1.setPartnerProvisionInContractScale(new BigDecimal(150));

        ReceiverPointProvision receiverPointProvision_TAURON_2 = new ReceiverPointProvision();
        receiverPointProvision_TAURON_2.setLevelCode("Próg II");
        receiverPointProvision_TAURON_2.setTraderProvisionInYearScale(new BigDecimal(400));
        receiverPointProvision_TAURON_2.setTraderProvisionInContractScale(new BigDecimal(800));
        receiverPointProvision_TAURON_2.setPartnerProvisionInContractScale(new BigDecimal(200));

        ReceiverPointProvision receiverPointProvision_TAURON_3 = new ReceiverPointProvision();
        receiverPointProvision_TAURON_3.setLevelCode("Próg III");
        receiverPointProvision_TAURON_3.setTraderProvisionInYearScale(new BigDecimal(500));
        receiverPointProvision_TAURON_3.setTraderProvisionInContractScale(new BigDecimal(1000));
        receiverPointProvision_TAURON_3.setPartnerProvisionInContractScale(new BigDecimal(250));

        receiverPointEstimation_TAURON.setReceiverPointProvisionList(Arrays.asList(
                receiverPointProvision_TAURON_1, receiverPointProvision_TAURON_2, receiverPointProvision_TAURON_3
        ));
        //Cez
        ReceiverPointEstimation receiverPointEstimation_CEZ =
                new ReceiverPointEstimation();
        receiverPointEstimation_CEZ.setSellerCode("Cez");

        ReceiverPointDataEstimation receiverDataEstimationDTO_CEZ = new ReceiverPointDataEstimation();
        receiverDataEstimationDTO_CEZ.setTariffIssueDate(new Date());
        receiverDataEstimationDTO_CEZ.setEstimatedUnitConsumptionInYearScale(new BigDecimal(9999));
        receiverDataEstimationDTO_CEZ.setEstimatedUnitConsumptionInYearScale(new BigDecimal(9999));
        receiverDataEstimationDTO_CEZ.setEstimatedContractProfitValue(new BigDecimal(5000));
        receiverDataEstimationDTO_CEZ.setEstimatedContractProfitValueInYearScale(new BigDecimal(2500));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInYearScale(new BigDecimal(600));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInContractScale(new BigDecimal(1200));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInPercentage(new BigDecimal(30));

        receiverPointEstimation_CEZ.setReceiverPointDataEstimation(receiverDataEstimationDTO_CEZ);

        ReceiverPointProvision receiverPointProvision_CEZ_1 = new ReceiverPointProvision();
        receiverPointProvision_CEZ_1.setLevelCode("Próg I");
        receiverPointProvision_CEZ_1.setTraderProvisionInYearScale(new BigDecimal(350));
        receiverPointProvision_CEZ_1.setTraderProvisionInContractScale(new BigDecimal(700));
        receiverPointProvision_CEZ_1.setPartnerProvisionInContractScale(new BigDecimal(175));

        ReceiverPointProvision receiverPointProvision_CEZ_2 = new ReceiverPointProvision();
        receiverPointProvision_CEZ_2.setLevelCode("Próg II");
        receiverPointProvision_CEZ_2.setTraderProvisionInYearScale(new BigDecimal(450));
        receiverPointProvision_CEZ_2.setTraderProvisionInContractScale(new BigDecimal(900));
        receiverPointProvision_CEZ_2.setPartnerProvisionInContractScale(new BigDecimal(225));

        ReceiverPointProvision receiverPointProvision_CEZ_3 = new ReceiverPointProvision();
        receiverPointProvision_CEZ_3.setLevelCode("Próg III");
        receiverPointProvision_CEZ_3.setTraderProvisionInYearScale(new BigDecimal(550));
        receiverPointProvision_CEZ_3.setTraderProvisionInContractScale(new BigDecimal(1100));
        receiverPointProvision_CEZ_3.setPartnerProvisionInContractScale(new BigDecimal(275));

        receiverPointEstimation_CEZ.setReceiverPointProvisionList(Arrays.asList(
                receiverPointProvision_CEZ_1, receiverPointProvision_CEZ_2, receiverPointProvision_CEZ_3
        ));
        return receiverPointEstimation_TAURON;
    }

    private ReceiverPointEstimation generateReceiverPointEstimationDTO_ForCez() {
        //Cez
        ReceiverPointEstimation receiverPointEstimation_CEZ =
                new ReceiverPointEstimation();
        receiverPointEstimation_CEZ.setSellerCode("Cez");

        ReceiverPointDataEstimation receiverDataEstimationDTO_CEZ = new ReceiverPointDataEstimation();
        receiverDataEstimationDTO_CEZ.setTariffIssueDate(new Date());
        receiverDataEstimationDTO_CEZ.setEstimatedUnitConsumptionInYearScale(new BigDecimal(9999));
        receiverDataEstimationDTO_CEZ.setEstimatedUnitConsumptionInYearScale(new BigDecimal(9999));
        receiverDataEstimationDTO_CEZ.setEstimatedContractProfitValue(new BigDecimal(5000));
        receiverDataEstimationDTO_CEZ.setEstimatedContractProfitValueInYearScale(new BigDecimal(2500));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInYearScale(new BigDecimal(600));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInContractScale(new BigDecimal(1200));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInPercentage(new BigDecimal(30));

        receiverPointEstimation_CEZ.setReceiverPointDataEstimation(receiverDataEstimationDTO_CEZ);

        ReceiverPointProvision receiverPointProvision_CEZ_1 = new ReceiverPointProvision();
        receiverPointProvision_CEZ_1.setLevelCode("Próg I");
        receiverPointProvision_CEZ_1.setTraderProvisionInYearScale(new BigDecimal(350));
        receiverPointProvision_CEZ_1.setTraderProvisionInContractScale(new BigDecimal(700));
        receiverPointProvision_CEZ_1.setPartnerProvisionInContractScale(new BigDecimal(175));

        ReceiverPointProvision receiverPointProvision_CEZ_2 = new ReceiverPointProvision();
        receiverPointProvision_CEZ_2.setLevelCode("Próg II");
        receiverPointProvision_CEZ_2.setTraderProvisionInYearScale(new BigDecimal(450));
        receiverPointProvision_CEZ_2.setTraderProvisionInContractScale(new BigDecimal(900));
        receiverPointProvision_CEZ_2.setPartnerProvisionInContractScale(new BigDecimal(225));

        ReceiverPointProvision receiverPointProvision_CEZ_3 = new ReceiverPointProvision();
        receiverPointProvision_CEZ_3.setLevelCode("Próg III");
        receiverPointProvision_CEZ_3.setTraderProvisionInYearScale(new BigDecimal(550));
        receiverPointProvision_CEZ_3.setTraderProvisionInContractScale(new BigDecimal(1100));
        receiverPointProvision_CEZ_3.setPartnerProvisionInContractScale(new BigDecimal(275));

        receiverPointEstimation_CEZ.setReceiverPointProvisionList(Arrays.asList(
                receiverPointProvision_CEZ_1, receiverPointProvision_CEZ_2, receiverPointProvision_CEZ_3
        ));
        return receiverPointEstimation_CEZ;
    }

/*    // Receiver point 150
    private ReceiverPoint createReceiverPoint_2() {
        ReceiverPoint receiverPointDTO = new ReceiverPoint();
        receiverPointDTO.setReceiverPointDescription("Licznik numer 150");
        receiverPointDTO.setActualTradeFee(new BigDecimal(20));
        receiverPointDTO.setActualNumberOfTariffs(2);
        receiverPointDTO.setProposalNumberOfTariffs(2);
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
        tariffPeriodConsumptionDTO_A1_1.setPeriodStart(utilsService.getDateObjectForPattern("2016-08-01"));
        tariffPeriodConsumptionDTO_A1_1.setPeriodEnd(utilsService.getDateObjectForPattern("2016-09-09"));
        tariffPeriodConsumptionDTO_A1_1.setUnitConsumption(new BigDecimal(50));
        tariffPeriodConsumptionDTO_A1_1.setDocumentNumber("FA 485/08/2016");

        TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO_A1_2 = new TariffPeriodConsumptionDTO();
        tariffPeriodConsumptionDTO_A1_2.setPeriodStart(utilsService.getDateObjectForPattern("2016-07-01"));
        tariffPeriodConsumptionDTO_A1_2.setPeriodEnd(utilsService.getDateObjectForPattern("2016-10-01"));
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
        tariffPeriodConsumptionDTO_C2_1.setPeriodStart(utilsService.getDateObjectForPattern("2016-09-08"));
        tariffPeriodConsumptionDTO_C2_1.setPeriodEnd(utilsService.getDateObjectForPattern("2016-11-01"));
        tariffPeriodConsumptionDTO_C2_1.setUnitConsumption(new BigDecimal(100));
        tariffPeriodConsumptionDTO_C2_1.setDocumentNumber("FA 512265656565222/08/2016");

        TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO_C2_2 = new TariffPeriodConsumptionDTO();
        tariffPeriodConsumptionDTO_C2_2.setPeriodStart(utilsService.getDateObjectForPattern("2016-08-01"));
        tariffPeriodConsumptionDTO_C2_2.setPeriodEnd(utilsService.getDateObjectForPattern("2016-10-01"));
        tariffPeriodConsumptionDTO_C2_2.setUnitConsumption(new BigDecimal(600));
        tariffPeriodConsumptionDTO_C2_2.setDocumentNumber("FA 566666688484848455/08/2016");

        actualTariff_2.setTariffPeriodConsumptionDTOList(Arrays.asList(tariffPeriodConsumptionDTO_C2_1,
                tariffPeriodConsumptionDTO_C2_2));

        return Arrays.asList(actualTariff_1, actualTariff_2);
    }

    private List<ProposalSeller> createProposalSellerList_ForPoint_2() {
        // Seller Tauron
        ProposalSeller proposalSeller_1 = new ProposalSeller();
        proposalSeller_1.setSellerCode("Tauron");
        proposalSeller_1.setProposalTradeFee(new BigDecimal(16));
        proposalSeller_1.setSellerTariffPublicationDate(new Date());

        ProposalZoneDetails proposalTariff_A_TAURON = new ProposalZoneDetails();
        proposalTariff_A_TAURON.setActualZoneCode("Taryfa E");
        proposalTariff_A_TAURON.setSellerMinimalUnitPrice(new BigDecimal(380));
        proposalTariff_A_TAURON.setProposalUnitPrice(new BigDecimal(450));
        proposalTariff_A_TAURON.setProposalZoneCode("Taryfa A od Tauron");

        ProposalZoneDetails proposalTariff_B_TAURON = new ProposalZoneDetails();
        proposalTariff_B_TAURON.setActualZoneCode("Taryfa F");
        proposalTariff_B_TAURON.setSellerMinimalUnitPrice(new BigDecimal(220));
        proposalTariff_B_TAURON.setProposalUnitPrice(new BigDecimal(380));
        proposalTariff_B_TAURON.setProposalZoneCode("Taryfa B od Tauron");
        proposalSeller_1.setProposalZoneDetailsList(Arrays.asList(proposalTariff_A_TAURON, proposalTariff_B_TAURON));

        // Seller CEZ
        ProposalSeller proposalSeller_2 = new ProposalSeller();
        proposalSeller_2.setSellerCode("Cez");
        proposalSeller_2.setProposalTradeFee(new BigDecimal(15));
        proposalSeller_2.setSellerTariffPublicationDate(new Date());

        ProposalZoneDetails proposalTariff_A_CEZ = new ProposalZoneDetails();
        proposalTariff_A_CEZ.setActualZoneCode("Taryfa E");
        proposalTariff_A_CEZ.setSellerMinimalUnitPrice(new BigDecimal(320));
        proposalTariff_A_CEZ.setProposalUnitPrice(new BigDecimal(410));
        proposalTariff_A_CEZ.setProposalZoneCode("Taryfa E od CEZ");

        ProposalZoneDetails proposalTariff_B_CEZ = new ProposalZoneDetails();
        proposalTariff_B_CEZ.setActualZoneCode("Taryfa F");
        proposalTariff_B_CEZ.setSellerMinimalUnitPrice(new BigDecimal(280));
        proposalTariff_B_CEZ.setProposalUnitPrice(new BigDecimal(390));
        proposalTariff_B_CEZ.setProposalZoneCode("Taryfa F od CEZ");
        proposalSeller_2.setProposalZoneDetailsList(Arrays.asList(proposalTariff_A_CEZ, proposalTariff_B_CEZ));

        return Arrays.asList(proposalSeller_1, proposalSeller_2);

    }


    private TotalConsumptionSummary createReceiverPointConsumptionSummaryDTO_ForPoint_2() {
        TotalConsumptionSummary totalConsumptionSummaryDTO =
                new TotalConsumptionSummary();
        totalConsumptionSummaryDTO.setTotalNumberOfDaysForAllPeriods(80);
        totalConsumptionSummaryDTO.setTotalElectricityUnitsConsumptionInAllPeriods(new BigDecimal(1000));
        totalConsumptionSummaryDTO.setPredictedElectricityUnitConsumptionPerYear(new BigDecimal(6000));
        return totalConsumptionSummaryDTO;
    }

    private List<ReceiverPointEstimation> createReceiverPointEstimationDTO_ForPoint_2() {
        //TAURON
        ReceiverPointEstimation receiverPointEstimationDTO_TAURON =
                new ReceiverPointEstimation();
        receiverPointEstimationDTO_TAURON.setSellerCode("Tauron");

        ReceiverPointDataEstimation receiverPointDataEstimationDTO_TAURON = new ReceiverPointDataEstimation();
        receiverPointDataEstimationDTO_TAURON.setTariffIssueDate(new Date());
        receiverPointDataEstimationDTO_TAURON.setEstimatedUnitConsumptionInYearScale(new BigDecimal(9999));
        receiverPointDataEstimationDTO_TAURON.setEstimatedContractProfitValue(new BigDecimal(5000));
        receiverPointDataEstimationDTO_TAURON.setEstimatedContractProfitValueInYearScale(new BigDecimal(2500));
        receiverPointDataEstimationDTO_TAURON.setEstimatedSavingsInYearScale(new BigDecimal(600));
        receiverPointDataEstimationDTO_TAURON.setEstimatedSavingsInContractScale(new BigDecimal(1200));
        receiverPointDataEstimationDTO_TAURON.setEstimatedSavingsInPercentage(new BigDecimal(30));

        receiverPointEstimationDTO_TAURON.setReceiverPointDataEstimation(receiverPointDataEstimationDTO_TAURON);

        ReceiverPointProvision receiverPointProvisionDTO_TAURON_1 = new ReceiverPointProvision();
        receiverPointProvisionDTO_TAURON_1.setLevelCode("Próg I");
        receiverPointProvisionDTO_TAURON_1.setTraderProvisionInYearScale(new BigDecimal(300));
        receiverPointProvisionDTO_TAURON_1.setTraderProvisionInContractScale(new BigDecimal(600));
        receiverPointProvisionDTO_TAURON_1.setPartnerProvisionInContractScale(new BigDecimal(150));

        ReceiverPointProvision receiverPointProvisionDTO_TAURON_2 = new ReceiverPointProvision();
        receiverPointProvisionDTO_TAURON_2.setLevelCode("Próg II");
        receiverPointProvisionDTO_TAURON_2.setTraderProvisionInYearScale(new BigDecimal(400));
        receiverPointProvisionDTO_TAURON_2.setTraderProvisionInContractScale(new BigDecimal(800));
        receiverPointProvisionDTO_TAURON_2.setPartnerProvisionInContractScale(new BigDecimal(200));

        ReceiverPointProvision receiverPointProvisionDTO_TAURON_3 = new ReceiverPointProvision();
        receiverPointProvisionDTO_TAURON_3.setLevelCode("Próg III");
        receiverPointProvisionDTO_TAURON_3.setTraderProvisionInYearScale(new BigDecimal(500));
        receiverPointProvisionDTO_TAURON_3.setTraderProvisionInContractScale(new BigDecimal(1000));
        receiverPointProvisionDTO_TAURON_3.setPartnerProvisionInContractScale(new BigDecimal(250));

        receiverPointEstimationDTO_TAURON.setReceiverPointProvisionList(Arrays.asList(
                receiverPointProvisionDTO_TAURON_1, receiverPointProvisionDTO_TAURON_2, receiverPointProvisionDTO_TAURON_3
        ));
        //Cez
        ReceiverPointEstimation receiverPointEstimationDTO_CEZ =
                new ReceiverPointEstimation();
        receiverPointEstimationDTO_CEZ.setSellerCode("Cez");

        ReceiverPointDataEstimation receiverDataEstimationDTO_CEZ = new ReceiverPointDataEstimation();
        receiverDataEstimationDTO_CEZ.setTariffIssueDate(new Date());
        receiverDataEstimationDTO_CEZ.setEstimatedUnitConsumptionInYearScale(new BigDecimal(9999));
        receiverDataEstimationDTO_CEZ.setEstimatedContractProfitValue(new BigDecimal(5000));
        receiverDataEstimationDTO_CEZ.setEstimatedContractProfitValueInYearScale(new BigDecimal(2500));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInYearScale(new BigDecimal(600));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInContractScale(new BigDecimal(1200));
        receiverDataEstimationDTO_CEZ.setEstimatedSavingsInPercentage(new BigDecimal(30));

        receiverPointEstimationDTO_CEZ.setReceiverPointDataEstimation(receiverDataEstimationDTO_CEZ);

        ReceiverPointProvision receiverPointProvisionDTO_CEZ_1 = new ReceiverPointProvision();
        receiverPointProvisionDTO_CEZ_1.setLevelCode("Próg I");
        receiverPointProvisionDTO_CEZ_1.setTraderProvisionInYearScale(new BigDecimal(350));
        receiverPointProvisionDTO_CEZ_1.setTraderProvisionInContractScale(new BigDecimal(700));
        receiverPointProvisionDTO_CEZ_1.setPartnerProvisionInContractScale(new BigDecimal(175));

        ReceiverPointProvision receiverPointProvisionDTO_CEZ_2 = new ReceiverPointProvision();
        receiverPointProvisionDTO_CEZ_2.setLevelCode("Próg II");
        receiverPointProvisionDTO_CEZ_2.setTraderProvisionInYearScale(new BigDecimal(450));
        receiverPointProvisionDTO_CEZ_2.setTraderProvisionInContractScale(new BigDecimal(900));
        receiverPointProvisionDTO_CEZ_2.setPartnerProvisionInContractScale(new BigDecimal(225));

        ReceiverPointProvision receiverPointProvisionDTO_CEZ_3 = new ReceiverPointProvision();
        receiverPointProvisionDTO_CEZ_3.setLevelCode("Próg III");
        receiverPointProvisionDTO_CEZ_3.setTraderProvisionInYearScale(new BigDecimal(550));
        receiverPointProvisionDTO_CEZ_3.setTraderProvisionInContractScale(new BigDecimal(1100));
        receiverPointProvisionDTO_CEZ_3.setPartnerProvisionInContractScale(new BigDecimal(275));

        receiverPointEstimationDTO_CEZ.setReceiverPointProvisionList(Arrays.asList(
                receiverPointProvisionDTO_CEZ_1, receiverPointProvisionDTO_CEZ_2, receiverPointProvisionDTO_CEZ_3
        ));
        return Arrays.asList(receiverPointEstimationDTO_TAURON, receiverPointEstimationDTO_CEZ);
    }*/


    // Summary

    private OfferSummaryDTO createOfferSummaryDTO() {
        OfferSummaryDTO offerSummaryDTO = new OfferSummaryDTO();
        //TAURON
        AllReceiverPointsEstimationForSeller allReceiverPointsEstimationForSeller_TAURON = new AllReceiverPointsEstimationForSeller();
        allReceiverPointsEstimationForSeller_TAURON.setSellerCode("TAURON");

        AllReceiverPointsDataEstimationForSeller allReceiverPointsDataEstimationForSeller_TAURON =
                new AllReceiverPointsDataEstimationForSeller();
        allReceiverPointsDataEstimationForSeller_TAURON.setTariffIssueDate(new Date());
        allReceiverPointsDataEstimationForSeller_TAURON.setEstimatedContractValueForAllPoint(new BigDecimal(5000));
        allReceiverPointsDataEstimationForSeller_TAURON.setEstimatedContractValueInYearScaleForAllPoint(new BigDecimal(2500));
        allReceiverPointsDataEstimationForSeller_TAURON.setEstimatedSavingsInYearScaleForAllPoint(new BigDecimal(600));
        allReceiverPointsDataEstimationForSeller_TAURON.setEstimatedSavingsInContractScaleForAllPoint(new BigDecimal(1200));
        allReceiverPointsDataEstimationForSeller_TAURON.setEstimatedSavingsInPercentageForAllPoint(new BigDecimal(30));

        allReceiverPointsEstimationForSeller_TAURON.setAllReceiverPointsDataEstimationForSeller(
                allReceiverPointsDataEstimationForSeller_TAURON);

        AllReceiverPointsProvisionForSeller allReceiverPointsProvisionForSeller_TAURON_1 = new AllReceiverPointsProvisionForSeller();
        allReceiverPointsProvisionForSeller_TAURON_1.setLevelCode("Próg I");
        allReceiverPointsProvisionForSeller_TAURON_1.setTraderProvisionInYearScaleForAllPoint(new BigDecimal(300));
        allReceiverPointsProvisionForSeller_TAURON_1.setTraderProvisionInContractScaleForAllPoint(new BigDecimal(600));
        allReceiverPointsProvisionForSeller_TAURON_1.setPartnerProvisionInContractScaleForAllPoint(new BigDecimal(150));


        AllReceiverPointsProvisionForSeller allReceiverPointsProvisionForSeller_TAURON_2 = new AllReceiverPointsProvisionForSeller();
        allReceiverPointsProvisionForSeller_TAURON_2.setLevelCode("Próg II");
        allReceiverPointsProvisionForSeller_TAURON_2.setTraderProvisionInYearScaleForAllPoint(new BigDecimal(400));
        allReceiverPointsProvisionForSeller_TAURON_2.setTraderProvisionInContractScaleForAllPoint(new BigDecimal(800));
        allReceiverPointsProvisionForSeller_TAURON_2.setPartnerProvisionInContractScaleForAllPoint(new BigDecimal(200));

        AllReceiverPointsProvisionForSeller allReceiverPointsProvisionForSeller_TAURON_3 = new AllReceiverPointsProvisionForSeller();
        allReceiverPointsProvisionForSeller_TAURON_3.setLevelCode("Próg III");
        allReceiverPointsProvisionForSeller_TAURON_3.setTraderProvisionInYearScaleForAllPoint(new BigDecimal(500));
        allReceiverPointsProvisionForSeller_TAURON_3.setTraderProvisionInContractScaleForAllPoint(new BigDecimal(1000));
        allReceiverPointsProvisionForSeller_TAURON_3.setPartnerProvisionInContractScaleForAllPoint(new BigDecimal(250));

        allReceiverPointsEstimationForSeller_TAURON.setAllReceiverPointsProvisionForSellerList(Arrays.asList(
                allReceiverPointsProvisionForSeller_TAURON_1, allReceiverPointsProvisionForSeller_TAURON_2, allReceiverPointsProvisionForSeller_TAURON_3));
        //CEZ
        AllReceiverPointsEstimationForSeller allReceiverPointsEstimationForSeller_CEZ = new AllReceiverPointsEstimationForSeller();
        allReceiverPointsEstimationForSeller_CEZ.setSellerCode("CEZ");

        AllReceiverPointsDataEstimationForSeller allReceiverPointsDataEstimationForSeller_CEZ =
                new AllReceiverPointsDataEstimationForSeller();
        allReceiverPointsDataEstimationForSeller_CEZ.setTariffIssueDate(new Date());
        allReceiverPointsDataEstimationForSeller_CEZ.setEstimatedContractValueForAllPoint(new BigDecimal(2500));
        allReceiverPointsDataEstimationForSeller_CEZ.setEstimatedContractValueInYearScaleForAllPoint(new BigDecimal(1250));
        allReceiverPointsDataEstimationForSeller_CEZ.setEstimatedSavingsInYearScaleForAllPoint(new BigDecimal(300));
        allReceiverPointsDataEstimationForSeller_CEZ.setEstimatedSavingsInContractScaleForAllPoint(new BigDecimal(600));
        allReceiverPointsDataEstimationForSeller_CEZ.setEstimatedSavingsInPercentageForAllPoint(new BigDecimal(30));

        allReceiverPointsEstimationForSeller_CEZ.setAllReceiverPointsDataEstimationForSeller(
                allReceiverPointsDataEstimationForSeller_CEZ);

        AllReceiverPointsProvisionForSeller allReceiverPointsProvisionForSeller_CEZ_1 = new AllReceiverPointsProvisionForSeller();
        allReceiverPointsProvisionForSeller_CEZ_1.setLevelCode("Próg I");
        allReceiverPointsProvisionForSeller_CEZ_1.setTraderProvisionInYearScaleForAllPoint(new BigDecimal(350));
        allReceiverPointsProvisionForSeller_CEZ_1.setTraderProvisionInContractScaleForAllPoint(new BigDecimal(700));
        allReceiverPointsProvisionForSeller_CEZ_1.setPartnerProvisionInContractScaleForAllPoint(new BigDecimal(175));


        AllReceiverPointsProvisionForSeller allReceiverPointsProvisionForSeller_CEZ_2 = new AllReceiverPointsProvisionForSeller();
        allReceiverPointsProvisionForSeller_CEZ_2.setLevelCode("Próg II");
        allReceiverPointsProvisionForSeller_CEZ_2.setTraderProvisionInYearScaleForAllPoint(new BigDecimal(450));
        allReceiverPointsProvisionForSeller_CEZ_2.setTraderProvisionInContractScaleForAllPoint(new BigDecimal(900));
        allReceiverPointsProvisionForSeller_CEZ_2.setPartnerProvisionInContractScaleForAllPoint(new BigDecimal(225));

        AllReceiverPointsProvisionForSeller allReceiverPointsProvisionForSeller_CEZ_3 = new AllReceiverPointsProvisionForSeller();
        allReceiverPointsProvisionForSeller_CEZ_3.setLevelCode("Próg III");
        allReceiverPointsProvisionForSeller_CEZ_3.setTraderProvisionInYearScaleForAllPoint(new BigDecimal(550));
        allReceiverPointsProvisionForSeller_CEZ_3.setTraderProvisionInContractScaleForAllPoint(new BigDecimal(1100));
        allReceiverPointsProvisionForSeller_CEZ_3.setPartnerProvisionInContractScaleForAllPoint(new BigDecimal(275));

        allReceiverPointsEstimationForSeller_CEZ.setAllReceiverPointsProvisionForSellerList(Arrays.asList(
                allReceiverPointsProvisionForSeller_CEZ_1, allReceiverPointsProvisionForSeller_CEZ_2, allReceiverPointsProvisionForSeller_CEZ_3));

        offerSummaryDTO.setReceiverPointEstimationList(Arrays.asList(
                allReceiverPointsEstimationForSeller_TAURON, allReceiverPointsEstimationForSeller_CEZ));
        return offerSummaryDTO;

    }
}
