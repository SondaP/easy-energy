package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.AllReceiverPointsDataEstimationForSeller;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.AllReceiverPointsEstimationForSeller;
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


    public OfferSummaryDTO calculateAllReceiverPointsEstimationForSellerList(final List<ReceiverPoint> receiverPointList) {
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
        List<AllReceiverPointsEstimationForSeller> allReceiverPointsEstimationForSellerList = extractAllReceiverPointsEstimationForSellerList(cache);
        offerSummaryDTO.setReceiverPointEstimationList(allReceiverPointsEstimationForSellerList);
        return offerSummaryDTO;
    }

    private void updateCache(String sellerCode,
                             HashMap<String, AllReceiverPointsDataEstimationForSeller> cache,
                             ReceiverPointDataEstimation receiverPointDataEstimation) {
        if (cache.get(sellerCode) != null) {
            AllReceiverPointsDataEstimationForSeller allReceiverPointsDataEstimationForSeller = cache.get(sellerCode);
            BigDecimal estimatedContractValueForAllPoint = allReceiverPointsDataEstimationForSeller
                    .getEstimatedContractValueForAllPoint();

            BigDecimal estimatedContractValueForAllPoint_updated = estimatedContractValueForAllPoint
                    .add(receiverPointDataEstimation.getEstimatedContractProfitValue());
            allReceiverPointsDataEstimationForSeller
                    .setEstimatedContractValueForAllPoint(estimatedContractValueForAllPoint_updated);
        } else {
            AllReceiverPointsDataEstimationForSeller allReceiverPointsDataEstimationForSeller =
                    new AllReceiverPointsDataEstimationForSeller();
            allReceiverPointsDataEstimationForSeller.setEstimatedContractValueForAllPoint(
                    receiverPointDataEstimation.getEstimatedContractProfitValue());


            cache.put(sellerCode, allReceiverPointsDataEstimationForSeller);
        }
    }

    private List<AllReceiverPointsEstimationForSeller> extractAllReceiverPointsEstimationForSellerList(
            HashMap<String, AllReceiverPointsDataEstimationForSeller> cache) {
        List<AllReceiverPointsEstimationForSeller> allReceiverPointsDataEstimationForSellerList = new ArrayList<>();

        for (Map.Entry entry : cache.entrySet()) {
            AllReceiverPointsEstimationForSeller allReceiverPointsEstimationForSeller = new AllReceiverPointsEstimationForSeller();
            allReceiverPointsEstimationForSeller.setSellerCode((String) entry.getKey());
            allReceiverPointsEstimationForSeller.setAllReceiverPointsDataEstimationForSeller(
                    (AllReceiverPointsDataEstimationForSeller) entry.getValue());
            calculateProvision();
            allReceiverPointsDataEstimationForSellerList.add(allReceiverPointsEstimationForSeller);
        }

        return allReceiverPointsDataEstimationForSellerList;
    }

    private void calculateProvision() {

    }
}
