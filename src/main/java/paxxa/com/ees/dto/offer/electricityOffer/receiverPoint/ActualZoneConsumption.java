package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;
import java.util.Date;

public class ActualZoneConsumption {

    private Integer orderNumber;
    private String actualZoneCode;
    private String documentNumber;
    private Date periodStart;
    private Date getPeriodStop;
    private BigDecimal unitConsumption;

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getActualZoneCode() {
        return actualZoneCode;
    }

    public void setActualZoneCode(String actualZoneCode) {
        this.actualZoneCode = actualZoneCode;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Date getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    public Date getGetPeriodStop() {
        return getPeriodStop;
    }

    public void setGetPeriodStop(Date getPeriodStop) {
        this.getPeriodStop = getPeriodStop;
    }

    public BigDecimal getUnitConsumption() {
        return unitConsumption;
    }

    public void setUnitConsumption(BigDecimal unitConsumption) {
        this.unitConsumption = unitConsumption;
    }
}
