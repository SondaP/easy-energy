package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRootDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.*;
import paxxa.com.ees.service.utils.UtilsService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectricityOfferCalculationService {

    @Autowired
    private UtilsService utilsService;
    @Autowired
    private ElectricityOfferValidationService electricityOfferValidationService;

    public ElectricityOfferRootDTO calculateElectricityOffer(ElectricityOfferRootDTO electricityOfferRootDTO, String userName) {
        BigDecimal proposalContractMonthLength = new BigDecimal(electricityOfferRootDTO.getProposalContractMonthLength());
        calculateReceiversPoint(electricityOfferRootDTO.getReceiverPointDTOList(), proposalContractMonthLength);

        return electricityOfferRootDTO;
    }

    private void calculateReceiversPoint(List<ReceiverPointDTO> receiverPointDTOList, BigDecimal proposalContractMonthLength) {
        for (ReceiverPointDTO receiverPointDTO : receiverPointDTOList) {

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

            // Setting ReceiverPointConsumptionSummaryDTO
            setReceiverPointConsumptionSummaryDTO(actualTariffList, receiverPointDescription, receiverPointDTO);
            setActualTariffsDetails(actualTariffList,
                    receiverPointDTO.getReceiverPointConsumptionSummaryDTO().getTotalElectricityUnitsConsumptionInAllPeriods());

            // Setting ReceiverPointEstimationDTO
            List<ProposalSeller> proposalSellerList = receiverPointDTO.getProposalSellerList();
            List<ReceiverPointEstimationDTO> receiverPointEstimationDTOs =
                    generateReceiverPointEstimationList(proposalSellerList, actualTariffList,
                            receiverPointDTO.getReceiverPointConsumptionSummaryDTO(),
                            proposalContractMonthLength, receiverPointDTO.getActualTradeFee());

            receiverPointDTO.setReceiverPointEstimationList(receiverPointEstimationDTOs);

        }
    }

    /**
     * Setting ReceiverPointConsumptionSummaryDTO
     */
    private void setReceiverPointConsumptionSummaryDTO(List<ActualTariff> actualTariffList, String receiverPointDescription,
                                                       ReceiverPointDTO receiverPointDTO) {
        // Variables
        Integer totalDaysNumberForPeriods = calculateTotalDaysNumberForPeriods(actualTariffList, receiverPointDescription);
        BigDecimal totalElectricityConsumptionUnitsForReceiverPoint = calculateTotalConsumptionUnits(actualTariffList, receiverPointDescription);
        BigDecimal predictedElectricityUnitConsumptionPerYear = calculatePredictedElectricityUnitConsumptionPerYear(
                new BigDecimal(totalDaysNumberForPeriods), totalElectricityConsumptionUnitsForReceiverPoint);
        // Setting variables
        ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO = new ReceiverPointConsumptionSummaryDTO();
        receiverPointConsumptionSummaryDTO.setTotalNumberOfDaysForAllPeriods(totalDaysNumberForPeriods);
        receiverPointConsumptionSummaryDTO.setTotalElectricityUnitsConsumptionInAllPeriods(totalElectricityConsumptionUnitsForReceiverPoint);
        receiverPointConsumptionSummaryDTO.setPredictedElectricityUnitConsumptionPerYear(predictedElectricityUnitConsumptionPerYear);

        receiverPointDTO.setReceiverPointConsumptionSummaryDTO(receiverPointConsumptionSummaryDTO);
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

    /**
     * Setting ReceiverPointEstimationDTO
     */

    private List<ReceiverPointEstimationDTO> generateReceiverPointEstimationList(List<ProposalSeller> proposalSellerList,
                                                                                 List<ActualTariff> actualTariffList,
                                                                                 ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO,
                                                                                 BigDecimal proposalContractMonthLength,
                                                                                 BigDecimal actualTradeFee) {
        List<ReceiverPointEstimationDTO> receiverPointEstimationDTOs = new ArrayList<>();
        for (ProposalSeller proposalSeller : proposalSellerList) {
            ReceiverPointEstimationDTO receiverPointEstimationDTO = generateReceiverPointEstimation(proposalSeller,
                    actualTariffList, receiverPointConsumptionSummaryDTO, proposalContractMonthLength, actualTradeFee);
            receiverPointEstimationDTOs.add(receiverPointEstimationDTO);
        }
        return receiverPointEstimationDTOs;
    }

    private ReceiverPointEstimationDTO generateReceiverPointEstimation(ProposalSeller proposalSeller,
                                                                       List<ActualTariff> actualTariffList,
                                                                       ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO,
                                                                       BigDecimal proposalContractMonthLength,
                                                                       BigDecimal actualTradeFee) {
        ReceiverPointEstimationDTO receiverPointEstimationDTO = new ReceiverPointEstimationDTO();
        receiverPointEstimationDTO.setSellerCode(proposalSeller.getSellerCode());

        // Setting estimation
        ReceiverPointDataEstimationDTO receiverPointDataEstimationDTO = new ReceiverPointDataEstimationDTO();
        BigDecimal estimatedUnitConsumptionInYearScale = calculateEstimatedUnitConsumptionInYearScale(receiverPointConsumptionSummaryDTO);
        BigDecimal estimatedContractProfitValueInYearScale = calculateEstimatedContractProfitValueInYearScale(proposalSeller, actualTariffList,
                receiverPointConsumptionSummaryDTO);
        BigDecimal estimatedContractProfitValue = calculateEstimatedContractProfitValue(estimatedContractProfitValueInYearScale, proposalContractMonthLength);

        BigDecimal estimatedSavingsInYearScale = calculateEstimatedSavingsInYearScale(proposalSeller, actualTariffList,
                receiverPointConsumptionSummaryDTO, actualTradeFee);

        receiverPointDataEstimationDTO.setTariffIssueDate(proposalSeller.getSellerTariffPublicationDate());
        receiverPointDataEstimationDTO.setEstimatedUnitConsumptionInYearScale(estimatedUnitConsumptionInYearScale);
        receiverPointDataEstimationDTO.setEstimatedContractProfitValueInYearScale(estimatedContractProfitValueInYearScale);
        receiverPointDataEstimationDTO.setEstimatedContractProfitValue(estimatedContractProfitValue);
        receiverPointDataEstimationDTO.setEstimatedSavingsInYearScale(estimatedSavingsInYearScale);


        receiverPointEstimationDTO.setReceiverPointDataEstimationDTO(receiverPointDataEstimationDTO);
        return receiverPointEstimationDTO;
    }

    private BigDecimal calculateEstimatedSavingsInYearScale(ProposalSeller proposalSeller,
                                                            List<ActualTariff> actualTariffList,
                                                            ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO,
                                                            BigDecimal actualTradeFee) {

        BigDecimal totalActualCostForAllUnitsConsumption = BigDecimal.ZERO;
        for (ProposalTariff proposalTariff : proposalSeller.getProposalTariffList()) {
            BigDecimal totalElectricityUnitConsumptionForActualTariff =
                    getTotalElectricityUnitConsumptionForActualTariff(actualTariffList, proposalTariff.getActualTariffCode());
            BigDecimal actualUnitPriceForActualTariff = getActualUnitPriceForActualTariff(actualTariffList, proposalTariff.getActualTariffCode());

            BigDecimal total = totalElectricityUnitConsumptionForActualTariff
                    .divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP)
                    .multiply(actualUnitPriceForActualTariff);

            totalActualCostForAllUnitsConsumption = totalActualCostForAllUnitsConsumption.add(total);
        }
        totalActualCostForAllUnitsConsumption = totalActualCostForAllUnitsConsumption.add(actualTradeFee);

        BigDecimal totalCostForAllUnitsConsumptionBasedOnProposal = BigDecimal.ZERO;
        for (ProposalTariff proposalTariff : proposalSeller.getProposalTariffList()) {
            BigDecimal totalElectricityUnitConsumptionForActualTariff =
                    getTotalElectricityUnitConsumptionForActualTariff(actualTariffList, proposalTariff.getActualTariffCode());
            BigDecimal proposalUnitPrice = proposalTariff.getProposalUnitPrice();

            BigDecimal total = totalElectricityUnitConsumptionForActualTariff
                    .divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP)
                    .multiply(proposalUnitPrice);

            totalCostForAllUnitsConsumptionBasedOnProposal = totalCostForAllUnitsConsumptionBasedOnProposal.add(total);
        }
        totalCostForAllUnitsConsumptionBasedOnProposal = totalCostForAllUnitsConsumptionBasedOnProposal.add(actualTradeFee);

        BigDecimal totalNumberOfDaysForAllPeriods = new BigDecimal(receiverPointConsumptionSummaryDTO.getTotalNumberOfDaysForAllPeriods());
        return totalActualCostForAllUnitsConsumption
                .subtract(totalCostForAllUnitsConsumptionBasedOnProposal)
                .divide(totalNumberOfDaysForAllPeriods, 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(365));
    }

    private BigDecimal calculateEstimatedUnitConsumptionInYearScale(ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO) {
        BigDecimal totalElectricityUnitsConsumptionInAllPeriods = receiverPointConsumptionSummaryDTO.getTotalElectricityUnitsConsumptionInAllPeriods();
        BigDecimal totalNumberOfDaysForAllPeriods = new BigDecimal(receiverPointConsumptionSummaryDTO.getTotalNumberOfDaysForAllPeriods());
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
                                                                        ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO) {
        BigDecimal profitForAllTariffs = BigDecimal.ZERO;
        for (ProposalTariff proposalTariff : proposalSeller.getProposalTariffList()) {
            BigDecimal marginForUnitPrice = proposalTariff.getProposalUnitPrice().subtract(proposalTariff.getSellerMinimalUnitPrice());
            BigDecimal totalElectricityUnitConsumptionForActualTariff =
                    getTotalElectricityUnitConsumptionForActualTariff(actualTariffList, proposalTariff.getActualTariffCode());
            BigDecimal tariffProfit = marginForUnitPrice
                    .multiply(totalElectricityUnitConsumptionForActualTariff);

            profitForAllTariffs = profitForAllTariffs.add(tariffProfit.divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP));
        }
        Integer totalNumberOfDaysForAllPeriods = receiverPointConsumptionSummaryDTO.getTotalNumberOfDaysForAllPeriods();
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
    }


}
