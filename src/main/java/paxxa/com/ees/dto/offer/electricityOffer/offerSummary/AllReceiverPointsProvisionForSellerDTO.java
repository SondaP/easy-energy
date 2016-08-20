package paxxa.com.ees.dto.offer.electricityOffer.offerSummary;

import java.math.BigDecimal;

public class AllReceiverPointsProvisionForSellerDTO {

    private String levelCode;
    private BigDecimal traderProvisionInYearScaleForAllPoint;
    private BigDecimal traderProvisionInContractScaleForAllPoint;
    private BigDecimal partnerProvisionInContractScaleForAllPoint;

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public BigDecimal getTraderProvisionInYearScaleForAllPoint() {
        return traderProvisionInYearScaleForAllPoint;
    }

    public void setTraderProvisionInYearScaleForAllPoint(BigDecimal traderProvisionInYearScaleForAllPoint) {
        this.traderProvisionInYearScaleForAllPoint = traderProvisionInYearScaleForAllPoint;
    }

    public BigDecimal getTraderProvisionInContractScaleForAllPoint() {
        return traderProvisionInContractScaleForAllPoint;
    }

    public void setTraderProvisionInContractScaleForAllPoint(BigDecimal traderProvisionInContractScaleForAllPoint) {
        this.traderProvisionInContractScaleForAllPoint = traderProvisionInContractScaleForAllPoint;
    }

    public BigDecimal getPartnerProvisionInContractScaleForAllPoint() {
        return partnerProvisionInContractScaleForAllPoint;
    }

    public void setPartnerProvisionInContractScaleForAllPoint(BigDecimal partnerProvisionInContractScaleForAllPoint) {
        this.partnerProvisionInContractScaleForAllPoint = partnerProvisionInContractScaleForAllPoint;
    }
}
