package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.springframework.beans.factory.annotation.Autowired;
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


    public OfferSummaryDTO calculateAllReceiverPointsEstimationForSellerList(final List<ReceiverPoint> receiverPointList,
                                                                             final String userName) {
        OfferSummaryDTO offerSummaryDTO = new OfferSummaryDTO();

        HashMap<String, AllReceiverPointsDataEstimationForSeller> cache = new HashMap<>();

        for (ReceiverPoint receiverPoint : receiverPointList) {
            List<ProposalSeller> proposalSellerList = receiverPoint.getReceiverPointOfferCalculation()
                    .getProposalSellerList();
            for (ProposalSeller proposalSeller : proposalSellerList) {
                String sellerCode = proposalSeller.getSellerCode();

                ReceiverPointEstimation receiverPointEstimation = proposalSeller.getReceiverPointEstimation();
                ReceiverPointDataEstimation receiverPointDataEstimation = receiverPointEstimation
                        .getReceiverPointDataEstimation();

                updateCache(proposalSeller.getSellerCode(), cache, receiverPointDataEstimation);
            }
        }
        List<AllReceiverPointsEstimationForSeller> allReceiverPointsEstimationForSellerList =
                extractAllReceiverPointsEstimationForSellerList(cache, userName);
        offerSummaryDTO.setReceiverPointEstimationList(allReceiverPointsEstimationForSellerList);
        return offerSummaryDTO;
    }

    private void updateCache(String sellerCode,
                             HashMap<String, AllReceiverPointsDataEstimationForSeller> cache,
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

            cache.put(sellerCode, allReceiverPointsDataEstimationForSeller);
        }
    }

    private List<AllReceiverPointsEstimationForSeller> extractAllReceiverPointsEstimationForSellerList(
            HashMap<String, AllReceiverPointsDataEstimationForSeller> cache, final String userName) {
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

    private List<AllReceiverPointsProvisionForSeller> calculateProvision(final BigDecimal totalEstimatedContractProfitValue,
                                                                         final BigDecimal totalEstimatedContractProfitValueInYearScale,
                                                                         final String sellerName,
                                                                         final String userName) {
        return electricityOfferProvisionService.calculateAllReceiverPointProvisionList(totalEstimatedContractProfitValue,
                totalEstimatedContractProfitValueInYearScale, sellerName, userName);
    }
}
