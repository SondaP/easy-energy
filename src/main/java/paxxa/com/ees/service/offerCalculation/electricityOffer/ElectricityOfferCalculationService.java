package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRootDTO;
import paxxa.com.ees.service.utils.UtilsService;

@Service
public class ElectricityOfferCalculationService {

    @Autowired
    private UtilsService utilsService;
    @Autowired
    private ElectricityOfferValidationService electricityOfferValidationService;

   public ElectricityOfferRootDTO calculateElectricityOffer(ElectricityOfferRootDTO electricityOfferRootDTO, String userName) {
       /* BigDecimal proposalContractMonthLength = new BigDecimal(electricityOfferRootDTO.getProposalContractMonthLength());
        calculateReceiversPoint(electricityOfferRootDTO.getReceiverPointList(), proposalContractMonthLength);

        return electricityOfferRootDTO;*/

       return null;
    }

     /*

    private void calculateReceiversPoint(List<ReceiverPoint> receiverPointDTOList, BigDecimal proposalContractMonthLength) {
        for (ReceiverPoint receiverPointDTO : receiverPointDTOList) {

            List<ActualTariff> actualTariffList = receiverPointDTO.getActualTariffList();
            String receiverPointDescription = receiverPointDTO.getReceiverPointDescription();
            // Validation section
            electricityOfferValidationService.validateReceiverPoint(receiverPointDTO);
            electricityOfferValidationService.validateNumberOfActualTariffs(receiverPointDTO.getActualNumberOfTariffs(), actualTariffList,
                    receiverPointDescription);
            electricityOfferValidationService.validateTariffPeriodsConsumptions(actualTariffList, receiverPointDescription);

            electricityOfferValidationService.validateProposalSellers(receiverPointDTO.getProposalSellerList(),
                    receiverPointDescription, receiverPointDTO.getProposalNumberOfTariffs());
            electricityOfferValidationService.validateProposalTariffs(receiverPointDTO.getProposalSellerList(),
                    receiverPointDescription, receiverPointDTO.getProposalNumberOfTariffs(), getActualTariffsCodes(actualTariffList));

            // Setting TotalConsumptionSummary
            setReceiverPointConsumptionSummaryDTO(actualTariffList, receiverPointDescription, receiverPointDTO);
            setActualTariffsDetails(actualTariffList,
                    receiverPointDTO.getReceiverPointConsumptionSummaryDTO().getTotalElectricityUnitsConsumptionInAllPeriods());

            // Setting ReceiverPointEstimation
            List<ProposalSeller> proposalSellerList = receiverPointDTO.getProposalSellerList();
            List<ReceiverPointEstimation> receiverPointEstimationDTOs =
                    generateReceiverPointEstimationList(proposalSellerList, actualTariffList,
                            receiverPointDTO.getReceiverPointConsumptionSummaryDTO(),
                            proposalContractMonthLength, receiverPointDTO.getActualTradeFee());

            receiverPointDTO.setReceiverPointEstimationList(receiverPointEstimationDTOs);

        }
    }

    *//**
     * Setting TotalConsumptionSummary
     *//*
    private void setReceiverPointConsumptionSummaryDTO(List<ActualTariff> actualTariffList, String receiverPointDescription,
                                                       ReceiverPoint receiverPointDTO) {
        // Variables
        Integer totalDaysNumberForPeriods = calculateTotalDaysNumberForPeriods(actualTariffList, receiverPointDescription);
        BigDecimal totalElectricityConsumptionUnitsForReceiverPoint = calculateTotalConsumptionUnits(actualTariffList, receiverPointDescription);
        BigDecimal predictedElectricityUnitConsumptionPerYear = calculatePredictedElectricityUnitConsumptionPerYear(
                new BigDecimal(totalDaysNumberForPeriods), totalElectricityConsumptionUnitsForReceiverPoint);
        // Setting variables
        TotalConsumptionSummary totalConsumptionSummaryDTO = new TotalConsumptionSummary();
        totalConsumptionSummaryDTO.setTotalNumberOfDaysForAllPeriods(totalDaysNumberForPeriods);
        totalConsumptionSummaryDTO.setTotalElectricityUnitsConsumptionInAllPeriods(totalElectricityConsumptionUnitsForReceiverPoint);
        totalConsumptionSummaryDTO.setPredictedElectricityUnitConsumptionPerYear(predictedElectricityUnitConsumptionPerYear);

        receiverPointDTO.setReceiverPointConsumptionSummaryDTO(totalConsumptionSummaryDTO);
    }

    private BigDecimal calculateTotalConsumptionUnits(List<ActualTariff> actualTariffList, String receiverPointDescription) {
        BigDecimal totalConsumptionUnits = BigDecimal.ZERO;
        for (ActualTariff actualTariff : actualTariffList) {
            List<TariffPeriodConsumptionDTO> tariffPeriodConsumptionDTOList =
                    actualTariff.getTariffPeriodConsumptionDTOList();

            for (TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO : tariffPeriodConsumptionDTOList) {
                BigDecimal unitConsumption = tariffPeriodConsumptionDTO.getUnitConsumption();
                totalConsumptionUnits = totalConsumptionUnits.add(unitConsumption);
            }
        }
        return totalConsumptionUnits;
    }

    private void setActualTariffsDetails(List<ActualTariff> actualTariffList, BigDecimal totalElectricityConsumptionUnitsForReceiverPoint) {
        for (ActualTariff actualTariff : actualTariffList) {
            BigDecimal tariffConsumptionUnits = BigDecimal.ZERO;
            List<TariffPeriodConsumptionDTO> tariffPeriodConsumptionDTOList =
                    actualTariff.getTariffPeriodConsumptionDTOList();
            for (TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO : tariffPeriodConsumptionDTOList) {
                BigDecimal unitConsumption = tariffPeriodConsumptionDTO.getUnitConsumption();
                tariffConsumptionUnits = tariffConsumptionUnits.add(unitConsumption);
            }

            BigDecimal actualShareOfTotalReceiverPointConsumption = tariffConsumptionUnits
                    .divide(totalElectricityConsumptionUnitsForReceiverPoint, 2, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(100));

            actualTariff.setTotalUnitConsumptionFromPeriods(tariffConsumptionUnits);
            actualTariff.setActualShareOfTotalReceiverPointConsumption(actualShareOfTotalReceiverPointConsumption);
        }
    }

    private BigDecimal calculatePredictedElectricityUnitConsumptionPerYear(
            BigDecimal totalDaysNumberForPeriods, BigDecimal totalElectricityConsumptionUnitsForReceiverPoint) {
        return totalElectricityConsumptionUnitsForReceiverPoint
                .divide(totalDaysNumberForPeriods, 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(365));
    }


    private Integer calculateTotalDaysNumberForPeriods(List<ActualTariff> actualTariffList, String receiverPointDescription) {
        Integer totalNumberOfDays = 0;
        for (ActualTariff actualTariff : actualTariffList) {
            List<TariffPeriodConsumptionDTO> tariffPeriodConsumptionDTOList =
                    actualTariff.getTariffPeriodConsumptionDTOList();

            for (TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO : tariffPeriodConsumptionDTOList) {

                Integer differenceDays = utilsService.countDaysBetweenTwoDates(
                        tariffPeriodConsumptionDTO.getPeriodStart(), tariffPeriodConsumptionDTO.getPeriodEnd());
                totalNumberOfDays = totalNumberOfDays + differenceDays;
            }
        }
        return totalNumberOfDays;
    }

    private List<String> getActualTariffsCodes(List<ActualTariff> actualTariffList) {
        return actualTariffList.stream().map(ActualTariff::getActualTariffCode).collect(Collectors.toList());
    }

    *//**
     * Setting ReceiverPointEstimation
     *//*

    private List<ReceiverPointEstimation> generateReceiverPointEstimationList(List<ProposalSeller> proposalSellerList,
                                                                                 List<ActualTariff> actualTariffList,
                                                                                 TotalConsumptionSummary totalConsumptionSummaryDTO,
                                                                                 BigDecimal proposalContractMonthLength,
                                                                                 BigDecimal actualTradeFee) {
        List<ReceiverPointEstimation> receiverPointEstimationDTOs = new ArrayList<>();
        for (ProposalSeller proposalSeller : proposalSellerList) {
            ReceiverPointEstimation receiverPointEstimationDTO = generateReceiverPointEstimation(proposalSeller,
                    actualTariffList, totalConsumptionSummaryDTO, proposalContractMonthLength, actualTradeFee);
            receiverPointEstimationDTOs.add(receiverPointEstimationDTO);
        }
        return receiverPointEstimationDTOs;
    }

    private ReceiverPointEstimation generateReceiverPointEstimation(ProposalSeller proposalSeller,
                                                                       List<ActualTariff> actualTariffList,
                                                                       TotalConsumptionSummary totalConsumptionSummaryDTO,
                                                                       BigDecimal proposalContractMonthLength,
                                                                       BigDecimal actualTradeFee) {
        ReceiverPointEstimation receiverPointEstimationDTO = new ReceiverPointEstimation();
        receiverPointEstimationDTO.setSellerCode(proposalSeller.getSellerCode());

        // Setting estimation
        ReceiverPointDataEstimation receiverPointDataEstimationDTO = new ReceiverPointDataEstimation();
        BigDecimal estimatedUnitConsumptionInYearScale = calculateEstimatedUnitConsumptionInYearScale(totalConsumptionSummaryDTO);
        BigDecimal estimatedContractProfitValueInYearScale = calculateEstimatedContractProfitValueInYearScale(proposalSeller, actualTariffList,
                totalConsumptionSummaryDTO);
        BigDecimal estimatedContractProfitValue = calculateEstimatedContractProfitValue(estimatedContractProfitValueInYearScale, proposalContractMonthLength);

        BigDecimal estimatedSavingsInYearScale = calculateEstimatedSavingsInYearScale(proposalSeller, actualTariffList,
                totalConsumptionSummaryDTO, actualTradeFee);

        receiverPointDataEstimationDTO.setTariffIssueDate(proposalSeller.getSellerTariffPublicationDate());
        receiverPointDataEstimationDTO.setEstimatedUnitConsumptionInYearScale(estimatedUnitConsumptionInYearScale);
        receiverPointDataEstimationDTO.setEstimatedContractProfitValueInYearScale(estimatedContractProfitValueInYearScale);
        receiverPointDataEstimationDTO.setEstimatedContractProfitValue(estimatedContractProfitValue);
        receiverPointDataEstimationDTO.setEstimatedSavingsInYearScale(estimatedSavingsInYearScale);


        receiverPointEstimationDTO.setReceiverPointDataEstimation(receiverPointDataEstimationDTO);
        return receiverPointEstimationDTO;
    }

    private BigDecimal calculateEstimatedSavingsInYearScale(ProposalSeller proposalSeller,
                                                            List<ActualTariff> actualTariffList,
                                                            TotalConsumptionSummary totalConsumptionSummaryDTO,
                                                            BigDecimal actualTradeFee) {

        BigDecimal totalActualCostForAllUnitsConsumption = BigDecimal.ZERO;
        for (ProposalTariff proposalTariff : proposalSeller.getProposalTariffList()) {
            BigDecimal totalElectricityUnitConsumptionForActualTariff =
                    getTotalElectricityUnitConsumptionForActualTariff(actualTariffList, proposalTariff.getActualZoneCode());
            BigDecimal actualUnitPriceForActualTariff = getActualUnitPriceForActualTariff(actualTariffList, proposalTariff.getActualZoneCode());

            BigDecimal total = totalElectricityUnitConsumptionForActualTariff
                    .divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP)
                    .multiply(actualUnitPriceForActualTariff);

            totalActualCostForAllUnitsConsumption = totalActualCostForAllUnitsConsumption.add(total);
        }
        totalActualCostForAllUnitsConsumption = totalActualCostForAllUnitsConsumption.add(actualTradeFee);

        BigDecimal totalCostForAllUnitsConsumptionBasedOnProposal = BigDecimal.ZERO;
        for (ProposalTariff proposalTariff : proposalSeller.getProposalTariffList()) {
            BigDecimal totalElectricityUnitConsumptionForActualTariff =
                    getTotalElectricityUnitConsumptionForActualTariff(actualTariffList, proposalTariff.getActualZoneCode());
            BigDecimal proposalUnitPrice = proposalTariff.getProposalUnitPrice();

            BigDecimal total = totalElectricityUnitConsumptionForActualTariff
                    .divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP)
                    .multiply(proposalUnitPrice);

            totalCostForAllUnitsConsumptionBasedOnProposal = totalCostForAllUnitsConsumptionBasedOnProposal.add(total);
        }
        totalCostForAllUnitsConsumptionBasedOnProposal = totalCostForAllUnitsConsumptionBasedOnProposal.add(actualTradeFee);

        BigDecimal totalNumberOfDaysForAllPeriods = new BigDecimal(totalConsumptionSummaryDTO.getTotalNumberOfDaysForAllPeriods());
        return totalActualCostForAllUnitsConsumption
                .subtract(totalCostForAllUnitsConsumptionBasedOnProposal)
                .divide(totalNumberOfDaysForAllPeriods, 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(365));
    }

    private BigDecimal calculateEstimatedUnitConsumptionInYearScale(TotalConsumptionSummary totalConsumptionSummaryDTO) {
        BigDecimal totalElectricityUnitsConsumptionInAllPeriods = totalConsumptionSummaryDTO.getTotalElectricityUnitsConsumptionInAllPeriods();
        BigDecimal totalNumberOfDaysForAllPeriods = new BigDecimal(totalConsumptionSummaryDTO.getTotalNumberOfDaysForAllPeriods());
        return totalElectricityUnitsConsumptionInAllPeriods
                .divide(totalNumberOfDaysForAllPeriods, 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(365));
    }

    private BigDecimal calculateEstimatedContractProfitValue(BigDecimal estimatedContractProfitValueInYearScale, BigDecimal proposalContractMonthLength) {
        return estimatedContractProfitValueInYearScale
                .divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP)
                .multiply(proposalContractMonthLength);
    }

    private BigDecimal calculateEstimatedContractProfitValueInYearScale(ProposalSeller proposalSeller,
                                                                        List<ActualTariff> actualTariffList,
                                                                        TotalConsumptionSummary totalConsumptionSummaryDTO) {
        BigDecimal profitForAllTariffs = BigDecimal.ZERO;
        for (ProposalTariff proposalTariff : proposalSeller.getProposalTariffList()) {
            BigDecimal marginForUnitPrice = proposalTariff.getProposalUnitPrice().subtract(proposalTariff.getSellerMinimalUnitPrice());
            BigDecimal totalElectricityUnitConsumptionForActualTariff =
                    getTotalElectricityUnitConsumptionForActualTariff(actualTariffList, proposalTariff.getActualZoneCode());
            BigDecimal tariffProfit = marginForUnitPrice
                    .multiply(totalElectricityUnitConsumptionForActualTariff);

            profitForAllTariffs = profitForAllTariffs.add(tariffProfit.divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP));
        }
        Integer totalNumberOfDaysForAllPeriods = totalConsumptionSummaryDTO.getTotalNumberOfDaysForAllPeriods();
        BigDecimal estimatedContractValueInYearScale = profitForAllTariffs
                .divide(new BigDecimal(totalNumberOfDaysForAllPeriods), 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(365));
        return estimatedContractValueInYearScale;
    }

    private BigDecimal getTotalElectricityUnitConsumptionForActualTariff(List<ActualTariff> actualTariffList, String expectedActualTariffCode) {
        for (ActualTariff actualTariff : actualTariffList) {
            if (expectedActualTariffCode.equals(actualTariff.getActualTariffCode())){
                return actualTariff.getTotalUnitConsumptionFromPeriods();
            }
        }
        throw new RuntimeException("Unexpected situation, actual tariff should contain TotalElectricityUnitConsumptionForTariff");
    }

    private BigDecimal getActualUnitPriceForActualTariff(List<ActualTariff> actualTariffList, String expectedActualTariffCode) {
        for (ActualTariff actualTariff : actualTariffList) {
            if (expectedActualTariffCode.equals(actualTariff.getActualTariffCode())){
                return actualTariff.getActualUnitPrice();
            }
        }
        throw new RuntimeException("Unexpected situation, actual tariff should contain ActualUnitPriceForActualTariff");
    }*/


}
