package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation;

import java.math.BigDecimal;
import java.util.List;

public class TotalConsumptionSummary {

    private Integer totalNumberOfDaysForAllPeriods;
    private BigDecimal totalElectricityUnitsConsumptionInAllPeriods;
    private BigDecimal predictedElectricityUnitConsumptionPerYear;
    private Integer invoiceNumbers;
    private List<ZoneTotalConsumptionSummary> zoneTotalConsumptionSummaryList;
    private BigDecimal totalActualCostForAllUnitsConsumption;
    private BigDecimal totalCostForAllUnitsConsumptionBasedOnProposal;

    public Integer getTotalNumberOfDaysForAllPeriods() {
        return totalNumberOfDaysForAllPeriods;
    }

    public void setTotalNumberOfDaysForAllPeriods(Integer totalNumberOfDaysForAllPeriods) {
        this.totalNumberOfDaysForAllPeriods = totalNumberOfDaysForAllPeriods;
    }

    public BigDecimal getTotalElectricityUnitsConsumptionInAllPeriods() {
        return totalElectricityUnitsConsumptionInAllPeriods;
    }

    public void setTotalElectricityUnitsConsumptionInAllPeriods(BigDecimal totalElectricityUnitsConsumptionInAllPeriods) {
        this.totalElectricityUnitsConsumptionInAllPeriods = totalElectricityUnitsConsumptionInAllPeriods;
    }

    public BigDecimal getPredictedElectricityUnitConsumptionPerYear() {
        return predictedElectricityUnitConsumptionPerYear;
    }

    public void setPredictedElectricityUnitConsumptionPerYear(BigDecimal predictedElectricityUnitConsumptionPerYear) {
        this.predictedElectricityUnitConsumptionPerYear = predictedElectricityUnitConsumptionPerYear;
    }

    public Integer getInvoiceNumbers() {
        return invoiceNumbers;
    }

    public void setInvoiceNumbers(Integer invoiceNumbers) {
        this.invoiceNumbers = invoiceNumbers;
    }

    public List<ZoneTotalConsumptionSummary> getZoneTotalConsumptionSummaryList() {
        return zoneTotalConsumptionSummaryList;
    }

    public void setZoneTotalConsumptionSummaryList(List<ZoneTotalConsumptionSummary> zoneTotalConsumptionSummaryList) {
        this.zoneTotalConsumptionSummaryList = zoneTotalConsumptionSummaryList;
    }

    public BigDecimal getTotalActualCostForAllUnitsConsumption() {
        return totalActualCostForAllUnitsConsumption;
    }

    public void setTotalActualCostForAllUnitsConsumption(BigDecimal totalActualCostForAllUnitsConsumption) {
        this.totalActualCostForAllUnitsConsumption = totalActualCostForAllUnitsConsumption;
    }

    public BigDecimal getTotalCostForAllUnitsConsumptionBasedOnProposal() {
        return totalCostForAllUnitsConsumptionBasedOnProposal;
    }

    public void setTotalCostForAllUnitsConsumptionBasedOnProposal(BigDecimal totalCostForAllUnitsConsumptionBasedOnProposal) {
        this.totalCostForAllUnitsConsumptionBasedOnProposal = totalCostForAllUnitsConsumptionBasedOnProposal;
    }
}
