package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation;

import java.util.List;

public class OfferCalculation {

    private TotalConsumptionSummaryDTO totalConsumptionSummaryDTO;
    private ActualReceiverPointFees actualReceiverPointFees;
    private OfferParameters offerParameters;

    private List<ProposalSeller> proposalSellerList;
    private List<ReceiverPointEstimationDTO> receiverPointEstimationList;


    public TotalConsumptionSummaryDTO getTotalConsumptionSummaryDTO() {
        return totalConsumptionSummaryDTO;
    }

    public void setTotalConsumptionSummaryDTO(TotalConsumptionSummaryDTO totalConsumptionSummaryDTO) {
        this.totalConsumptionSummaryDTO = totalConsumptionSummaryDTO;
    }

    public ActualReceiverPointFees getActualReceiverPointFees() {
        return actualReceiverPointFees;
    }

    public void setActualReceiverPointFees(ActualReceiverPointFees actualReceiverPointFees) {
        this.actualReceiverPointFees = actualReceiverPointFees;
    }

    public OfferParameters getOfferParameters() {
        return offerParameters;
    }

    public void setOfferParameters(OfferParameters offerParameters) {
        this.offerParameters = offerParameters;
    }

    public List<ProposalSeller> getProposalSellerList() {
        return proposalSellerList;
    }

    public void setProposalSellerList(List<ProposalSeller> proposalSellerList) {
        this.proposalSellerList = proposalSellerList;
    }

    public List<ReceiverPointEstimationDTO> getReceiverPointEstimationList() {
        return receiverPointEstimationList;
    }

    public void setReceiverPointEstimationList(List<ReceiverPointEstimationDTO> receiverPointEstimationList) {
        this.receiverPointEstimationList = receiverPointEstimationList;
    }
}
