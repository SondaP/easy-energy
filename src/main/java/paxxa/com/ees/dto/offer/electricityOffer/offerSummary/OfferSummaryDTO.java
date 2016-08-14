package paxxa.com.ees.dto.offer.electricityOffer.offerSummary;

import java.util.List;

public class OfferSummaryDTO {

    private List<AllReceiverPointsEstimationForSellerDTO> electricityReceiverPointEstimationList;

    public List<AllReceiverPointsEstimationForSellerDTO> getElectricityReceiverPointEstimationList() {
        return electricityReceiverPointEstimationList;
    }

    public void setElectricityReceiverPointEstimationList(List<AllReceiverPointsEstimationForSellerDTO> electricityReceiverPointEstimationList) {
        this.electricityReceiverPointEstimationList = electricityReceiverPointEstimationList;
    }
}
