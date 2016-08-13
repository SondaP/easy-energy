package paxxa.com.ees.dto.offer.electricityOffer.offerSummary;

import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ReceiverPointProvisionDTO;

import java.util.List;

public class AllReceiverPointsEstimationDTO {

    private String sellerCode;
    private List<AllReceiversDataEstimationDTO> allReceiversDataEstimationDTOList;
    private List<AllReceiverPointsProvisionDTO> allReceiverPointsProvisionDTOList;

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public List<AllReceiversDataEstimationDTO> getAllReceiversDataEstimationDTOList() {
        return allReceiversDataEstimationDTOList;
    }

    public void setAllReceiversDataEstimationDTOList(List<AllReceiversDataEstimationDTO> allReceiversDataEstimationDTOList) {
        this.allReceiversDataEstimationDTOList = allReceiversDataEstimationDTOList;
    }

    public List<AllReceiverPointsProvisionDTO> getAllReceiverPointsProvisionDTOList() {
        return allReceiverPointsProvisionDTOList;
    }

    public void setAllReceiverPointsProvisionDTOList(List<AllReceiverPointsProvisionDTO> allReceiverPointsProvisionDTOList) {
        this.allReceiverPointsProvisionDTOList = allReceiverPointsProvisionDTOList;
    }
}
