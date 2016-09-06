package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation;

import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ActualZoneFee;

import java.math.BigDecimal;
import java.util.List;

public class ActualReceiverPointFees {

    private BigDecimal actualTradeFee;
    private List<ActualZoneFee> actualZoneFeeList;

    public BigDecimal getActualTradeFee() {
        return actualTradeFee;
    }

    public void setActualTradeFee(BigDecimal actualTradeFee) {
        this.actualTradeFee = actualTradeFee;
    }

    public List<ActualZoneFee> getActualZoneFeeList() {
        return actualZoneFeeList;
    }

    public void setActualZoneFeeList(List<ActualZoneFee> actualZoneFeeList) {
        this.actualZoneFeeList = actualZoneFeeList;
    }
}
