package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation;

import java.math.BigDecimal;

public class ZoneTotalConsumptionSummary {

    private String actualZoneCode;
    private BigDecimal totalUnitConsumption;

    public String getActualZoneCode() {
        return actualZoneCode;
    }

    public void setActualZoneCode(String actualZoneCode) {
        this.actualZoneCode = actualZoneCode;
    }

    public BigDecimal getTotalUnitConsumption() {
        return totalUnitConsumption;
    }

    public void setTotalUnitConsumption(BigDecimal totalUnitConsumption) {
        this.totalUnitConsumption = totalUnitConsumption;
    }
}
 