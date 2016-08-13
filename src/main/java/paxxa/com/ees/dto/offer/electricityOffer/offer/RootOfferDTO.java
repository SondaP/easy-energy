package paxxa.com.ees.dto.offer.electricityOffer.offer;

import paxxa.com.ees.dto.company.CompanyDTO;
import paxxa.com.ees.dto.offer.AbstractOfferDTO;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.OfferSummaryDTO;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ReceiverPointDTO;

import java.util.Date;
import java.util.List;

public class RootOfferDTO implements AbstractOfferDTO{

    private String offerNumber;
    private Date creationDate;
    private Date lastEditionDate;
    private Integer proposoalContractMonthLenght;
    private CompanyDTO companyDTO;
    private List<ReceiverPointDTO> receiverPointDTOList;
    private String offerNote;
    private OfferSummaryDTO offerSummaryDTO;



}
