package paxxa.com.ees.dto.offer.electricityOffer.offerSummary;

import java.util.List;

public class OfferSummaryDTO {

    private List<AllReceiverPointsEstimationDTO> electricityReceiverPointEstimationList;

    public List<AllReceiverPointsEstimationDTO> getElectricityReceiverPointEstimationList() {
        return electricityReceiverPointEstimationList;
    }

    public void setElectricityReceiverPointEstimationList(List<AllReceiverPointsEstimationDTO> electricityReceiverPointEstimationList) {
        this.electricityReceiverPointEstimationList = electricityReceiverPointEstimationList;
    }
}
