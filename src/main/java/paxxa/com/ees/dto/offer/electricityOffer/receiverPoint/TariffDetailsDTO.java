package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;

public class TariffDetailsDTO {

    private String tariffCode;
    private BigDecimal totalUnitConsumptionFromPeriods;
    private BigDecimal actualUnitPrice;
    private BigDecimal actualShareOfTotalReceiverPointConsumption;
    private String proposalOfferCode;
    private BigDecimal predictedShareOfTotalReceiverPointConsumption;
    private BigDecimal proposalUnitPrice;
}
