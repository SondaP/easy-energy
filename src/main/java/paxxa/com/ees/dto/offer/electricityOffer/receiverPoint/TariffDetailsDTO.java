package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;

public class TariffDetailsDTO {

    private String tariffCode;
    private BigDecimal totalUnitConsumptionFromPeriods;
    private BigDecimal actualUnitPrice;
    private BigDecimal actualShareOfTotalReceiverPointConsumption;
    private String proposalOfferCode;
    private BigDecimal predictedShareOfTotalReceiverPointConsumption;
    private BigDecimal proposalUnitPrice;

    public String getTariffCode() {
        return tariffCode;
    }

    public void setTariffCode(String tariffCode) {
        this.tariffCode = tariffCode;
    }

    public BigDecimal getTotalUnitConsumptionFromPeriods() {
        return totalUnitConsumptionFromPeriods;
    }

    public void setTotalUnitConsumptionFromPeriods(BigDecimal totalUnitConsumptionFromPeriods) {
        this.totalUnitConsumptionFromPeriods = totalUnitConsumptionFromPeriods;
    }

    public BigDecimal getActualUnitPrice() {
        return actualUnitPrice;
    }

    public void setActualUnitPrice(BigDecimal actualUnitPrice) {
        this.actualUnitPrice = actualUnitPrice;
    }

    public BigDecimal getActualShareOfTotalReceiverPointConsumption() {
        return actualShareOfTotalReceiverPointConsumption;
    }

    public void setActualShareOfTotalReceiverPointConsumption(BigDecimal actualShareOfTotalReceiverPointConsumption) {
        this.actualShareOfTotalReceiverPointConsumption = actualShareOfTotalReceiverPointConsumption;
    }

    public String getProposalOfferCode() {
        return proposalOfferCode;
    }

    public void setProposalOfferCode(String proposalOfferCode) {
        this.proposalOfferCode = proposalOfferCode;
    }

    public BigDecimal getPredictedShareOfTotalReceiverPointConsumption() {
        return predictedShareOfTotalReceiverPointConsumption;
    }

    public void setPredictedShareOfTotalReceiverPointConsumption(BigDecimal predictedShareOfTotalReceiverPointConsumption) {
        this.predictedShareOfTotalReceiverPointConsumption = predictedShareOfTotalReceiverPointConsumption;
    }

    public BigDecimal getProposalUnitPrice() {
        return proposalUnitPrice;
    }

    public void setProposalUnitPrice(BigDecimal proposalUnitPrice) {
        this.proposalUnitPrice = proposalUnitPrice;
    }
}
