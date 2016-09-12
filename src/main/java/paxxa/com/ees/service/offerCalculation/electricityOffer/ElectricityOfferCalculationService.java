package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRoot;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.Invoice;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ProposalZoneDetails;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ReceiverPoint;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.*;
import paxxa.com.ees.service.utils.UtilsService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ElectricityOfferCalculationService {

    @Autowired
    private UtilsService utilsService;
    @Autowired
    private ElectricityOfferValidationService electricityOfferValidationService;

    public ElectricityOfferRoot calculateElectricityOffer(ElectricityOfferRoot electricityOfferRoot,
                                                          String userName) {
        List<ReceiverPoint> receiverPointList = electricityOfferRoot.getReceiverPointList();
        electricityOfferValidationService.validateReceiverPointList(receiverPointList);

        if (electricityOfferRoot.isOfferCalculationPerReceiverPointSet()) {
            calculateOfferCalculation(electricityOfferRoot.getReceiverPointList());


        } else {

        }

        return electricityOfferRoot;
    }

    private void calculateOfferCalculation(List<ReceiverPoint> receiverPointList) {
        for (ReceiverPoint receiverPoint : receiverPointList) {
            setTotalConsumptionSummary(receiverPoint);
            setCalculatedProposalSellerList(receiverPoint.getReceiverPointOfferCalculation(), receiverPoint.getInvoiceList());
        }

    }

    //Setting TotalConsumptionSummary
    private void setTotalConsumptionSummary(ReceiverPoint receiverPoint) {
        OfferCalculation receiverPointOfferCalculation = receiverPoint.getReceiverPointOfferCalculation();
        TotalConsumptionSummary totalConsumptionSummary = calculateTotalConsumptionSummary(receiverPoint.getInvoiceList());
        receiverPointOfferCalculation.setTotalConsumptionSummary(totalConsumptionSummary);
        receiverPoint.setReceiverPointOfferCalculation(receiverPointOfferCalculation);
    }

    private TotalConsumptionSummary calculateTotalConsumptionSummary(List<Invoice> invoiceList) {
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
        BigDecimal predictedElectricityUnitConsumptionPerYear =
                calculatePredictedElectricityUnitConsumptionPerYear(
                        new BigDecimal(totalNumberOfDaysForAllPeriods), totalElectricityUnitsConsumptionInAllPeriods);
        List<ZoneTotalConsumptionSummary> calculatedZoneTotalConsumptionSummaryList =
                getCalculatedZoneTotalConsumptionSummaryList(invoiceList);

        TotalConsumptionSummary totalConsumptionSummary = new TotalConsumptionSummary();
        totalConsumptionSummary.setTotalNumberOfDaysForAllPeriods(totalNumberOfDaysForAllPeriods);
        totalConsumptionSummary.setTotalElectricityUnitsConsumptionInAllPeriods(totalElectricityUnitsConsumptionInAllPeriods);
        totalConsumptionSummary.setPredictedElectricityUnitConsumptionPerYear(predictedElectricityUnitConsumptionPerYear);
        totalConsumptionSummary.setZoneTotalConsumptionSummaryList(calculatedZoneTotalConsumptionSummaryList);
        return totalConsumptionSummary;
    }

    private List<ZoneTotalConsumptionSummary> getCalculatedZoneTotalConsumptionSummaryList(List<Invoice> invoiceList) {
        Map<String, ZoneTotalConsumptionSummary> zoneTotalConsumptionMap = new HashMap<>();
        for (Invoice invoice : invoiceList) {
            for (InvoiceZoneConsumption invoiceZoneConsumption : invoice.getInvoiceZoneConsumptionList()) {
                String actualZoneCode = invoiceZoneConsumption.getActualZoneCode();
                if (zoneTotalConsumptionMap.containsKey(actualZoneCode)) {
                    ZoneTotalConsumptionSummary zoneTotalConsumptionSummary = zoneTotalConsumptionMap.get(actualZoneCode);
                    BigDecimal actualTotalUnitConsumption = zoneTotalConsumptionSummary.getTotalUnitConsumption();
                    zoneTotalConsumptionSummary.setTotalUnitConsumption(actualTotalUnitConsumption.
                            add(invoiceZoneConsumption.getUnitConsumption()));
                } else {
                    ZoneTotalConsumptionSummary zoneTotalConsumptionSummary = new ZoneTotalConsumptionSummary();
                    zoneTotalConsumptionSummary.setActualZoneCode(invoiceZoneConsumption.getActualZoneCode());
                    zoneTotalConsumptionSummary.setTotalUnitConsumption(invoiceZoneConsumption.getUnitConsumption());
                    zoneTotalConsumptionMap.put(invoiceZoneConsumption.getActualZoneCode(), zoneTotalConsumptionSummary);
                }
            }
        }
        return new ArrayList<>(zoneTotalConsumptionMap.values());
    }

    private BigDecimal calculatePredictedElectricityUnitConsumptionPerYear(BigDecimal totalDaysNumberForPeriods,
                                                                           BigDecimal totalElectricityConsumptionUnitsForReceiverPoint) {
        return totalElectricityConsumptionUnitsForReceiverPoint
                .divide(totalDaysNumberForPeriods, 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(365));
    }

    //Setting ProposalSellerList
    private void setCalculatedProposalSellerList(OfferCalculation receiverPointOfferCalculation, List<Invoice> invoiceList) {
        List<ProposalSeller> proposalSellerList = receiverPointOfferCalculation.getProposalSellerList();
        TotalConsumptionSummary totalConsumptionSummary = receiverPointOfferCalculation.getTotalConsumptionSummary();
        BigDecimal proposalContractMonthLength = receiverPointOfferCalculation.getOfferParameters().getProposalContractMonthLength();
        ActualReceiverPointFees actualReceiverPointFees = receiverPointOfferCalculation.getActualReceiverPointFees();

        for (ProposalSeller proposalSeller : proposalSellerList) {
            calculateProposalSeller(proposalSeller, actualReceiverPointFees, totalConsumptionSummary, proposalContractMonthLength, invoiceList);
        }
    }

    private void calculateProposalSeller(ProposalSeller proposalSeller,
                                         ActualReceiverPointFees actualReceiverPointFees,
                                         TotalConsumptionSummary totalConsumptionSummary,
                                         BigDecimal proposalContractMonthLength,
                                         List<Invoice> invoiceList) {
        ReceiverPointDataEstimation receiverPointDataEstimation = calculateReceiverPointDataEstimation(
                proposalSeller, actualReceiverPointFees, totalConsumptionSummary, proposalContractMonthLength, invoiceList);
        List<ReceiverPointProvision> receiverPointProvisionList = calculateReceiverPointProvisionList();

        ReceiverPointEstimation receiverPointEstimation = new ReceiverPointEstimation();
        receiverPointEstimation.setSellerCode(proposalSeller.getSellerCode());
        receiverPointEstimation.setReceiverPointDataEstimation(receiverPointDataEstimation);
        receiverPointEstimation.setReceiverPointProvisionList(receiverPointProvisionList);

        proposalSeller.setReceiverPointEstimation(receiverPointEstimation);
    }

    //Calculating ReceiverPointDataEstimation
    private ReceiverPointDataEstimation calculateReceiverPointDataEstimation(ProposalSeller proposalSeller,
                                                                             ActualReceiverPointFees actualReceiverPointFees,
                                                                             TotalConsumptionSummary totalConsumptionSummary,
                                                                             BigDecimal proposalContractMonthLength,
                                                                             List<Invoice> invoiceList) {
        BigDecimal estimatedUnitConsumptionInYearScale = calculateEstimatedUnitConsumptionInYearScale(totalConsumptionSummary);
        BigDecimal estimatedContractProfitValueInYearScale = calculateEstimatedContractProfitValueInYearScale(
                proposalSeller, actualReceiverPointFees, totalConsumptionSummary, invoiceList);
        BigDecimal estimatedContractProfitValue = calculateEstimatedContractProfitValue(estimatedContractProfitValueInYearScale, proposalContractMonthLength);

        ReceiverPointDataEstimation receiverPointDataEstimation = new ReceiverPointDataEstimation();
        receiverPointDataEstimation.setTariffIssueDate(proposalSeller.getSellerTariffPublicationDate());
        receiverPointDataEstimation.setEstimatedUnitConsumptionInYearScale(estimatedUnitConsumptionInYearScale);
        receiverPointDataEstimation.setEstimatedContractProfitValueInYearScale(estimatedContractProfitValueInYearScale);
        receiverPointDataEstimation.setEstimatedContractProfitValue(estimatedContractProfitValue);

        return receiverPointDataEstimation;
    }

    private BigDecimal calculateEstimatedUnitConsumptionInYearScale(TotalConsumptionSummary totalConsumptionSummary) {
        BigDecimal totalElectricityUnitsConsumptionInAllPeriods = totalConsumptionSummary.getTotalElectricityUnitsConsumptionInAllPeriods();
        BigDecimal totalNumberOfDaysForAllPeriods = new BigDecimal(totalConsumptionSummary.getTotalNumberOfDaysForAllPeriods());
        return totalElectricityUnitsConsumptionInAllPeriods
                .divide(totalNumberOfDaysForAllPeriods, 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(365));
    }

    private BigDecimal calculateEstimatedContractProfitValueInYearScale(ProposalSeller proposalSeller,
                                                                        ActualReceiverPointFees actualReceiverPointFees,
                                                                        TotalConsumptionSummary totalConsumptionSummary,
                                                                        List<Invoice> invoiceList) {
        BigDecimal profitForAllZones = BigDecimal.ZERO;
        for (ProposalZoneDetails proposalZoneDetails : proposalSeller.getProposalZoneDetailsList()) {
            BigDecimal marginForUnitPrice = proposalZoneDetails.getProposalUnitPrice().subtract(proposalZoneDetails.getSellerMinimalUnitPrice());
            BigDecimal totalElectricityUnitConsumptionForZoneCode = getTotalElectricityUnitConsumptionForZoneCode(totalConsumptionSummary, proposalZoneDetails.getActualZoneCode());

            BigDecimal zoneProfit = marginForUnitPrice
                    .multiply(totalElectricityUnitConsumptionForZoneCode);
            profitForAllZones = profitForAllZones.add(zoneProfit.divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP));
        }
        Integer totalNumberOfDaysForAllPeriods = totalConsumptionSummary.getTotalNumberOfDaysForAllPeriods();
        BigDecimal estimatedContractValueInYearScale = profitForAllZones
                .divide(new BigDecimal(totalNumberOfDaysForAllPeriods), 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(365));
        return estimatedContractValueInYearScale;
    }

    private BigDecimal getTotalElectricityUnitConsumptionForZoneCode(TotalConsumptionSummary totalConsumptionSummary, String expectedZoneCode) {
        return totalConsumptionSummary.getZoneTotalConsumptionSummaryList()
                .stream()
                .filter(x -> expectedZoneCode.equals(x.getActualZoneCode()))
                .findFirst()
                .map(ZoneTotalConsumptionSummary::getTotalUnitConsumption)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal calculateEstimatedContractProfitValue(BigDecimal estimatedContractProfitValueInYearScale, BigDecimal proposalContractMonthLength) {
        return estimatedContractProfitValueInYearScale
                .divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP)
                .multiply(proposalContractMonthLength);
    }

/*
    private BigDecimal calculateEstimatedSavingsInYearScale(ProposalSeller proposalSeller,
                                                            ActualReceiverPointFees actualReceiverPointFees,
                                                            TotalConsumptionSummary totalConsumptionSummary,
                                                            List<Invoice> invoiceList,
                                                            BigDecimal actualTradeFee) {
        BigDecimal totalActualCostForAllUnitsConsumption = BigDecimal.ZERO;





        //BigDecimal totalActualCostForAllUnitsConsumption = BigDecimal.ZERO;
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

    */


    //Calculating ReceiverPointProvisionList
    private List<ReceiverPointProvision> calculateReceiverPointProvisionList() {
        List<ReceiverPointProvision> receiverPointProvisionList = new ArrayList<>();

        return receiverPointProvisionList;
    }

}
