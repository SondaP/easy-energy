package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation;

import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.DefaultZoneParams;

import java.math.BigDecimal;
import java.util.List;

public class OfferParameters {

    private BigDecimal proposalContractMonthLength;
    private BigDecimal defaultProposalTradeFee;
    private List<DefaultZoneParams> defaultZoneParamsList;
    private boolean defaultZoneCodesSameAsActual;

    public BigDecimal getProposalContractMonthLength() {
        return proposalContractMonthLength;
    }

    public void setProposalContractMonthLength(BigDecimal proposalContractMonthLength) {
        this.proposalContractMonthLength = proposalContractMonthLength;
    }

    public BigDecimal getDefaultProposalTradeFee() {
        return defaultProposalTradeFee;
    }

    public void setDefaultProposalTradeFee(BigDecimal defaultProposalTradeFee) {
        this.defaultProposalTradeFee = defaultProposalTradeFee;
    }

    public List<DefaultZoneParams> getDefaultZoneParamsList() {
        return defaultZoneParamsList;
    }

    public void setDefaultZoneParamsList(List<DefaultZoneParams> defaultZoneParamsList) {
        this.defaultZoneParamsList = defaultZoneParamsList;
    }

    public boolean isDefaultZoneCodesSameAsActual() {
        return defaultZoneCodesSameAsActual;
    }

    public void setDefaultZoneCodesSameAsActual(boolean defaultZoneCodesSameAsActual) {
        this.defaultZoneCodesSameAsActual = defaultZoneCodesSameAsActual;
    }
}
