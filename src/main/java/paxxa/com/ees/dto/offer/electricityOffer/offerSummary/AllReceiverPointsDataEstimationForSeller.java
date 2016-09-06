package paxxa.com.ees.dto.offer.electricityOffer.offerSummary;

import java.math.BigDecimal;
import java.util.Date;

public class AllReceiverPointsDataEstimationForSeller {

    private Date tariffIssueDate;
    private BigDecimal estimatedContractValueForAllPoint;
    private BigDecimal estimatedContractValueInYearScaleForAllPoint;
    private BigDecimal estimatedSavingsInYearScaleForAllPoint;
    private BigDecimal estimatedSavingsInContractScaleForAllPoint;
    private BigDecimal estimatedSavingsInPercentageForAllPoint;

    public Date getTariffIssueDate() {
        return tariffIssueDate;
    }

    public void setTariffIssueDate(Date tariffIssueDate) {
        this.tariffIssueDate = tariffIssueDate;
    }

    public BigDecimal getEstimatedContractValueForAllPoint() {
        return estimatedContractValueForAllPoint;
    }

    public void setEstimatedContractValueForAllPoint(BigDecimal estimatedContractValueForAllPoint) {
        this.estimatedContractValueForAllPoint = estimatedContractValueForAllPoint;
    }

    public BigDecimal getEstimatedContractValueInYearScaleForAllPoint() {
        return estimatedContractValueInYearScaleForAllPoint;
    }

    public void setEstimatedContractValueInYearScaleForAllPoint(BigDecimal estimatedContractValueInYearScaleForAllPoint) {
        this.estimatedContractValueInYearScaleForAllPoint = estimatedContractValueInYearScaleForAllPoint;
    }

    public BigDecimal getEstimatedSavingsInYearScaleForAllPoint() {
        return estimatedSavingsInYearScaleForAllPoint;
    }

    public void setEstimatedSavingsInYearScaleForAllPoint(BigDecimal estimatedSavingsInYearScaleForAllPoint) {
        this.estimatedSavingsInYearScaleForAllPoint = estimatedSavingsInYearScaleForAllPoint;
    }

    public BigDecimal getEstimatedSavingsInContractScaleForAllPoint() {
        return estimatedSavingsInContractScaleForAllPoint;
    }

    public void setEstimatedSavingsInContractScaleForAllPoint(BigDecimal estimatedSavingsInContractScaleForAllPoint) {
        this.estimatedSavingsInContractScaleForAllPoint = estimatedSavingsInContractScaleForAllPoint;
    }

    public BigDecimal getEstimatedSavingsInPercentageForAllPoint() {
        return estimatedSavingsInPercentageForAllPoint;
    }

    public void setEstimatedSavingsInPercentageForAllPoint(BigDecimal estimatedSavingsInPercentageForAllPoint) {
        this.estimatedSavingsInPercentageForAllPoint = estimatedSavingsInPercentageForAllPoint;
    }
}
