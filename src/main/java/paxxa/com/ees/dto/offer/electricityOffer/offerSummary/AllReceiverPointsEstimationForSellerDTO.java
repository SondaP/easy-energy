package paxxa.com.ees.dto.offer.electricityOffer.offerSummary;

import java.util.List;

public class AllReceiverPointsEstimationForSellerDTO {

    private String sellerCode;
    private AllReceiverPointsDataEstimationForSellerDTO allReceiverPointsDataEstimationForSellerDTO;
    private List<AllReceiverPointsProvisionForSellerDTO> allReceiverPointsProvisionForSellerDTOList;

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public AllReceiverPointsDataEstimationForSellerDTO getAllReceiverPointsDataEstimationForSellerDTO() {
        return allReceiverPointsDataEstimationForSellerDTO;
    }

    public void setAllReceiverPointsDataEstimationForSellerDTO(AllReceiverPointsDataEstimationForSellerDTO allReceiverPointsDataEstimationForSellerDTO) {
        this.allReceiverPointsDataEstimationForSellerDTO = allReceiverPointsDataEstimationForSellerDTO;
    }

    public List<AllReceiverPointsProvisionForSellerDTO> getAllReceiverPointsProvisionForSellerDTOList() {
        return allReceiverPointsProvisionForSellerDTOList;
    }

    public void setAllReceiverPointsProvisionForSellerDTOList(List<AllReceiverPointsProvisionForSellerDTO> allReceiverPointsProvisionForSellerDTOList) {
        this.allReceiverPointsProvisionForSellerDTOList = allReceiverPointsProvisionForSellerDTOList;
    }
}
