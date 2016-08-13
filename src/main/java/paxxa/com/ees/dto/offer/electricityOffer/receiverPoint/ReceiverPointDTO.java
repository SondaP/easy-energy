package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;
import java.util.List;

public class ReceiverPointDTO {

    private String receiverPointDescription;
    private BigDecimal actualTradeFee;
    private BigDecimal proposalTradeFee;
    private int actualTariffsNumber;
    private int proposalTariffsNumber;
    private List<TariffConsumptionDTO> tariffConsumptionDTOList;
    private List<TariffDetailsDTO> tariffDetailsDTOList;
    private ReceiverPointConsumptionSummaryDTO receiverPointConsumptionSummaryDTO;
    private List<ReceiverPointEstimationDTO> electricityReceiverPointEstimationList;


}
