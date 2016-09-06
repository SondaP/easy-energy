package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation;

import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ProposalTariff;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProposalSeller {

    private String sellerCode;
    private BigDecimal proposalTradeFee;
    private Date sellerTariffPublicationDate;
    private List<ProposalTariff> proposalTariffList;
    private ReceiverPointEstimation receiverPointEstimation;

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public BigDecimal getProposalTradeFee() {
        return proposalTradeFee;
    }

    public void setProposalTradeFee(BigDecimal proposalTradeFee) {
        this.proposalTradeFee = proposalTradeFee;
    }

    public Date getSellerTariffPublicationDate() {
        return sellerTariffPublicationDate;
    }

    public void setSellerTariffPublicationDate(Date sellerTariffPublicationDate) {
        this.sellerTariffPublicationDate = sellerTariffPublicationDate;
    }

    public List<ProposalTariff> getProposalTariffList() {
        return proposalTariffList;
    }

    public void setProposalTariffList(List<ProposalTariff> proposalTariffList) {
        this.proposalTariffList = proposalTariffList;
    }

    public ReceiverPointEstimation getReceiverPointEstimation() {
        return receiverPointEstimation;
    }

    public void setReceiverPointEstimation(ReceiverPointEstimation receiverPointEstimation) {
        this.receiverPointEstimation = receiverPointEstimation;
    }
}
