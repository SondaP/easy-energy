package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;

public class ProposalTariff {

    private String actualTariffCode;
    private BigDecimal sellerMinimalUnitPrice;
    private BigDecimal proposalUnitPrice;
    private String proposalTariffCode;

    public String getActualTariffCode() {
        return actualTariffCode;
    }

    public void setActualTariffCode(String actualTariffCode) {
        this.actualTariffCode = actualTariffCode;
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

    public String getProposalTariffCode() {
        return proposalTariffCode;
    }

    public void setProposalTariffCode(String proposalTariffCode) {
        this.proposalTariffCode = proposalTariffCode;
    }
}
