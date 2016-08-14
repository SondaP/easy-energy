package paxxa.com.ees.entity.product;

import paxxa.com.ees.entity.seller.Seller;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String productCode;
    @OneToOne
    @JoinColumn(name = "FK_SELLER")
    private Seller seller;

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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
