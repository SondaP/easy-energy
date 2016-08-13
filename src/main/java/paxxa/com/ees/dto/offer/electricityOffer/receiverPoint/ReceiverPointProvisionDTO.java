package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;

public class ReceiverPointProvisionDTO {

    private String levelCode;
    private BigDecimal provisionInYearScale;
    private BigDecimal getProvisionInContractScale;

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public BigDecimal getProvisionInYearScale() {
        return provisionInYearScale;
    }

    public void setProvisionInYearScale(BigDecimal provisionInYearScale) {
        this.provisionInYearScale = provisionInYearScale;
    }

    public BigDecimal getGetProvisionInContractScale() {
        return getProvisionInContractScale;
    }

    public void setGetProvisionInContractScale(BigDecimal getProvisionInContractScale) {
        this.getProvisionInContractScale = getProvisionInContractScale;
    }
}
