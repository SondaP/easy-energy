package paxxa.com.ees.dto.offer.electricityOffer.offerSummary;

import java.util.List;

public class OfferSummaryDTO {

    private List<AllReceiverPointsEstimationForSeller> receiverPointEstimationList;

    public List<AllReceiverPointsEstimationForSeller> getReceiverPointEstimationList() {
        return receiverPointEstimationList;
    }

    public void setReceiverPointEstimationList(List<AllReceiverPointsEstimationForSeller> receiverPointEstimationList) {
        this.receiverPointEstimationList = receiverPointEstimationList;
    }
}
