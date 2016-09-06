package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation;

import java.util.List;

public class OfferCalculation {

    private TotalConsumptionSummary totalConsumptionSummary;
    private ActualReceiverPointFees actualReceiverPointFees;
    private OfferParameters offerParameters;
    private List<ProposalSeller> proposalSellerList;


    public TotalConsumptionSummary getTotalConsumptionSummary() {
        return totalConsumptionSummary;
    }

    public void setTotalConsumptionSummary(TotalConsumptionSummary totalConsumptionSummary) {
        this.totalConsumptionSummary = totalConsumptionSummary;
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

}
