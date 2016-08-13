package paxxa.com.ees.dto.offer.electricityOffer.offerSummary;

import java.math.BigDecimal;

public class AllReceiverPointsProvisionDTO {

    private String levelCode;
    private BigDecimal provisionInYearScaleForAllPoint;
    private BigDecimal getProvisionInContractScaleForAllPoint;

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public BigDecimal getProvisionInYearScaleForAllPoint() {
        return provisionInYearScaleForAllPoint;
    }

    public void setProvisionInYearScaleForAllPoint(BigDecimal provisionInYearScaleForAllPoint) {
        this.provisionInYearScaleForAllPoint = provisionInYearScaleForAllPoint;
    }

    public BigDecimal getGetProvisionInContractScaleForAllPoint() {
        return getProvisionInContractScaleForAllPoint;
    }

    public void setGetProvisionInContractScaleForAllPoint(BigDecimal getProvisionInContractScaleForAllPoint) {
        this.getProvisionInContractScaleForAllPoint = getProvisionInContractScaleForAllPoint;
    }
}
