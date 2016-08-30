package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;
import java.util.Date;

public class ReceiverPointDataEstimationDTO {

    private Date tariffIssueDate;
    private BigDecimal estimatedUnitConsumptionInYearScale;
    private BigDecimal estimatedContractProfitValue;
    private BigDecimal estimatedContractProfitValueInYearScale;
    private BigDecimal estimatedSavingsInYearScale;
    private BigDecimal estimatedSavingsInContractScale;
    private BigDecimal estimatedSavingsInPercentage;

    public BigDecimal getEstimatedUnitConsumptionInYearScale() {
        return estimatedUnitConsumptionInYearScale;
    }

    public void setEstimatedUnitConsumptionInYearScale(BigDecimal estimatedUnitConsumptionInYearScale) {
        this.estimatedUnitConsumptionInYearScale = estimatedUnitConsumptionInYearScale;
    }

    public Date getTariffIssueDate() {
        return tariffIssueDate;
    }

    public void setTariffIssueDate(Date tariffIssueDate) {
        this.tariffIssueDate = tariffIssueDate;
    }

    public BigDecimal getEstimatedContractProfitValue() {
        return estimatedContractProfitValue;
    }

    public void setEstimatedContractProfitValue(BigDecimal estimatedContractProfitValue) {
        this.estimatedContractProfitValue = estimatedContractProfitValue;
    }

    public BigDecimal getEstimatedContractProfitValueInYearScale() {
        return estimatedContractProfitValueInYearScale;
    }

    public void setEstimatedContractProfitValueInYearScale(BigDecimal estimatedContractProfitValueInYearScale) {
        this.estimatedContractProfitValueInYearScale = estimatedContractProfitValueInYearScale;
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
