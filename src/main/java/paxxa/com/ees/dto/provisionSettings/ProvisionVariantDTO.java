package paxxa.com.ees.dto.provisionSettings;

import java.math.BigDecimal;

public class ProvisionVariantDTO {

    private Integer id;
    private String provisionLevelDescription;
    private BigDecimal provisionPercentageValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
