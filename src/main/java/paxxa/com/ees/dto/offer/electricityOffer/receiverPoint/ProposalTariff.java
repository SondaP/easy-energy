package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;

public class ProposalTariff {

    private String actualZoneCode;
    private BigDecimal sellerMinimalUnitPrice;
    private BigDecimal proposalUnitPrice;
    private String proposalZoneCode;

    public String getActualZoneCode() {
        return actualZoneCode;
    }

    public void setActualZoneCode(String actualZoneCode) {
        this.actualZoneCode = actualZoneCode;
    }

    public BigDecimal getSellerMinimalUnitPrice() {
        return sellerMinimalUnitPrice;
    }

    public void setSellerMinimalUnitPrice(BigDecimal sellerMinimalUnitPrice) {
        this.sellerMinimalUnitPrice = sellerMinimalUnitPrice;
    }

    public BigDecimal getProposalUnitPrice() {
        return proposalUnitPrice;
    }

    public void setProposalUnitPrice(BigDecimal proposalUnitPrice) {
        this.proposalUnitPrice = proposalUnitPrice;
    }

    public String getProposalZoneCode() {
        return proposalZoneCode;
    }

    public void setProposalZoneCode(String proposalZoneCode) {
        this.proposalZoneCode = proposalZoneCode;
    }
}
