package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;

public class ProposalTariff {

    private String actualTariffCode;
    private BigDecimal proposalUnitPrice;
    private String proposalTariffCode;

    public String getActualTariffCode() {
        return actualTariffCode;
    }

    public void setActualTariffCode(String actualTariffCode) {
        this.actualTariffCode = actualTariffCode;
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
