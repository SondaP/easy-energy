package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;
import java.util.List;

public class ReceiverPointDTO {

    private String receiverPointDescription;
    private BigDecimal actualTradeFee;
    private BigDecimal proposalTradeFee;
    private int actualTariffsNumber;
    private int proposalTariffsNumber;
    private List<TariffConsumptionDTO> tariffConsumptionDTOList;
    private List<TariffDetailsDTO> tariffDetailsDTOList;
    private ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO;
    private List<ReceiverPointEstimationDTO> electricityReceiverPointEstimationList;

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

    public BigDecimal getProposalTradeFee() {
        return proposalTradeFee;
    }

    public void setProposalTradeFee(BigDecimal proposalTradeFee) {
        this.proposalTradeFee = proposalTradeFee;
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

    public List<TariffConsumptionDTO> getTariffConsumptionDTOList() {
        return tariffConsumptionDTOList;
    }

    public void setTariffConsumptionDTOList(List<TariffConsumptionDTO> tariffConsumptionDTOList) {
        this.tariffConsumptionDTOList = tariffConsumptionDTOList;
    }

    public List<TariffDetailsDTO> getTariffDetailsDTOList() {
        return tariffDetailsDTOList;
    }

    public void setTariffDetailsDTOList(List<TariffDetailsDTO> tariffDetailsDTOList) {
        this.tariffDetailsDTOList = tariffDetailsDTOList;
    }

    public ReceiverPointConsumptionSummaryDTO getReceiverPointConsumptionSummaryDTO() {
        return receiverPointConsumptionSummaryDTO;
    }

    public void setReceiverPointConsumptionSummaryDTO(ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO) {
        this.receiverPointConsumptionSummaryDTO = receiverPointConsumptionSummaryDTO;
    }

    public List<ReceiverPointEstimationDTO> getElectricityReceiverPointEstimationList() {
        return electricityReceiverPointEstimationList;
    }

    public void setElectricityReceiverPointEstimationList(List<ReceiverPointEstimationDTO> electricityReceiverPointEstimationList) {
        this.electricityReceiverPointEstimationList = electricityReceiverPointEstimationList;
    }
}
