package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.AllReceiverPointsDataEstimationForSeller;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.AllReceiverPointsEstimationForSeller;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.AllReceiverPointsProvisionForSeller;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.OfferSummaryDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ReceiverPoint;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.ProposalSeller;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.ReceiverPointDataEstimation;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.ReceiverPointEstimation;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.TotalConsumptionSummary;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ElectricityOfferAllReceiverPointsSummaryService {

    @Autowired
    private ElectricityOfferProvisionService electricityOfferProvisionService;


    public OfferSummaryDTO calculateOfferTotalSummary(final List<ReceiverPoint> receiverPointList,
                                                      final String userName) {
        OfferSummaryDTO offerSummaryDTO = new OfferSummaryDTO();

        HashMap<String, AllReceiverPointsDataEstimationForSeller> cache_AllPintsDataEstimation = new HashMap<>();
        HashMap<String, TotalConsumptionSummary> cache_TotalConsumptionSummary = new HashMap<>();

        for (ReceiverPoint receiverPoint : receiverPointList) {
            List<ProposalSeller> proposalSellerList = receiverPoint.getReceiverPointOfferCalculation()
                    .getProposalSellerList();
            for (ProposalSeller proposalSeller : proposalSellerList) {
                String sellerCode = proposalSeller.getSellerCode();

                //
                updateCache_TotalConsumptionSummary(
                        proposalSeller.getSellerCode(),
                        cache_TotalConsumptionSummary,
                        receiverPoint.getReceiverPointOfferCalculation().getTotalConsumptionSummary());

                //
                ReceiverPointEstimation receiverPointEstimation = proposalSeller.getReceiverPointEstimation();
                ReceiverPointDataEstimation receiverPointDataEstimation = receiverPointEstimation
                        .getReceiverPointDataEstimation();
                updateCache_AllPointsDataEstimation(proposalSeller.getSellerCode(),
                        cache_AllPintsDataEstimation, cache_TotalConsumptionSummary, receiverPointDataEstimation);



            }
        }
        List<AllReceiverPointsEstimationForSeller> allReceiverPointsEstimationForSellerList =
                extractAllReceiverPointsEstimationForSellerList(cache_AllPintsDataEstimation, userName);
        offerSummaryDTO.setReceiverPointEstimationList(allReceiverPointsEstimationForSellerList);
        return offerSummaryDTO;
    }

    // Dla przypadku kiedy parametry ofwrty mamy na jednym obiekcie
    public OfferSummaryDTO calculateOfferTotalSummary_caseOneData(final List<ProposalSeller> proposalSellerList,
                                                                  final String userName) {
        OfferSummaryDTO offerSummaryDTO = new OfferSummaryDTO();
        HashMap<String, AllReceiverPointsDataEstimationForSeller> cache_AllPintsDataEstimation = new HashMap<>();
        HashMap<String, TotalConsumptionSummary> cache_TotalConsumptionSummary = new HashMap<>();

        for (ProposalSeller proposalSeller : proposalSellerList) {
            String sellerCode = proposalSeller.getSellerCode();

            ReceiverPointEstimation receiverPointEstimation = proposalSeller.getReceiverPointEstimation();
            ReceiverPointDataEstimation receiverPointDataEstimation = receiverPointEstimation
                    .getReceiverPointDataEstimation();

            updateCache_AllPointsDataEstimation(proposalSeller.getSellerCode(), cache_AllPintsDataEstimation,
                    cache_TotalConsumptionSummary, receiverPointDataEstimation);

        }

        List<AllReceiverPointsEstimationForSeller> allReceiverPointsEstimationForSellerList =
                extractAllReceiverPointsEstimationForSellerList(cache_AllPintsDataEstimation, userName);
        offerSummaryDTO.setReceiverPointEstimationList(allReceiverPointsEstimationForSellerList);
        return offerSummaryDTO;
    }


    private void updateCache_AllPointsDataEstimation(String sellerCode,
                                                     HashMap<String, AllReceiverPointsDataEstimationForSeller> cache,
                                                     HashMap<String, TotalConsumptionSummary> cache_TotalConsumptionSummary,
                                                     ReceiverPointDataEstimation receiverPointDataEstimation) {
        if (cache.get(sellerCode) != null) {
            AllReceiverPointsDataEstimationForSeller allReceiverPointsDataEstimationForSeller = cache.get(sellerCode);
            //estimatedContractValueForAllPoint
            BigDecimal estimatedContractValueForAllPoint = allReceiverPointsDataEstimationForSeller
                    .getEstimatedContractValueForAllPoint();
            BigDecimal estimatedContractValueForAllPoint_updated = estimatedContractValueForAllPoint
                    .add(receiverPointDataEstimation.getEstimatedContractProfitValue());
            allReceiverPointsDataEstimationForSeller
                    .setEstimatedContractValueForAllPoint(estimatedContractValueForAllPoint_updated);
            //estimatedContractValueInYearScaleForAllPoint
            BigDecimal estimatedContractValueInYearScaleForAllPoint = allReceiverPointsDataEstimationForSeller
                    .getEstimatedContractValueInYearScaleForAllPoint();
            BigDecimal estimatedContractProfitValueInYearScale_updated = estimatedContractValueInYearScaleForAllPoint
                    .add(receiverPointDataEstimation.getEstimatedContractProfitValueInYearScale());
            allReceiverPointsDataEstimationForSeller.setEstimatedContractValueInYearScaleForAllPoint(
                    estimatedContractProfitValueInYearScale_updated);
            //estimatedSavingsInContractScaleForAllPoint
            BigDecimal estimatedSavingsInContractScaleForAllPoint = allReceiverPointsDataEstimationForSeller
                    .getEstimatedSavingsInContractScaleForAllPoint();
            BigDecimal estimatedSavingsInContractScaleForAllPoint_updated = estimatedSavingsInContractScaleForAllPoint
                    .add(receiverPointDataEstimation.getEstimatedSavingsInContractScale());
            allReceiverPointsDataEstimationForSeller.setEstimatedSavingsInContractScaleForAllPoint(
                    estimatedSavingsInContractScaleForAllPoint_updated);
            //estimatedSavingsInYearScaleForAllPoint
            BigDecimal estimatedSavingsInYearScaleForAllPoint = allReceiverPointsDataEstimationForSeller
                    .getEstimatedSavingsInYearScaleForAllPoint();
            BigDecimal estimatedSavingsInYearScaleForAllPoint_updated = estimatedSavingsInYearScaleForAllPoint
                    .add(receiverPointDataEstimation.getEstimatedSavingsInYearScale());
            allReceiverPointsDataEstimationForSeller.setEstimatedSavingsInYearScaleForAllPoint(
                    estimatedSavingsInYearScaleForAllPoint_updated);
            //estimatedSavingsInPercentageForAllPoint
            allReceiverPointsDataEstimationForSeller.setEstimatedSavingsInPercentageForAllPoint(
                    calculateSavingsInPercentage(cache_TotalConsumptionSummary, sellerCode));

        } else {
            AllReceiverPointsDataEstimationForSeller allReceiverPointsDataEstimationForSeller =
                    new AllReceiverPointsDataEstimationForSeller();
            //estimatedContractValueForAllPoint
            allReceiverPointsDataEstimationForSeller.setEstimatedContractValueForAllPoint(
                    receiverPointDataEstimation.getEstimatedContractProfitValue());
            //estimatedContractValueInYearScaleForAllPoint
            allReceiverPointsDataEstimationForSeller.setEstimatedContractValueInYearScaleForAllPoint(
                    receiverPointDataEstimation.getEstimatedContractProfitValueInYearScale());
            //estimatedSavingsInContractScaleForAllPoint
            allReceiverPointsDataEstimationForSeller.setEstimatedSavingsInContractScaleForAllPoint(
                    receiverPointDataEstimation.getEstimatedSavingsInContractScale());
            //estimatedSavingsInYearScaleForAllPoint
            allReceiverPointsDataEstimationForSeller.setEstimatedSavingsInYearScaleForAllPoint(
                    receiverPointDataEstimation.getEstimatedSavingsInYearScale());
            //estimatedSavingsInPercentageForAllPoint
            allReceiverPointsDataEstimationForSeller.setEstimatedSavingsInPercentageForAllPoint(
                    calculateSavingsInPercentage(cache_TotalConsumptionSummary, sellerCode));

            cache.put(sellerCode, allReceiverPointsDataEstimationForSeller);
        }
    }

    private List<AllReceiverPointsEstimationForSeller> extractAllReceiverPointsEstimationForSellerList(
            HashMap<String, AllReceiverPointsDataEstimationForSeller> cache,
            final String userName) {
        List<AllReceiverPointsEstimationForSeller> allReceiverPointsDataEstimationForSellerList = new ArrayList<>();

        for (Map.Entry entry : cache.entrySet()) {
            AllReceiverPointsEstimationForSeller allReceiverPointsEstimationForSeller = new AllReceiverPointsEstimationForSeller();
            String sellerCode = (String) entry.getKey();
            allReceiverPointsEstimationForSeller.setSellerCode(sellerCode);
            AllReceiverPointsDataEstimationForSeller allReceiverPointsDataEstimationForSeller =
                    (AllReceiverPointsDataEstimationForSeller) entry.getValue();


            allReceiverPointsEstimationForSeller.setAllReceiverPointsDataEstimationForSeller(allReceiverPointsDataEstimationForSeller);

            List<AllReceiverPointsProvisionForSeller> allReceiverPointsProvisionForSellers_calculated = calculateProvision(
                    allReceiverPointsDataEstimationForSeller.getEstimatedContractValueForAllPoint(),
                    allReceiverPointsDataEstimationForSeller.getEstimatedContractValueInYearScaleForAllPoint(),
                    sellerCode,
                    userName);
            allReceiverPointsEstimationForSeller.setAllReceiverPointsProvisionForSellerList(allReceiverPointsProvisionForSellers_calculated);

            allReceiverPointsDataEstimationForSellerList.add(allReceiverPointsEstimationForSeller);
        }

        return allReceiverPointsDataEstimationForSellerList;
    }

    private BigDecimal calculateSavingsInPercentage(HashMap<String, TotalConsumptionSummary> cache_TotalConsumptionSummary,
                                                    String sellerCode) {
        TotalConsumptionSummary totalConsumptionSummary = cache_TotalConsumptionSummary.get(sellerCode);
        return calculateEstimatedSavingsInPercentage(totalConsumptionSummary);
    }

    private BigDecimal calculateEstimatedSavingsInPercentage(TotalConsumptionSummary totalConsumptionSummary) {
        BigDecimal ratio = totalConsumptionSummary.getTotalCostForAllUnitsConsumptionBasedOnProposal()
                .divide(totalConsumptionSummary.getTotalActualCostForAllUnitsConsumption(), 4, BigDecimal.ROUND_HALF_EVEN);
        return (BigDecimal.ONE.subtract(ratio)).multiply(new BigDecimal(100));
    }

    private void updateCache_TotalConsumptionSummary(String sellerCode,
                                                     HashMap<String, TotalConsumptionSummary> cache_TotalConsumptionSummary,
                                                     TotalConsumptionSummary totalConsumptionSummary) {
        if (cache_TotalConsumptionSummary.get(sellerCode) != null) {
            TotalConsumptionSummary totalConsumptionSummary_cache = cache_TotalConsumptionSummary.get(sellerCode);
            //totalCostForAllUnitsConsumptionBasedOnProposal
            BigDecimal totalCostForAllUnitsConsumptionBasedOnProposal = totalConsumptionSummary_cache
                    .getTotalCostForAllUnitsConsumptionBasedOnProposal();
            BigDecimal totalCostForAllUnitsConsumptionBasedOnProposal_updated =
                    totalCostForAllUnitsConsumptionBasedOnProposal.add(totalConsumptionSummary
                            .getTotalCostForAllUnitsConsumptionBasedOnProposal());
            totalConsumptionSummary_cache.setTotalCostForAllUnitsConsumptionBasedOnProposal(
                    totalCostForAllUnitsConsumptionBasedOnProposal_updated);
            //totalActualCostForAllUnitsConsumption
            BigDecimal totalActualCostForAllUnitsConsumption = totalConsumptionSummary_cache
                    .getTotalActualCostForAllUnitsConsumption();
            BigDecimal totalActualCostForAllUnitsConsumption_updated = totalActualCostForAllUnitsConsumption
                    .add(totalConsumptionSummary.getTotalActualCostForAllUnitsConsumption());
            totalConsumptionSummary_cache.setTotalActualCostForAllUnitsConsumption(totalActualCostForAllUnitsConsumption_updated);

            cache_TotalConsumptionSummary.put(sellerCode, totalConsumptionSummary_cache);
        } else {
            //totalCostForAllUnitsConsumptionBasedOnProposal
            TotalConsumptionSummary totalConsumptionSummary_cache = new TotalConsumptionSummary();
            totalConsumptionSummary_cache.setTotalCostForAllUnitsConsumptionBasedOnProposal(totalConsumptionSummary
                    .getTotalCostForAllUnitsConsumptionBasedOnProposal());
            //totalActualCostForAllUnitsConsumption
            totalConsumptionSummary_cache.setTotalActualCostForAllUnitsConsumption(totalConsumptionSummary
                    .getTotalActualCostForAllUnitsConsumption());

            cache_TotalConsumptionSummary.put(sellerCode, totalConsumptionSummary_cache);
        }

    }

    private List<AllReceiverPointsProvisionForSeller> calculateProvision(final BigDecimal totalEstimatedContractProfitValue,
                                                                         final BigDecimal totalEstimatedContractProfitValueInYearScale,
                                                                         final String sellerName,
                                                                         final String userName) {
        return electricityOfferProvisionService.calculateAllReceiverPointProvisionList(totalEstimatedContractProfitValue,
                totalEstimatedContractProfitValueInYearScale, sellerName, userName);
    }
}
