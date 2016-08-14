package paxxa.com.ees.entity.seller;

import paxxa.com.ees.entity.company.Company;
import paxxa.com.ees.entity.ratingDefinition.RatingDefinition;

import javax.persistence.*;
import java.util.List;

@Entity
public class Seller {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String sellerCode;
    private boolean enabled;

    @OneToOne
    @JoinColumn
    private Company company;

    @OneToMany
    @JoinColumn(name = "FK_RATING_DEFINITION")
    private List<RatingDefinition> ratingDefinitionList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
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

    public List<RatingDefinition> getRatingDefinitionList() {
        return ratingDefinitionList;
    }

    public void setRatingDefinitionList(List<RatingDefinition> ratingDefinitionList) {
        this.ratingDefinitionList = ratingDefinitionList;
    }
}
