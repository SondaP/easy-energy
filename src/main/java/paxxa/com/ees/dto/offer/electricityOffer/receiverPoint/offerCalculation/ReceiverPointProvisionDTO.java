package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation;

import java.math.BigDecimal;

public class ReceiverPointProvisionDTO {

    private String levelCode;
    private BigDecimal traderProvisionInYearScale;
    private BigDecimal traderProvisionInContractScale;
    private BigDecimal partnerProvisionInContractScale;

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public BigDecimal getTraderProvisionInYearScale() {
        return traderProvisionInYearScale;
    }

    public void setTraderProvisionInYearScale(BigDecimal traderProvisionInYearScale) {
        this.traderProvisionInYearScale = traderProvisionInYearScale;
    }

    public BigDecimal getTraderProvisionInContractScale() {
        return traderProvisionInContractScale;
    }

    public void setTraderProvisionInContractScale(BigDecimal traderProvisionInContractScale) {
        this.traderProvisionInContractScale = traderProvisionInContractScale;
    }

    public BigDecimal getPartnerProvisionInContractScale() {
        return partnerProvisionInContractScale;
    }

    public void setPartnerProvisionInContractScale(BigDecimal partnerProvisionInContractScale) {
        this.partnerProvisionInContractScale = partnerProvisionInContractScale;
    }
}
