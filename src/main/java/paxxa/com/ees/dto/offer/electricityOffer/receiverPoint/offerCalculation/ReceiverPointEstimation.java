package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation;

import java.util.List;

public class ReceiverPointEstimation {

    private String sellerCode;
    private ReceiverPointDataEstimation receiverPointDataEstimation;
    private List<ReceiverPointProvision> receiverPointProvisionList;

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public ReceiverPointDataEstimation getReceiverPointDataEstimation() {
        return receiverPointDataEstimation;
    }

    public void setReceiverPointDataEstimation(ReceiverPointDataEstimation receiverPointDataEstimation) {
        this.receiverPointDataEstimation = receiverPointDataEstimation;
    }

    public List<ReceiverPointProvision> getReceiverPointProvisionList() {
        return receiverPointProvisionList;
    }

    public void setReceiverPointProvisionList(List<ReceiverPointProvision> receiverPointProvisionList) {
        this.receiverPointProvisionList = receiverPointProvisionList;
    }
}
