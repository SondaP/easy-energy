package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.util.List;

public class TariffConsumptionDTO {

    private String tariffCode;
    private List<TariffPeriodConsumptionDTO> tariffPeriodConsumptionDTOList;

    public String getTariffCode() {
        return tariffCode;
    }

    public void setTariffCode(String tariffCode) {
        this.tariffCode = tariffCode;
    }

    public List<TariffPeriodConsumptionDTO> getTariffPeriodConsumptionDTOList() {
        return tariffPeriodConsumptionDTOList;
    }

    public void setTariffPeriodConsumptionDTOList(List<TariffPeriodConsumptionDTO> tariffPeriodConsumptionDTOList) {
        this.tariffPeriodConsumptionDTOList = tariffPeriodConsumptionDTOList;
    }
}
