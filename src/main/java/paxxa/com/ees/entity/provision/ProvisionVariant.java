package paxxa.com.ees.entity.provision;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "PROVISION_VARIANT")
public class ProvisionVariant {

    @Id
    @GeneratedValue
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

    public void setProvisionLevelDescription(String provisionLevelCode) {
        this.provisionLevelDescription = provisionLevelCode;
    }

    public BigDecimal getProvisionPercentageValue() {
        return provisionPercentageValue;
    }

    public void setProvisionPercentageValue(BigDecimal provisionPercentageValue) {
        this.provisionPercentageValue = provisionPercentageValue;
    }
}
