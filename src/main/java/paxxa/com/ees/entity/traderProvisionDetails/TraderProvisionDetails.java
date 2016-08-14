package paxxa.com.ees.entity.traderProvisionDetails;

import paxxa.com.ees.entity.user.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class TraderProvisionDetails {

    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne
    @JoinColumn(name = "FK_USER")
    private User user;
    private String productCode;
    private String sellerCode;
    private String provisionLevelCode;
    private BigDecimal provisionPercentageValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
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
