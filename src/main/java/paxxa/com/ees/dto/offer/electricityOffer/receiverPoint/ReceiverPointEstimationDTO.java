package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.util.List;

public class ReceiverPointEstimationDTO {

    private String sellerCode;
    private ReceiverDataEstimationDTO receiverDataEstimationDTO;
    private List<ReceiverPointProvisionDTO> receiverPointProvisionDTOList;

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public ReceiverDataEstimationDTO getReceiverDataEstimationDTO() {
        return receiverDataEstimationDTO;
    }

    public void setReceiverDataEstimationDTO(ReceiverDataEstimationDTO receiverDataEstimationDTO) {
        this.receiverDataEstimationDTO = receiverDataEstimationDTO;
    }

    public List<ReceiverPointProvisionDTO> getReceiverPointProvisionDTOList() {
        return receiverPointProvisionDTOList;
    }

    public void setReceiverPointProvisionDTOList(List<ReceiverPointProvisionDTO> receiverPointProvisionDTOList) {
        this.receiverPointProvisionDTOList = receiverPointProvisionDTOList;
    }
}
