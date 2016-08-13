package paxxa.com.ees.dto.offer.electricityOffer.offer;

import paxxa.com.ees.dto.company.CompanyDTO;
import paxxa.com.ees.dto.offer.AbstractOfferDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.OfferSummaryDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ReceiverPointDTO;

import java.util.Date;
import java.util.List;

public class ElectricityRootOfferDTO implements AbstractOfferDTO{

    private String offerNumber;
    private Date creationDate;
    private Date lastEditionDate;
    private Integer proposoalContractMonthLenght;
    private CompanyDTO companyDTO;
    private List<ReceiverPointDTO> receiverPointDTOList;
    private String offerNote;
    private OfferSummaryDTO offerSummaryDTO;

    public String getOfferNumber() {
        return offerNumber;
    }

    public void setOfferNumber(String offerNumber) {
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

    public Integer getProposoalContractMonthLenght() {
        return proposoalContractMonthLenght;
    }

    public void setProposoalContractMonthLenght(Integer proposoalContractMonthLenght) {
        this.proposoalContractMonthLenght = proposoalContractMonthLenght;
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
