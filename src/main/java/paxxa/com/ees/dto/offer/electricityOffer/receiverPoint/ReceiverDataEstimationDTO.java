package paxxa.com.ees.dto.offer.electricityOffer.receiverPoint;

import java.math.BigDecimal;
import java.util.Date;

public class ReceiverDataEstimationDTO {

    private Date tariffIssueDate;
    private BigDecimal estimatedContractValue;
    private BigDecimal estimatedContractValueInYearScale;
    private BigDecimal estimatedSavingsInYearScale;
    private BigDecimal estimatedSavingsInContractScale;
    private BigDecimal estimatedSavingsInPercentage;
}
