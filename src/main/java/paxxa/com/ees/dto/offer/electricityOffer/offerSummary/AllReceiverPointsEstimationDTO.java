package paxxa.com.ees.dto.offer.electricityOffer.offerSummary;

import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ReceiverPointProvisionDTO;

import java.util.List;

public class AllReceiverPointsEstimationDTO {

    private String sellerCode;
    private List<AllReceiversDataEstimationDTO> allReceiversDataEstimationDTOList;
    private List<AllReceiverPointsProvisionDTO> allReceiverPointsProvisionDTOList;
}
