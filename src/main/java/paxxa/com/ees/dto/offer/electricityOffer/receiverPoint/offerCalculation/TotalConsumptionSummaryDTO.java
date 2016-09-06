package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation;

import java.math.BigDecimal;

public class TotalConsumptionSummaryDTO {

    private Integer totalNumberOfDaysForAllPeriods;
    private BigDecimal totalElectricityUnitsConsumptionInAllPeriods;
    private BigDecimal predictedElectricityUnitConsumptionPerYear;

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
}
