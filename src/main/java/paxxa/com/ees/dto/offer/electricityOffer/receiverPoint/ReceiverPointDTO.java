package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;
import java.util.List;

public class ReceiverPointDTO {

    private String receiverPointDescription;
    private BigDecimal actualTradeFee;
    private int actualTariffsNumber;
    private int proposalTariffsNumber;
    private List<ActualTariff> actualTariffList;
    private ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO;
    private List<ProposalSeller> proposalSellerList;
    private List<ReceiverPointEstimationDTO> receiverPointEstimationList;

    public String getReceiverPointDescription() {
        return receiverPointDescription;
    }

    public void setReceiverPointDescription(String receiverPointDescription) {
        this.receiverPointDescription = receiverPointDescription;
    }

    public BigDecimal getActualTradeFee() {
        return actualTradeFee;
    }

    public void setActualTradeFee(BigDecimal actualTradeFee) {
        this.actualTradeFee = actualTradeFee;
    }

    public int getActualTariffsNumber() {
        return actualTariffsNumber;
    }

    public void setActualTariffsNumber(int actualTariffsNumber) {
        this.actualTariffsNumber = actualTariffsNumber;
    }

    public int getProposalTariffsNumber() {
        return proposalTariffsNumber;
    }

    public void setProposalTariffsNumber(int proposalTariffsNumber) {
        this.proposalTariffsNumber = proposalTariffsNumber;
    }

    public List<ActualTariff> getActualTariffList() {
        return actualTariffList;
    }

    public void setActualTariffList(List<ActualTariff> actualTariffList) {
        this.actualTariffList = actualTariffList;
    }

    public List<ProposalSeller> getProposalSellerList() {
        return proposalSellerList;
    }

    public void setProposalSellerList(List<ProposalSeller> proposalSellerList) {
        this.proposalSellerList = proposalSellerList;
    }

    public ReceiverPointConsumptionSummaryDTO getReceiverPointConsumptionSummaryDTO() {
        return receiverPointConsumptionSummaryDTO;
    }

    public void setReceiverPointConsumptionSummaryDTO(ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO) {
        this.receiverPointConsumptionSummaryDTO = receiverPointConsumptionSummaryDTO;
    }

    public List<ReceiverPointEstimationDTO> getReceiverPointEstimationList() {
        return receiverPointEstimationList;
    }

    public void setReceiverPointEstimationList(List<ReceiverPointEstimationDTO> receiverPointEstimationList) {
        this.receiverPointEstimationList = receiverPointEstimationList;
    }
}
