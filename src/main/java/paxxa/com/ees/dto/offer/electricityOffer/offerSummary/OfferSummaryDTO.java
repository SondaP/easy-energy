package paxxa.com.ees.dto.offer.electricityOffer.offerSummary;

import java.util.List;

public class OfferSummaryDTO {

    private List<AllReceiverPointsEstimationForSeller> electricityReceiverPointEstimationList;

    public List<AllReceiverPointsEstimationForSeller> getElectricityReceiverPointEstimationList() {
        return electricityReceiverPointEstimationList;
    }

    public void setElectricityReceiverPointEstimationList(List<AllReceiverPointsEstimationForSeller> electricityReceiverPointEstimationList) {
        this.electricityReceiverPointEstimationList = electricityReceiverPointEstimationList;
    }
}
