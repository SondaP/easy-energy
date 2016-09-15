package paxxa.com.ees.entity.Provision;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ProvisionVariant {

    @Id
    @GeneratedValue
    private Integer id;

    private String provisionLevelCode;
    private BigDecimal provisionPercentageValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvisionLevelCode() {
        return provisionLevelCode;
    }

    public void setProvisionLevelCode(String provisionLevelCode) {
        this.provisionLevelCode = provisionLevelCode;
    }

    public BigDecimal getProvisionPercentageValue() {
        return provisionPercentageValue;
    }

    public void setProvisionPercentageValue(BigDecimal provisionPercentageValue) {
        this.provisionPercentageValue = provisionPercentageValue;
    }
}
