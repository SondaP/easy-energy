package paxxa.com.ees.entity.provision;

import paxxa.com.ees.entity.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PROVISION_CONDITIONS")
public class ProvisionConditions {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "FK_USER")
    private User user;

    private String productCode;
    private String sellerCode;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ProvisionVariant.class)
    private List<ProvisionVariant> provisionVariantList;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<ProvisionVariant> getProvisionVariantList() {
        return provisionVariantList;
    }

    public void setProvisionVariantList(List<ProvisionVariant> provisionVariantList) {
        this.provisionVariantList = provisionVariantList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
