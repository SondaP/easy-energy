package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRoot;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ActualZoneFee;
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
            setCalculatedProposalSellerList(receiverPoint.getReceiverPointOfferCalculation());
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
                calculateZoneTotalConsumptionSummaryList(invoiceList);

        TotalConsumptionSummary totalConsumptionSummary = new TotalConsumptionSummary();
        totalConsumptionSummary.setTotalNumberOfDaysForAllPeriods(totalNumberOfDaysForAllPeriods);
        totalConsumptionSummary.setTotalElectricityUnitsConsumptionInAllPeriods(totalElectricityUnitsConsumptionInAllPeriods);
        totalConsumptionSummary.setPredictedElectricityUnitConsumptionPerYear(predictedElectricityUnitConsumptionPerYear);
        totalConsumptionSummary.setInvoiceNumbers(invoiceList.size());
        totalConsumptionSummary.setZoneTotalConsumptionSummaryList(calculatedZoneTotalConsumptionSummaryList);
        return totalConsumptionSummary;
    }

    private List<ZoneTotalConsumptionSummary> calculateZoneTotalConsumptionSummaryList(List<Invoice> invoiceList) {
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
                .divide(totalDaysNumberForPeriods, 2, RoundingMode.HALF_EVEN)
                .multiply(new BigDecimal(365));
    }

    //Setting ProposalSellerList
    private void setCalculatedProposalSellerList(OfferCalculation receiverPointOfferCalculation) {
        List<ProposalSeller> proposalSellerList = receiverPointOfferCalculation.getProposalSellerList();
        TotalConsumptionSummary totalConsumptionSummary = receiverPointOfferCalculation.getTotalConsumptionSummary();
        BigDecimal proposalContractMonthLength = receiverPointOfferCalculation.getOfferParameters().getProposalContractMonthLength();
        ActualReceiverPointFees actualReceiverPointFees = receiverPointOfferCalculation.getActualReceiverPointFees();

        for (ProposalSeller proposalSeller : proposalSellerList) {
            calculateProposalSeller(proposalSeller, actualReceiverPointFees, totalConsumptionSummary, proposalContractMonthLength);
        }
    }

    private void calculateProposalSeller(ProposalSeller proposalSeller,
                                         ActualReceiverPointFees actualReceiverPointFees,
                                         TotalConsumptionSummary totalConsumptionSummary,
                                         BigDecimal proposalContractMonthLength) {
        ReceiverPointDataEstimation receiverPointDataEstimation = calculateReceiverPointDataEstimation(
                proposalSeller, actualReceiverPointFees, totalConsumptionSummary, proposalContractMonthLength);
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
                                                                             BigDecimal proposalContractMonthLength) {
        BigDecimal estimatedUnitConsumptionInYearScale = calculateEstimatedUnitConsumptionInYearScale(totalConsumptionSummary);
        BigDecimal estimatedContractProfitValueInYearScale = calculateEstimatedContractProfitValueInYearScale(
                proposalSeller, actualReceiverPointFees, totalConsumptionSummary);
        BigDecimal estimatedContractProfitValue = calculateEstimatedContractProfitValue(
                estimatedContractProfitValueInYearScale, proposalContractMonthLength);
        BigDecimal estimatedSavingsInYearScale =
                calculateEstimatedSavingsInYearScale(proposalSeller, actualReceiverPointFees, totalConsumptionSummary);
        BigDecimal estimatedSavingsInContractScale = calculateEstimatedSavingsInContractScale(estimatedSavingsInYearScale, proposalContractMonthLength);
        BigDecimal estimatedSavingsInPercentage = calculateEstimatedSavingsInPercentage(totalConsumptionSummary);


        ReceiverPointDataEstimation receiverPointDataEstimation = new ReceiverPointDataEstimation();
        receiverPointDataEstimation.setTariffIssueDate(proposalSeller.getSellerTariffPublicationDate());
        receiverPointDataEstimation.setEstimatedUnitConsumptionInYearScale(estimatedUnitConsumptionInYearScale);
        receiverPointDataEstimation.setEstimatedContractProfitValueInYearScale(estimatedContractProfitValueInYearScale);
        receiverPointDataEstimation.setEstimatedContractProfitValue(estimatedContractProfitValue);
        receiverPointDataEstimation.setEstimatedSavingsInYearScale(estimatedSavingsInYearScale);
        receiverPointDataEstimation.setEstimatedSavingsInContractScale(estimatedSavingsInContractScale);
        receiverPointDataEstimation.setEstimatedSavingsInPercentage(estimatedSavingsInPercentage);

        return receiverPointDataEstimation;
    }

    private BigDecimal calculateEstimatedUnitConsumptionInYearScale(TotalConsumptionSummary totalConsumptionSummary) {
        BigDecimal totalElectricityUnitsConsumptionInAllPeriods = totalConsumptionSummary.getTotalElectricityUnitsConsumptionInAllPeriods();
        BigDecimal totalNumberOfDaysForAllPeriods = new BigDecimal(totalConsumptionSummary.getTotalNumberOfDaysForAllPeriods());
        return totalElectricityUnitsConsumptionInAllPeriods
                .divide(totalNumberOfDaysForAllPeriods, 2, RoundingMode.HALF_EVEN)
                .multiply(new BigDecimal(365));
    }

    private BigDecimal calculateEstimatedContractProfitValueInYearScale(ProposalSeller proposalSeller,
                                                                        ActualReceiverPointFees actualReceiverPointFees,
                                                                        TotalConsumptionSummary totalConsumptionSummary) {
        BigDecimal profitForAllZones = BigDecimal.ZERO;
        for (ProposalZoneDetails proposalZoneDetails : proposalSeller.getProposalZoneDetailsList()) {
            BigDecimal marginForUnitPrice = proposalZoneDetails.getProposalUnitPrice().subtract(proposalZoneDetails.getSellerMinimalUnitPrice());
            BigDecimal totalElectricityUnitConsumptionForZoneCode = getTotalUnitConsumptionForZoneCode(totalConsumptionSummary, proposalZoneDetails.getActualZoneCode());

            BigDecimal zoneProfit = marginForUnitPrice
                    .multiply(totalElectricityUnitConsumptionForZoneCode);
            profitForAllZones = profitForAllZones.add(zoneProfit.divide(new BigDecimal(1000), 2, RoundingMode.HALF_EVEN));
        }
        //Total profit from trade fee
      /*  BigDecimal invoiceNumbers = new BigDecimal(totalConsumptionSummary.getInvoiceNumbers());
        BigDecimal proposalTotalTradeFee = invoiceNumbers.multiply(proposalSeller.getProposalTradeFee());
        BigDecimal actualTotalTradeFee = invoiceNumbers.multiply(actualReceiverPointFees.getActualTradeFee());

        profitForAllZones = profitForAllZones.add(proposalTotalTradeFee.subtract(actualTotalTradeFee)); */

        Integer totalNumberOfDaysForAllPeriods = totalConsumptionSummary.getTotalNumberOfDaysForAllPeriods();
        BigDecimal estimatedContractValueInYearScale = profitForAllZones
                .divide(new BigDecimal(totalNumberOfDaysForAllPeriods), 2, RoundingMode.HALF_EVEN)
                .multiply(new BigDecimal(365));
        return estimatedContractValueInYearScale;
    }


    private BigDecimal getTotalUnitConsumptionForZoneCode(TotalConsumptionSummary totalConsumptionSummary, String expectedZoneCode) {
        return totalConsumptionSummary.getZoneTotalConsumptionSummaryList()
                .stream()
                .filter(x -> expectedZoneCode.equals(x.getActualZoneCode()))
                .findFirst()
                .map(ZoneTotalConsumptionSummary::getTotalUnitConsumption)
                .orElseThrow(() -> new RuntimeException("Did not fount total Unit consumption for expected ZoneCode:" + expectedZoneCode));
    }

    private BigDecimal calculateEstimatedContractProfitValue(BigDecimal estimatedContractProfitValueInYearScale, BigDecimal proposalContractMonthLength) {
        return estimatedContractProfitValueInYearScale
                .divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_EVEN)
                .multiply(proposalContractMonthLength);
    }


    private BigDecimal calculateEstimatedSavingsInYearScale(ProposalSeller proposalSeller,
                                                            ActualReceiverPointFees actualReceiverPointFees,
                                                            TotalConsumptionSummary totalConsumptionSummary) {
        BigDecimal invoiceNumbers = new BigDecimal(totalConsumptionSummary.getInvoiceNumbers());
        List<ZoneTotalConsumptionSummary> zoneTotalConsumptionSummaryList = totalConsumptionSummary.getZoneTotalConsumptionSummaryList();

        BigDecimal totalActualCostForAllUnitsConsumption = BigDecimal.ZERO;
        for (ZoneTotalConsumptionSummary zoneTotalConsumptionSummary : zoneTotalConsumptionSummaryList) {
            BigDecimal totalZoneUnitConsumption = zoneTotalConsumptionSummary.getTotalUnitConsumption();
            BigDecimal actualUnitPriceForZoneCode =
                    getActualUnitPriceForZoneCode(actualReceiverPointFees, zoneTotalConsumptionSummary.getActualZoneCode());
            BigDecimal total = totalZoneUnitConsumption
                    .divide(new BigDecimal(1000), 2, RoundingMode.HALF_EVEN)
                    .multiply(actualUnitPriceForZoneCode);
            totalActualCostForAllUnitsConsumption = totalActualCostForAllUnitsConsumption.add(total);
        }
        BigDecimal actualTotalTradeFee = invoiceNumbers.multiply(actualReceiverPointFees.getActualTradeFee());
        totalActualCostForAllUnitsConsumption = totalActualCostForAllUnitsConsumption.add(actualTotalTradeFee);
        totalConsumptionSummary.setTotalActualCostForAllUnitsConsumption(totalActualCostForAllUnitsConsumption);

        BigDecimal totalCostForAllUnitsConsumptionBasedOnProposal = BigDecimal.ZERO;
        List<ProposalZoneDetails> proposalZoneDetailsList = proposalSeller.getProposalZoneDetailsList();
        for (ProposalZoneDetails proposalZoneDetails : proposalZoneDetailsList) {
            String actualZoneCode = proposalZoneDetails.getActualZoneCode();
            BigDecimal totalUnitConsumptionForZoneCode = getTotalUnitConsumptionForZoneCode(totalConsumptionSummary, actualZoneCode);
            BigDecimal proposalUnitPrice = proposalZoneDetails.getProposalUnitPrice();
            BigDecimal total = totalUnitConsumptionForZoneCode
                    .divide(new BigDecimal(1000), 2, RoundingMode.HALF_EVEN)
                    .multiply(proposalUnitPrice);
            totalCostForAllUnitsConsumptionBasedOnProposal = totalCostForAllUnitsConsumptionBasedOnProposal.add(total);
        }
        BigDecimal proposalTradeFee = proposalSeller.getProposalTradeFee();
        BigDecimal proposalTotalTradeFee = invoiceNumbers.multiply(proposalTradeFee);
        totalCostForAllUnitsConsumptionBasedOnProposal = totalCostForAllUnitsConsumptionBasedOnProposal.add(proposalTotalTradeFee);
        totalConsumptionSummary.setTotalCostForAllUnitsConsumptionBasedOnProposal(totalCostForAllUnitsConsumptionBasedOnProposal);


        BigDecimal totalNumberOfDaysForAllPeriods = new BigDecimal(totalConsumptionSummary.getTotalNumberOfDaysForAllPeriods());
        return totalActualCostForAllUnitsConsumption
                .subtract(totalCostForAllUnitsConsumptionBasedOnProposal)
                .divide(totalNumberOfDaysForAllPeriods, 2, RoundingMode.HALF_EVEN)
                .multiply(new BigDecimal(365));
    }


    private BigDecimal getActualUnitPriceForZoneCode(ActualReceiverPointFees actualReceiverPointFees, String expectedZoneCode) {
        return actualReceiverPointFees.getActualZoneFeeList()
                .stream()
                .filter(x -> expectedZoneCode.equals(x.getActualZoneCode()))
                .findFirst()
                .map(ActualZoneFee::getActualUnitPrice)
                .orElseThrow(() -> new RuntimeException("Did not fount actual Unit price for expected ZoneCode:" + expectedZoneCode));
    }

    private BigDecimal calculateEstimatedSavingsInContractScale(BigDecimal estimatedSavingsInYearScale, BigDecimal proposalContractMonthLength) {
        BigDecimal year = new BigDecimal(12);
        return estimatedSavingsInYearScale
                .divide(year, 2, BigDecimal.ROUND_UNNECESSARY)
                .multiply(proposalContractMonthLength);
    }

    private BigDecimal calculateEstimatedSavingsInPercentage(TotalConsumptionSummary totalConsumptionSummary) {
        BigDecimal ratio = totalConsumptionSummary.getTotalCostForAllUnitsConsumptionBasedOnProposal()
                .divide(totalConsumptionSummary.getTotalActualCostForAllUnitsConsumption(), 2, BigDecimal.ROUND_HALF_EVEN);
        return (BigDecimal.ONE.subtract(ratio)).multiply(new BigDecimal(100));
    }


    //Calculating ReceiverPointProvisionList
    private List<ReceiverPointProvision> calculateReceiverPointProvisionList() {
        List<ReceiverPointProvision> receiverPointProvisionList = new ArrayList<>();

        return receiverPointProvisionList;
    }

}
