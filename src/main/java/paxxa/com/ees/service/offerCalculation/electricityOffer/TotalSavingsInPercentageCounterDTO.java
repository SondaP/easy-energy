package paxxa.com.ees.service.offerCalculation.electricityOffer;

import java.math.BigDecimal;

public class TotalSavingsInPercentageCounterDTO {

    public BigDecimal totalBase;
    public BigDecimal totalSummaryOfSavingsInPercentage;

    public BigDecimal getTotalBase() {
        return totalBase;
    }

    public void setTotalBase(BigDecimal totalBase) {
        this.totalBase = totalBase;
    }

    public BigDecimal getTotalSummaryOfSavingsInPercentage() {
        return totalSummaryOfSavingsInPercentage;
    }

    public void setTotalSummaryOfSavingsInPercentage(BigDecimal totalSummaryOfSavingsInPercentage) {
        this.totalSummaryOfSavingsInPercentage = totalSummaryOfSavingsInPercentage;
    }
}
