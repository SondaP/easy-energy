package paxxa.com.ees.dto.offer.electricityOffer.offer;

import paxxa.com.ees.dto.company.CompanyDTO;
import paxxa.com.ees.dto.offer.AbstractOfferDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.OfferSummaryDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ReceiverPointDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class ElectricityOfferRootDTO extends AbstractOfferDTO {

    private Integer offerStorageId;
    private Integer offerNumber;
    private Date creationDate;
    private Date lastEditionDate;
    private Integer proposalContractMonthLength;
    private CompanyDTO companyDTO;
    private List<ReceiverPointDTO> receiverPointDTOList;
    private String offerNote;
    private OfferSummaryDTO offerSummaryDTO;

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

    public Integer getProposalContractMonthLength() {
        return proposalContractMonthLength;
    }

    public void setProposalContractMonthLength(Integer proposalContractMonthLength) {
        this.proposalContractMonthLength = proposalContractMonthLength;
    }

    public CompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
    }

    public List<ReceiverPointDTO> getReceiverPointDTOList() {
        return receiverPointDTOList;
    }

    public void setReceiverPointDTOList(List<ReceiverPointDTO> receiverPointDTOList) {
        this.receiverPointDTOList = receiverPointDTOList;
    }

    public String getOfferNote() {
        return offerNote;
    }

    public void setOfferNote(String offerNote) {
        this.offerNote = offerNote;
    }

    public OfferSummaryDTO getOfferSummaryDTO() {
        return offerSummaryDTO;
    }

    public void setOfferSummaryDTO(OfferSummaryDTO offerSummaryDTO) {
        this.offerSummaryDTO = offerSummaryDTO;
    }
}
