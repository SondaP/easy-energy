package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;

public class ActualZoneFee {

    private String actualZoneCode;
    private BigDecimal actualUnitPrice;

    public String getActualZoneCode() {
        return actualZoneCode;
    }

    public void setActualZoneCode(String actualZoneCode) {
        this.actualZoneCode = actualZoneCode;
    }

    public BigDecimal getActualUnitPrice() {
        return actualUnitPrice;
    }

    public void setActualUnitPrice(BigDecimal actualUnitPrice) {
        this.actualUnitPrice = actualUnitPrice;
    }
}
