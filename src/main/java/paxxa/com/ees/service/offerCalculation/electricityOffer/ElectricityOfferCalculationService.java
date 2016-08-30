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
        calculateReceiversPoint(electricityOfferRootDTO.getReceiverPointDTOList());

        return electricityOfferRootDTO;
    }

    private void calculateReceiversPoint(List<ReceiverPointDTO> receiverPointDTOList) {
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
                            receiverPointDTO.getReceiverPointConsumptionSummaryDTO());
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
                                                                                 ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO) {
        List<ReceiverPointEstimationDTO> receiverPointEstimationDTOs = new ArrayList<>();
        for (ProposalSeller proposalSeller : proposalSellerList) {
            ReceiverPointEstimationDTO receiverPointEstimationDTO =
                    generateReceiverPointEstimation(proposalSeller, actualTariffList, receiverPointConsumptionSummaryDTO);
            receiverPointEstimationDTOs.add(receiverPointEstimationDTO);
        }
        return receiverPointEstimationDTOs;
    }

    private ReceiverPointEstimationDTO generateReceiverPointEstimation(ProposalSeller proposalSeller,
                                                                       List<ActualTariff> actualTariffList,
                                                                       ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO) {
        ReceiverPointEstimationDTO receiverPointEstimationDTO = new ReceiverPointEstimationDTO();
        receiverPointEstimationDTO.setSellerCode(proposalSeller.getSellerCode());

        ReceiverPointDataEstimationDTO receiverPointDataEstimationDTO = new ReceiverPointDataEstimationDTO();
        receiverPointDataEstimationDTO.setTariffIssueDate(proposalSeller.getSellerTariffPublicationDate());

        BigDecimal estimatedContractValueInYearScale = calculateEstimatedContractValueInYearScale(proposalSeller, actualTariffList,
                receiverPointConsumptionSummaryDTO);
        receiverPointDataEstimationDTO.setEstimatedContractValueInYearScale(estimatedContractValueInYearScale);


        receiverPointEstimationDTO.setReceiverPointDataEstimationDTO(receiverPointDataEstimationDTO);
        return receiverPointEstimationDTO;
    }

    private BigDecimal calculateEstimatedContractValueInYearScale(ProposalSeller proposalSeller,
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
            if (expectedActualTariffCode.equals(actualTariff.getActualTariffCode())) ;
            return actualTariff.getTotalUnitConsumptionFromPeriods();
        }
        throw new RuntimeException("Unexpected situation, actual tariff should contain TotalElectricityUnitConsumptionForTariff");
    }


}
