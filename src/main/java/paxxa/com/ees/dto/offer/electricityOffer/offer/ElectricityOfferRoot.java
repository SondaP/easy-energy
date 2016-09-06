package paxxa.com.ees.dto.offer.electricityOffer.offer;

import paxxa.com.ees.dto.company.CompanyDTO;
import paxxa.com.ees.dto.offer.AbstractOfferDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.OfferSummaryDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ReceiverPoint;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.OfferCalculation;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class ElectricityOfferRoot extends AbstractOfferDTO {

    private Integer offerStorageId;
    private Integer offerNumber;
    private Date creationDate;
    private Date lastEditionDate;
    private CompanyDTO companyDTO;
    private List<ReceiverPoint> receiverPointList;
    private OfferCalculation allReceiverPointsOfferCalculation;
    private OfferSummaryDTO offerSummaryDTO;
    private String offerNote;
    private boolean isOfferCalculationPerReceiverPointSet;

    public Integer getOfferStorageId() {
        return offerStorageId;
    }

    public void setOfferStorageId(Integer offerStorageId) {
        this.offerStorageId = offerStorageId;
    }

    public Integer getOfferNumber() {
        return offerNumber;
    }

    public void setOfferNumber(Integer offerNumber) {
        this.offerNumber = offerNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastEditionDate() {
        return lastEditionDate;
    }

    public void setLastEditionDate(Date lastEditionDate) {
        this.lastEditionDate = lastEditionDate;
    }

    public CompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
    }

    public List<ReceiverPoint> getReceiverPointList() {
        return receiverPointList;
    }

    public void setReceiverPointList(List<ReceiverPoint> receiverPointList) {
        this.receiverPointList = receiverPointList;
    }

    public OfferCalculation getAllReceiverPointsOfferCalculation() {
        return allReceiverPointsOfferCalculation;
    }

    public void setAllReceiverPointsOfferCalculation(OfferCalculation allReceiverPointsOfferCalculation) {
        this.allReceiverPointsOfferCalculation = allReceiverPointsOfferCalculation;
    }

    public OfferSummaryDTO getOfferSummaryDTO() {
        return offerSummaryDTO;
    }

    public void setOfferSummaryDTO(OfferSummaryDTO offerSummaryDTO) {
        this.offerSummaryDTO = offerSummaryDTO;
    }

    public String getOfferNote() {
        return offerNote;
    }

    public void setOfferNote(String offerNote) {
        this.offerNote = offerNote;
    }

    public boolean isOfferCalculationPerReceiverPointSet() {
        return isOfferCalculationPerReceiverPointSet;
    }

    public void setOfferCalculationPerReceiverPointSet(boolean offerCalculationPerReceiverPointSet) {
        this.isOfferCalculationPerReceiverPointSet = offerCalculationPerReceiverPointSet;
    }
}
