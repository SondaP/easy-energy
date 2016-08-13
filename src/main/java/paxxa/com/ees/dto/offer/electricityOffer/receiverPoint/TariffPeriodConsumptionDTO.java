package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;
import java.util.Date;

public class TariffPeriodConsumptionDTO {

    private Date periodStart;
    private Date periodEnd;
    private BigDecimal unitConsumption;
    private String periodDocumentNumber;

    public Date getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public BigDecimal getUnitConsumption() {
        return unitConsumption;
    }

    public void setUnitConsumption(BigDecimal unitConsumption) {
        this.unitConsumption = unitConsumption;
    }

    public String getPeriodDocumentNumber() {
        return periodDocumentNumber;
    }

    public void setPeriodDocumentNumber(String periodDocumentNumber) {
        this.periodDocumentNumber = periodDocumentNumber;
    }
}
