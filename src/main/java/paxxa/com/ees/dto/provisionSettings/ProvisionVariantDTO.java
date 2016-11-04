package paxxa.com.ees.dto.provisionSettings;

import java.math.BigDecimal;

public class ProvisionVariantDTO {

    private String provisionLevelDescription;
    private BigDecimal provisionPercentageValue;

    public String getProvisionLevelDescription() {
        return provisionLevelDescription;
    }

    public void setProvisionLevelDescription(String provisionLevelDescription) {
        this.provisionLevelDescription = provisionLevelDescription;
    }

    public BigDecimal getProvisionPercentageValue() {
        return provisionPercentageValue;
    }

    public void setProvisionPercentageValue(BigDecimal provisionPercentageValue) {
        this.provisionPercentageValue = provisionPercentageValue;
    }
}
