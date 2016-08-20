package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;
import java.util.List;

public class ActualTariff {

    private String actualTariffCode;
    private BigDecimal actualUnitPrice;
    private BigDecimal actualShareOfTotalReceiverPointConsumption;
    private BigDecimal totalUnitConsumptionFromPeriods;
    private List<TariffPeriodConsumptionDTO> tariffPeriodConsumptionDTOList;

    public String getActualTariffCode() {
        return actualTariffCode;
    }

    public void setActualTariffCode(String actualTariffCode) {
        this.actualTariffCode = actualTariffCode;
    }

    public BigDecimal getActualUnitPrice() {
        return actualUnitPrice;
    }

    public void setActualUnitPrice(BigDecimal actualUnitPrice) {
        this.actualUnitPrice = actualUnitPrice;
    }

    public BigDecimal getActualShareOfTotalReceiverPointConsumption() {
        return actualShareOfTotalReceiverPointConsumption;
    }

    public void setActualShareOfTotalReceiverPointConsumption(BigDecimal actualShareOfTotalReceiverPointConsumption) {
        this.actualShareOfTotalReceiverPointConsumption = actualShareOfTotalReceiverPointConsumption;
    }

    public BigDecimal getTotalUnitConsumptionFromPeriods() {
        return totalUnitConsumptionFromPeriods;
    }

    public void setTotalUnitConsumptionFromPeriods(BigDecimal totalUnitConsumptionFromPeriods) {
        this.totalUnitConsumptionFromPeriods = totalUnitConsumptionFromPeriods;
    }

    public List<TariffPeriodConsumptionDTO> getTariffPeriodConsumptionDTOList() {
        return tariffPeriodConsumptionDTOList;
    }

    public void setTariffPeriodConsumptionDTOList(List<TariffPeriodConsumptionDTO> tariffPeriodConsumptionDTOList) {
        this.tariffPeriodConsumptionDTOList = tariffPeriodConsumptionDTOList;
    }
}
