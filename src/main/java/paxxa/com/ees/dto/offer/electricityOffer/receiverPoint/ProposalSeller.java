package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProposalSeller {

    private String sellerCode;
    private BigDecimal proposalTradeFee;
    private Date sellerTariffPublicationDate;
    private List<ProposalTariff> proposalTariffList;

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
}
