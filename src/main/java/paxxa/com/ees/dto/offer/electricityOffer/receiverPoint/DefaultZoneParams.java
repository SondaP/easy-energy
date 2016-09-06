package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;

public class DefaultZoneParams {

    private String actualZoneCode;
    private BigDecimal defaultUnitPrice;

    public String getActualZoneCode() {
        return actualZoneCode;
    }

    public void setActualZoneCode(String actualZoneCode) {
        this.actualZoneCode = actualZoneCode;
    }

    public BigDecimal getDefaultUnitPrice() {
        return defaultUnitPrice;
    }

    public void setDefaultUnitPrice(BigDecimal defaultUnitPrice) {
        this.defaultUnitPrice = defaultUnitPrice;
    }
}
