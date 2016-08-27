package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRootDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ActualTariff;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ReceiverPointDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.TariffPeriodConsumptionDTO;
import paxxa.com.ees.service.exception.OfferCalculationException.MissingDataException;
import paxxa.com.ees.service.utils.UtilsService;

import java.util.List;

@Service
public class ElectricityOfferCalculationService {

    @Autowired
    private UtilsService utilsService;

    public ElectricityOfferRootDTO calculateElectricityOffer(ElectricityOfferRootDTO electricityOfferRootDTO, String userName) {

        List<ReceiverPointDTO> receiverPointDTOList = electricityOfferRootDTO.getReceiverPointDTOList();


        for (ReceiverPointDTO receiverPointDTO : receiverPointDTOList) {
            List<ActualTariff> actualTariffList = receiverPointDTO.getActualTariffList();
            String receiverPointDescription = receiverPointDTO.getReceiverPointDescription();
            validateNumberOfActualTariffs(receiverPointDTO.getActualTariffsNumber(), actualTariffList, receiverPointDescription);
            Integer integer = calculateTotalDaysNumberForPeriods(actualTariffList, receiverPointDescription);

        }


        return null;
    }


    private Integer calculateTotalDaysNumberForPeriods(List<ActualTariff> actualTariffList, String receiverPointDescription) {
        Integer totalNumberOfDays = 0;
        for (ActualTariff actualTariff : actualTariffList) {
            List<TariffPeriodConsumptionDTO> tariffPeriodConsumptionDTOList =
                    actualTariff.getTariffPeriodConsumptionDTOList();

            for (TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO : tariffPeriodConsumptionDTOList) {
                validIfDatesAreSet(tariffPeriodConsumptionDTO, receiverPointDescription);
                Integer differenceDays = utilsService.countDaysBetweenTwoDates(
                        tariffPeriodConsumptionDTO.getPeriodStart(), tariffPeriodConsumptionDTO.getPeriodEnd());
                totalNumberOfDays = totalNumberOfDays + differenceDays;
            }
        }
        return totalNumberOfDays;
    }

    private void validIfDatesAreSet(TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO, String receiverPointDescription) {
        if (tariffPeriodConsumptionDTO.getPeriodStart() == null) {
            throw new MissingDataException("Value for attribute: periodStart from Object: TariffPeriodConsumptionDTO at "
                    + receiverPointDescription + ", is required");
        }
        if (tariffPeriodConsumptionDTO.getPeriodEnd() == null) {
            throw new MissingDataException("Value for attribute: periodStop from Object: TariffPeriodConsumptionDTO at "
                    + receiverPointDescription + ", is required");
        }
    }

    private void validateNumberOfActualTariffs(Integer expectedNumberOfTariffs, List<ActualTariff> actualTariffList,
                                               String receiverPointDescription) {
        if (actualTariffList.size() != expectedNumberOfTariffs) throw new MissingDataException(
                "Incorrect number of tariffs at actualTariffList, should be " + expectedNumberOfTariffs
                + " positions. Receiver point: " + receiverPointDescription);

    }

}
