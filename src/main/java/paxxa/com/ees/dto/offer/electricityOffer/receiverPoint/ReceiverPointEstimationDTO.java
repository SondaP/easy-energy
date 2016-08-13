package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.util.List;

public class ReceiverPointEstimationDTO {

    private String sellerCode;
    private List<ReceiverDataEstimationDTO> receiverDataEstimationDTOList;
    private List<ReceiverPointProvisionDTO> receiverPointProvisionDTOList;

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public List<ReceiverDataEstimationDTO> getReceiverDataEstimationDTOList() {
        return receiverDataEstimationDTOList;
    }

    public void setReceiverDataEstimationDTOList(List<ReceiverDataEstimationDTO> receiverDataEstimationDTOList) {
        this.receiverDataEstimationDTOList = receiverDataEstimationDTOList;
    }

    public List<ReceiverPointProvisionDTO> getReceiverPointProvisionDTOList() {
        return receiverPointProvisionDTOList;
    }

    public void setReceiverPointProvisionDTOList(List<ReceiverPointProvisionDTO> receiverPointProvisionDTOList) {
        this.receiverPointProvisionDTOList = receiverPointProvisionDTOList;
    }
}
