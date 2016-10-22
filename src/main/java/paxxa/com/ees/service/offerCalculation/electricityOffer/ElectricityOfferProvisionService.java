package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.ReceiverPointProvision;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElectricityOfferProvisionService {

    public List<ReceiverPointProvision> calculateReceiverPointProvisionList(final BigDecimal estimatedContractProfitValue,
            final BigDecimal estimatedContractProfitValueInYearScale, final String userName) {

        List<ReceiverPointProvision> receiverPointProvisionList = new ArrayList<>();

        return receiverPointProvisionList;
    }
}
