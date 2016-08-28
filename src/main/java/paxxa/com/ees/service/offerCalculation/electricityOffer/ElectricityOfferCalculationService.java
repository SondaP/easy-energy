package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRootDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ActualTariff;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ReceiverPointConsumptionSummaryDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ReceiverPointDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.TariffPeriodConsumptionDTO;
import paxxa.com.ees.service.exception.OfferCalculationException.MissingDataException;
import paxxa.com.ees.service.utils.UtilsService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ElectricityOfferCalculationService {

    @Autowired
    private UtilsService utilsService;

    public ElectricityOfferRootDTO calculateElectricityOffer(ElectricityOfferRootDTO electricityOfferRootDTO, String userName) {

        calculateReceiversPoint(electricityOfferRootDTO.getReceiverPointDTOList());


        return electricityOfferRootDTO;
    }


    private void calculateReceiversPoint(List<ReceiverPointDTO> receiverPointDTOList) {
        for (ReceiverPointDTO receiverPointDTO : receiverPointDTOList) {

            List<ActualTariff> actualTariffList = receiverPointDTO.getActualTariffList();
            String receiverPointDescription = receiverPointDTO.getReceiverPointDescription();
            // Validation section
            validateReceiverPoint(receiverPointDTO);
            validateNumberOfActualTariffs(receiverPointDTO.getActualNumberOfTariffs(), actualTariffList,
                    receiverPointDescription);
            validateTariffPeriodsConsumptions(actualTariffList, receiverPointDescription);

            // Variables
            Integer totalDaysNumberForPeriods = calculateTotalDaysNumberForPeriods(actualTariffList, receiverPointDescription);
            BigDecimal totalElectricityConsumptionUnitsForReceiverPoint = calculateTotalConsumptionUnits(actualTariffList, receiverPointDescription);
            BigDecimal predictedElectricityUnitConsumptionPerYear = calculatePredictedElectricityUnitConsumptionPerYear(
                    new BigDecimal(totalDaysNumberForPeriods), totalElectricityConsumptionUnitsForReceiverPoint);


            // Setting variables
            setActualTariffsDetails(actualTariffList, totalElectricityConsumptionUnitsForReceiverPoint);

            ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO = new ReceiverPointConsumptionSummaryDTO();
            receiverPointConsumptionSummaryDTO.setTotalNumberOfDaysForAllPeriods(totalDaysNumberForPeriods);
            receiverPointConsumptionSummaryDTO.setTotalElectricityUnitsConsumptionInAllPeriods(totalElectricityConsumptionUnitsForReceiverPoint);
            receiverPointConsumptionSummaryDTO.setPredictedElectricityUnitConsumptionPerYear(predictedElectricityUnitConsumptionPerYear);


            receiverPointDTO.setReceiverPointConsumptionSummaryDTO(receiverPointConsumptionSummaryDTO);
        }
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

    private void setActualTariffsDetails(List<ActualTariff> actualTariffList, BigDecimal totalElectricityConsumptionUnitsForReceiverPoint){
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

    private void validateTariffPeriodsConsumptions(List<ActualTariff> actualTariffList, String receiverPointDescription) {
        for (ActualTariff actualTariff : actualTariffList) {

            if (actualTariff.getActualTariffCode() == null || actualTariff.getActualTariffCode().isEmpty()) {
                String message = "Value for attribute: actualTariffCode from Object: ActualTariff at "
                        + receiverPointDescription + ", is required";
                throw new MissingDataException(message);
            }
            if (actualTariff.getActualUnitPrice() == null) {
                String message = "Value for attribute: actualUnitPrice from Object: ActualTariff at "
                        + receiverPointDescription + ", is required";
                throw new MissingDataException(message);
            }


            List<TariffPeriodConsumptionDTO> tariffPeriodConsumptionDTOList =
                    actualTariff.getTariffPeriodConsumptionDTOList();

            for (TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO : tariffPeriodConsumptionDTOList) {

                if (tariffPeriodConsumptionDTO.getPeriodStart() == null) {
                    String message = "Value for attribute: periodStart from Object: TariffPeriodConsumptionDTO at "
                            + receiverPointDescription + ", is required";
                    throw new MissingDataException(message);
                }
                if (tariffPeriodConsumptionDTO.getPeriodEnd() == null) {
                    String message = "Value for attribute: periodStop from Object: TariffPeriodConsumptionDTO at "
                            + receiverPointDescription + ", is required";
                    throw new MissingDataException(message);
                }
                if (tariffPeriodConsumptionDTO.getUnitConsumption() == null) {
                    String message = "Value for attribute: unitConsumption from Object: TariffPeriodConsumptionDTO at "
                            + receiverPointDescription + ", is required";
                    throw new MissingDataException(message);
                }
                if (tariffPeriodConsumptionDTO.getDocumentNumber() == null || tariffPeriodConsumptionDTO.getDocumentNumber().isEmpty()) {
                    String message = "Value for attribute: documentNumber from Object: TariffPeriodConsumptionDTO at "
                            + receiverPointDescription + ", is required and cannot be empty";
                    throw new MissingDataException(message);
                }

            }
        }
    }

    private void validateNumberOfActualTariffs(Integer expectedNumberOfTariffs, List<ActualTariff> actualTariffList,
                                               String receiverPointDescription) {
        if (actualTariffList.size() != expectedNumberOfTariffs) throw new MissingDataException(
                "Incorrect number of tariffs at actualTariffList, should be " + expectedNumberOfTariffs
                        + " positions. Receiver point: " + receiverPointDescription);
    }

    private void validateReceiverPoint(ReceiverPointDTO receiverPointDTO){
        if (receiverPointDTO.getReceiverPointDescription() == null || receiverPointDTO.getReceiverPointDescription().isEmpty()) {
            String message = "Value for attribute: receiverPointDescription from Object: ReceiverPointDTO at "
                    + receiverPointDTO.getReceiverPointDescription() + ", is required and cannot be empty";
            throw new MissingDataException(message);
        }
        if (receiverPointDTO.getActualTradeFee() == null) {
            String message = "Value for attribute: actualTradeFee from Object: ReceiverPointDTO at "
                    + receiverPointDTO.getReceiverPointDescription() + ", is required";
            throw new MissingDataException(message);
        }
        if (receiverPointDTO.getActualNumberOfTariffs() == null) {
            String message = "Value for attribute: actualNumberOfTariffs from Object: ReceiverPointDTO at "
                    + receiverPointDTO.getReceiverPointDescription() + ", is required";
            throw new MissingDataException(message);
        }
        if (receiverPointDTO.getProposalNumberOfTariffs() == null) {
            String message = "Value for attribute: proposalNumberOfTariffs from Object: ReceiverPointDTO at "
                    + receiverPointDTO.getReceiverPointDescription() + ", is required";
            throw new MissingDataException(message);
        }
    }


}
