package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRoot;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.Invoice;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ReceiverPoint;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.InvoiceZoneConsumption;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.OfferCalculation;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.TotalConsumptionSummary;
import paxxa.com.ees.service.utils.UtilsService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ElectricityOfferCalculationService {

    @Autowired
    private UtilsService utilsService;
    @Autowired
    private ElectricityOfferValidationService electricityOfferValidationService;

    public ElectricityOfferRoot calculateElectricityOffer(ElectricityOfferRoot electricityOfferRoot, String userName) {
        List<ReceiverPoint> receiverPointList = electricityOfferRoot.getReceiverPointList();
        electricityOfferValidationService.validateReceiverPointList(receiverPointList);

        if (electricityOfferRoot.isOfferCalculationPerReceiverPointSet()) {
            calculateReceiverPoints(electricityOfferRoot.getReceiverPointList());


        } else {

        }

        return electricityOfferRoot;
    }

    public void calculateReceiverPoints(List<ReceiverPoint> receiverPointList) {
        for (ReceiverPoint receiverPoint : receiverPointList) {
            setOfferCalculation(receiverPoint);
        }

    }

    public void setOfferCalculation(ReceiverPoint receiverPoint){
        OfferCalculation offerCalculation = new OfferCalculation();
        TotalConsumptionSummary totalConsumptionSummary = calculateTotalConsumptionSummary(receiverPoint.getInvoiceList());
        offerCalculation.setTotalConsumptionSummary(totalConsumptionSummary);


        receiverPoint.setReceiverPointOfferCalculation(offerCalculation);
    }

    public TotalConsumptionSummary calculateTotalConsumptionSummary(List<Invoice> invoiceList) {
        Integer totalNumberOfDaysForAllPeriods = 0;
        BigDecimal totalElectricityUnitsConsumptionInAllPeriods = BigDecimal.ZERO;
        for (Invoice invoice : invoiceList) {
            Integer differenceDays = utilsService.countDaysBetweenTwoDates(
                    invoice.getPeriodStart(), invoice.getGetPeriodStop());
            totalNumberOfDaysForAllPeriods = totalNumberOfDaysForAllPeriods + differenceDays;


            BigDecimal invoiceUnitConsumption = invoice.getInvoiceZoneConsumptionList()
                    .stream()
                    .map(InvoiceZoneConsumption::getUnitConsumption)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            totalElectricityUnitsConsumptionInAllPeriods = totalElectricityUnitsConsumptionInAllPeriods.add(invoiceUnitConsumption);
        }
        TotalConsumptionSummary totalConsumptionSummary = new TotalConsumptionSummary();
        totalConsumptionSummary.setTotalNumberOfDaysForAllPeriods(totalNumberOfDaysForAllPeriods);
        totalConsumptionSummary.setTotalElectricityUnitsConsumptionInAllPeriods(totalElectricityUnitsConsumptionInAllPeriods);
        return totalConsumptionSummary;
    }




}
