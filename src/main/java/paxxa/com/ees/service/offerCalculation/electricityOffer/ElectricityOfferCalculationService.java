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
            Integer integer = calculateTotalDaysNumberForPeriods(receiverPointDTO.getActualTariffList());

        }


        return null;
    }


    private Integer calculateTotalDaysNumberForPeriods(List<ActualTariff> actualTariffList) {
        Integer totalNumberOfDays = 0;
        for (ActualTariff actualTariff : actualTariffList) {
            List<TariffPeriodConsumptionDTO> tariffPeriodConsumptionDTOList =
                    actualTariff.getTariffPeriodConsumptionDTOList();

            for (TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO : tariffPeriodConsumptionDTOList) {
                validIfDatesAreSet(tariffPeriodConsumptionDTO);
                Integer differenceDays = utilsService.countDaysBetweenTwoDates(
                        tariffPeriodConsumptionDTO.getPeriodStart(), tariffPeriodConsumptionDTO.getPeriodEnd());
                totalNumberOfDays = totalNumberOfDays + differenceDays;
            }
        }
    return totalNumberOfDays;
    }

    private void validIfDatesAreSet(TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO){
        if(tariffPeriodConsumptionDTO.getPeriodStart() == null) {
            throw new MissingDataException("periodStart", "TariffPeriodConsumptionDTO");
        }
        if(tariffPeriodConsumptionDTO.getPeriodEnd() == null) {
            throw new MissingDataException("periodEnd", "TariffPeriodConsumptionDTO");
        }
    }

}
