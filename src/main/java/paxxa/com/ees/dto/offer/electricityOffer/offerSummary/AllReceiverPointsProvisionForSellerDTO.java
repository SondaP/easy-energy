package paxxa.com.ees.dto.offer.electricityOffer.offerSummary;

import java.math.BigDecimal;

public class AllReceiverPointsProvisionForSellerDTO {

    private String levelCode;
    private BigDecimal provisionInYearScaleForAllPoint;
    private BigDecimal provisionInContractScaleForAllPoint;

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

    public BigDecimal getProvisionInContractScaleForAllPoint() {
        return provisionInContractScaleForAllPoint;
    }

    public void setProvisionInContractScaleForAllPoint(BigDecimal provisionInContractScaleForAllPoint) {
        this.provisionInContractScaleForAllPoint = provisionInContractScaleForAllPoint;
    }
}
