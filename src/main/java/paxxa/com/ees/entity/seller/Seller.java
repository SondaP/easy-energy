package paxxa.com.ees.entity.seller;

import paxxa.com.ees.entity.company.Company;

import javax.persistence.*;

@Entity
@Table(name = "SELLER")
public class Seller {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, unique = true)
    private String sellerName;
    private boolean enabled = true;

    @OneToOne
    @JoinColumn
    private Company company;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerCode) {
        this.sellerName = sellerCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


}
