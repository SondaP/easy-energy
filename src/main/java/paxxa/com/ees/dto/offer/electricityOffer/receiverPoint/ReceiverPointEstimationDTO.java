package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.util.List;

public class ReceiverPointEstimationDTO {

    private String sellerCode;
    private ReceiverPointDataEstimationDTO receiverPointDataEstimationDTO;
    private List<ReceiverPointProvisionDTO> receiverPointProvisionDTOList;

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public ReceiverPointDataEstimationDTO getReceiverPointDataEstimationDTO() {
        return receiverPointDataEstimationDTO;
    }

    public void setReceiverPointDataEstimationDTO(ReceiverPointDataEstimationDTO receiverPointDataEstimationDTO) {
        this.receiverPointDataEstimationDTO = receiverPointDataEstimationDTO;
    }

    public List<ReceiverPointProvisionDTO> getReceiverPointProvisionDTOList() {
        return receiverPointProvisionDTOList;
    }

    public void setReceiverPointProvisionDTOList(List<ReceiverPointProvisionDTO> receiverPointProvisionDTOList) {
        this.receiverPointProvisionDTOList = receiverPointProvisionDTOList;
    }
}
