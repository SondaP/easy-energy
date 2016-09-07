package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.InvoiceZoneConsumption;

import java.util.Date;
import java.util.List;

public class Invoice {

    private Integer orderNumber;
    private String documentNumber;
    private Date periodStart;
    private Date getPeriodStop;
    private List<InvoiceZoneConsumption> invoiceZoneConsumptionList;

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
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

    public List<InvoiceZoneConsumption> getInvoiceZoneConsumptionList() {
        return invoiceZoneConsumptionList;
    }

    public void setInvoiceZoneConsumptionList(List<InvoiceZoneConsumption> invoiceZoneConsumptionList) {
        this.invoiceZoneConsumptionList = invoiceZoneConsumptionList;
    }
}
