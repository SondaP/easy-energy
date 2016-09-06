package paxxa.com.ees.dto.offer.electricityOffer.offerSummary;

import java.util.List;

public class AllReceiverPointsEstimationForSeller {

    private String sellerCode;
    private AllReceiverPointsDataEstimationForSeller allReceiverPointsDataEstimationForSeller;
    private List<AllReceiverPointsProvisionForSeller> allReceiverPointsProvisionForSellerList;

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public AllReceiverPointsDataEstimationForSeller getAllReceiverPointsDataEstimationForSeller() {
        return allReceiverPointsDataEstimationForSeller;
    }

    public void setAllReceiverPointsDataEstimationForSeller(AllReceiverPointsDataEstimationForSeller allReceiverPointsDataEstimationForSeller) {
        this.allReceiverPointsDataEstimationForSeller = allReceiverPointsDataEstimationForSeller;
    }

    public List<AllReceiverPointsProvisionForSeller> getAllReceiverPointsProvisionForSellerList() {
        return allReceiverPointsProvisionForSellerList;
    }

    public void setAllReceiverPointsProvisionForSellerList(List<AllReceiverPointsProvisionForSeller> allReceiverPointsProvisionForSellerList) {
        this.allReceiverPointsProvisionForSellerList = allReceiverPointsProvisionForSellerList;
    }
}
