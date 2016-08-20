package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;
import java.util.Date;

public class ReceiverPointDataEstimationDTO {

    private Date tariffIssueDate;
    private BigDecimal estimatedContractValue;
    private BigDecimal estimatedContractValueInYearScale;
    private BigDecimal estimatedSavingsInYearScale;
    private BigDecimal estimatedSavingsInContractScale;
    private BigDecimal estimatedSavingsInPercentage;

    public Date getTariffIssueDate() {
        return tariffIssueDate;
    }

    public void setTariffIssueDate(Date tariffIssueDate) {
        this.tariffIssueDate = tariffIssueDate;
    }

    public BigDecimal getEstimatedContractValue() {
        return estimatedContractValue;
    }

    public void setEstimatedContractValue(BigDecimal estimatedContractValue) {
        this.estimatedContractValue = estimatedContractValue;
    }

    public BigDecimal getEstimatedContractValueInYearScale() {
        return estimatedContractValueInYearScale;
    }

    public void setEstimatedContractValueInYearScale(BigDecimal estimatedContractValueInYearScale) {
        this.estimatedContractValueInYearScale = estimatedContractValueInYearScale;
    }

    public BigDecimal getEstimatedSavingsInYearScale() {
        return estimatedSavingsInYearScale;
    }

    public void setEstimatedSavingsInYearScale(BigDecimal estimatedSavingsInYearScale) {
        this.estimatedSavingsInYearScale = estimatedSavingsInYearScale;
    }

    public BigDecimal getEstimatedSavingsInContractScale() {
        return estimatedSavingsInContractScale;
    }

    public void setEstimatedSavingsInContractScale(BigDecimal estimatedSavingsInContractScale) {
        this.estimatedSavingsInContractScale = estimatedSavingsInContractScale;
    }

    public BigDecimal getEstimatedSavingsInPercentage() {
        return estimatedSavingsInPercentage;
    }

    public void setEstimatedSavingsInPercentage(BigDecimal estimatedSavingsInPercentage) {
        this.estimatedSavingsInPercentage = estimatedSavingsInPercentage;
    }
}
