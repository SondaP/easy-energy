package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRootDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.*;
import paxxa.com.ees.service.exception.OfferCalculationException.IncorrectDataException;
import paxxa.com.ees.service.utils.UtilsService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

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

            validateProposalSellers(receiverPointDTO.getProposalSellerList(),
                    receiverPointDescription, receiverPointDTO.getProposalNumberOfTariffs());
            validateProposalTariffs(receiverPointDTO.getProposalSellerList(),
                    receiverPointDescription, receiverPointDTO.getProposalNumberOfTariffs(), getActualTariffsCodes(actualTariffList));

            // Setting ReceiverPointConsumptionSummaryDTO
            setReceiverPointConsumptionSummaryDTO(actualTariffList, receiverPointDescription, receiverPointDTO);
            setActualTariffsDetails(actualTariffList,
                    receiverPointDTO.getReceiverPointConsumptionSummaryDTO().getTotalElectricityUnitsConsumptionInAllPeriods());

            // ProposalSellers
            List<ProposalSeller> proposalSellerList = receiverPointDTO.getProposalSellerList();

        }
    }


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

    private void validateTariffPeriodsConsumptions(List<ActualTariff> actualTariffList, String receiverPointDescription) {
        for (ActualTariff actualTariff : actualTariffList) {

            if (actualTariff.getActualTariffCode() == null || actualTariff.getActualTariffCode().isEmpty()) {
                String message = "Value for attribute: actualTariffCode from Object: ActualTariff at "
                        + receiverPointDescription + ", is required";
                throw new IncorrectDataException(message);
            }
            if (actualTariff.getActualUnitPrice() == null) {
                String message = "Value for attribute: actualUnitPrice from Object: ActualTariff at "
                        + receiverPointDescription + ", is required";
                throw new IncorrectDataException(message);
            }


            List<TariffPeriodConsumptionDTO> tariffPeriodConsumptionDTOList =
                    actualTariff.getTariffPeriodConsumptionDTOList();

            for (TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO : tariffPeriodConsumptionDTOList) {

                if (tariffPeriodConsumptionDTO.getPeriodStart() == null) {
                    String message = "Value for attribute: periodStart from Object: TariffPeriodConsumptionDTO at "
                            + receiverPointDescription + ", is required";
                    throw new IncorrectDataException(message);
                }
                if (tariffPeriodConsumptionDTO.getPeriodEnd() == null) {
                    String message = "Value for attribute: periodStop from Object: TariffPeriodConsumptionDTO at "
                            + receiverPointDescription + ", is required";
                    throw new IncorrectDataException(message);
                }
                if (tariffPeriodConsumptionDTO.getUnitConsumption() == null) {
                    String message = "Value for attribute: unitConsumption from Object: TariffPeriodConsumptionDTO at "
                            + receiverPointDescription + ", is required";
                    throw new IncorrectDataException(message);
                }
                if (tariffPeriodConsumptionDTO.getDocumentNumber() == null || tariffPeriodConsumptionDTO.getDocumentNumber().isEmpty()) {
                    String message = "Value for attribute: documentNumber from Object: TariffPeriodConsumptionDTO at "
                            + receiverPointDescription + ", is required and cannot be empty";
                    throw new IncorrectDataException(message);
                }

            }
        }
    }

    private void validateNumberOfActualTariffs(Integer expectedNumberOfTariffs, List<ActualTariff> actualTariffList,
                                               String receiverPointDescription) {
        if (actualTariffList.size() != expectedNumberOfTariffs) throw new IncorrectDataException(
                "Incorrect number of tariffs at actualTariffList, should be " + expectedNumberOfTariffs
                        + " positions. Receiver point: " + receiverPointDescription);
    }

    private void validateReceiverPoint(ReceiverPointDTO receiverPointDTO) {
        if (receiverPointDTO.getReceiverPointDescription() == null || receiverPointDTO.getReceiverPointDescription().isEmpty()) {
            String message = "Value for attribute: receiverPointDescription from Object: ReceiverPointDTO at "
                    + receiverPointDTO.getReceiverPointDescription() + ", is required and cannot be empty";
            throw new IncorrectDataException(message);
        }
        if (receiverPointDTO.getActualTradeFee() == null) {
            String message = "Value for attribute: actualTradeFee from Object: ReceiverPointDTO at "
                    + receiverPointDTO.getReceiverPointDescription() + ", is required";
            throw new IncorrectDataException(message);
        }
        if (receiverPointDTO.getActualNumberOfTariffs() == null) {
            String message = "Value for attribute: actualNumberOfTariffs from Object: ReceiverPointDTO at "
                    + receiverPointDTO.getReceiverPointDescription() + ", is required";
            throw new IncorrectDataException(message);
        }
        if (receiverPointDTO.getProposalNumberOfTariffs() == null) {
            String message = "Value for attribute: proposalNumberOfTariffs from Object: ReceiverPointDTO at "
                    + receiverPointDTO.getReceiverPointDescription() + ", is required";
            throw new IncorrectDataException(message);
        }
    }

    private void validateElectricityOfferRoot(ElectricityOfferRootDTO electricityOfferRootDTO) {
        if (electricityOfferRootDTO.getProposalContractMonthLength() == null) {
            String message = "Value for attribute: proposalContractMonthLength from Object: ElectricityOfferRootDTO" +
                    ", is required";
            throw new IncorrectDataException(message);
        }
    }

    private void validateProposalSellers(List<ProposalSeller> proposalSellerList, String receiverPointDescription, Integer proposalNumberOfTariffs) {
        for (ProposalSeller proposalSeller : proposalSellerList) {
            if (proposalSeller.getSellerCode() == null || proposalSeller.getSellerCode().isEmpty()) {
                String message = "Value for attribute: sellerCode from Object: ProposalSeller at "
                        + receiverPointDescription + ", is required";
                throw new IncorrectDataException(message);
            }
            if (proposalSeller.getProposalTradeFee() == null) {
                String message = "Value for attribute: proposalTradeFee from Object: ProposalSeller at "
                        + receiverPointDescription + ", is required";
                throw new IncorrectDataException(message);
            }
            if (proposalSeller.getSellerTariffPublicationDate() == null) {
                String message = "Value for attribute: sellerTariffPublicationDate from Object: ProposalSeller at "
                        + receiverPointDescription + ", is required";
                throw new IncorrectDataException(message);
            }
            if (proposalSeller.getProposalTariffList() == null || proposalSeller.getProposalTariffList().size() != proposalNumberOfTariffs) {
                String message = "Value for attribute: proposalTariffList from Object: ProposalSeller at "
                        + receiverPointDescription + ", is required and list size must be equals to proposalNumberOfTariffs";
                throw new IncorrectDataException(message);
            }
        }
    }

    private void validateProposalTariffs(List<ProposalSeller> proposalSellerList, String receiverPointDescription,
                                         Integer proposalNumberOfTariffs, List<String> actualTariffCodesList) {
        for (ProposalSeller proposalSeller : proposalSellerList) {
            for (ProposalTariff proposalTariff : proposalSeller.getProposalTariffList()) {
                if (proposalTariff.getActualTariffCode() == null) {
                    String message = "Value for attribute: actualTariffCode from Object: ProposalSeller at "
                            + receiverPointDescription + ", is required";
                    throw new IncorrectDataException(message);
                }
                if (proposalTariff.getProposalTariffCode() == null) {
                    String message = "Value for attribute: proposalTariffCode from Object: ProposalSeller at "
                            + receiverPointDescription + ", is required";
                    throw new IncorrectDataException(message);
                }
                if (proposalTariff.getProposalUnitPrice() == null) {
                    String message = "Value for attribute: proposalUnitPrice from Object: ProposalSeller at "
                            + receiverPointDescription + ", is required";
                    throw new IncorrectDataException(message);
                }
                if (!actualTariffCodesList.contains(proposalTariff.getActualTariffCode())) {
                    String message = "Value for attribute: actualTariffCode from Object: ProposalSeller at "
                            + receiverPointDescription + ", is incorrect. Value should be the same as one of the " +
                            "actualTariffCode from ActualTariff ";
                    throw new IncorrectDataException(message);
                }
            }
        }
    }

    private List<String> getActualTariffsCodes(List<ActualTariff> actualTariffList) {
        return actualTariffList.stream().map(ActualTariff::getActualTariffCode).collect(Collectors.toList());
    }


}
