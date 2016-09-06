package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.OfferCalculation;

import java.util.List;

public class ReceiverPoint {

    private String receiverPointDescription;
    private String tariffCode;
    private Integer actualNumberOfZones;
    private List<ActualZone> actualZoneList;
    private List<ActualZoneConsumption> actualZoneConsumptionList;
    private OfferCalculation receiverPointOfferCalculation;

    public String getReceiverPointDescription() {
        return receiverPointDescription;
    }

    public void setReceiverPointDescription(String receiverPointDescription) {
        this.receiverPointDescription = receiverPointDescription;
    }

    public String getTariffCode() {
        return tariffCode;
    }

    public void setTariffCode(String tariffCode) {
        this.tariffCode = tariffCode;
    }

    public Integer getActualNumberOfZones() {
        return actualNumberOfZones;
    }

    public void setActualNumberOfZones(Integer actualNumberOfZones) {
        this.actualNumberOfZones = actualNumberOfZones;
    }

    public List<ActualZone> getActualZoneList() {
        return actualZoneList;
    }

    public void setActualZoneList(List<ActualZone> actualZoneList) {
        this.actualZoneList = actualZoneList;
    }

    public List<ActualZoneConsumption> getActualZoneConsumptionList() {
        return actualZoneConsumptionList;
    }

    public void setActualZoneConsumptionList(List<ActualZoneConsumption> actualZoneConsumptionList) {
        this.actualZoneConsumptionList = actualZoneConsumptionList;
    }

    public OfferCalculation getReceiverPointOfferCalculation() {
        return receiverPointOfferCalculation;
    }

    public void setReceiverPointOfferCalculation(OfferCalculation receiverPointOfferCalculation) {
        this.receiverPointOfferCalculation = receiverPointOfferCalculation;
    }
}
