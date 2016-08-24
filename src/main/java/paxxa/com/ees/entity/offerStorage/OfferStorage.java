package paxxa.com.ees.entity.offerStorage;

import paxxa.com.ees.entity.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class OfferStorage {

    @Id
    @GeneratedValue
    private Integer id;
    private String productCode;
    private Date creationDate;
    private Date lastEdition;
    private String offerNumber;

    @Lob
    @Column(name = "ABSTRACT_OFFER_DTO")
    private byte[] abstractOfferDTO;

    @OneToOne
    @JoinColumn(name = "FK_USER")
    private User user;

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastEdition() {
        return lastEdition;
    }

    public void setLastEdition(Date lastEdition) {
        this.lastEdition = lastEdition;
    }

    public String getOfferNumber() {
        return offerNumber;
    }

    public void setOfferNumber(String offerNumber) {
        this.offerNumber = offerNumber;
    }

    public byte[] getAbstractOfferDTO() {
        return abstractOfferDTO;
    }

    public void setAbstractOfferDTO(byte[] abstractOfferDTO) {
        this.abstractOfferDTO = abstractOfferDTO;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
