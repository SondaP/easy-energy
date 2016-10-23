package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.domainConstans.DomainConstans;
import paxxa.com.ees.dto.offer.electricityOffer.offerSummary.AllReceiverPointsProvisionForSeller;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.ReceiverPointProvision;
import paxxa.com.ees.entity.provision.ProvisionVariant;
import paxxa.com.ees.service.provisionService.ProvisionService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ElectricityOfferProvisionService {

    @Autowired
    private ProvisionService provisionService;

    public List<ReceiverPointProvision> calculateReceiverPointProvisionList(final BigDecimal estimatedContractProfitValue,
                                                                            final BigDecimal estimatedContractProfitValueInYearScale,
                                                                            final String sellerName,
                                                                            final String userName) {
        List<ProvisionVariant> provisionVariants = provisionService
                .getProvisionVariants(userName, DomainConstans.PRODUCT_CODE.ELECTRICITY, sellerName);
        if (!validateProvisionVariantList(provisionVariants)) return null;

        List<ReceiverPointProvision> receiverPointProvisionList = new ArrayList<>();
        for (ProvisionVariant variant : provisionVariants) {
            ReceiverPointProvision receiverPointProvision = new ReceiverPointProvision();
            receiverPointProvision.setLevelCode(variant.getProvisionLevelDescription());

            BigDecimal provisionPercentageValue = variant.getProvisionPercentageValue();
            BigDecimal traderContractProvision = estimatedContractProfitValue
                    .multiply(provisionPercentageValue);
            receiverPointProvision.setTraderProvisionInContractScale(traderContractProvision);
            receiverPointProvision.setTraderProvisionInYearScale(
                    estimatedContractProfitValueInYearScale
                            .multiply(provisionPercentageValue));
            receiverPointProvision.setPartnerProvisionInContractScale(
                    estimatedContractProfitValue
                            .subtract(traderContractProvision));

            receiverPointProvisionList.add(receiverPointProvision);
        }
        return receiverPointProvisionList;
    }

    private boolean validateProvisionVariantList(List<ProvisionVariant> provisionVariants) {
        if (provisionVariants == null) return false;
        if (provisionVariants.size() == 0) return false;
        return true;
    }

    public List<AllReceiverPointsProvisionForSeller> calculateAllReceiverPointProvisionList(final BigDecimal totalEstimatedContractProfitValue,
                                                                                            final BigDecimal totalEstimatedContractProfitValueInYearScale,
                                                                                            final String sellerName,
                                                                                            final String userName) {
        List<ProvisionVariant> provisionVariants = provisionService
                .getProvisionVariants(userName, DomainConstans.PRODUCT_CODE.ELECTRICITY, sellerName);
        if (!validateProvisionVariantList(provisionVariants)) return null;

        List<AllReceiverPointsProvisionForSeller> receiverPointProvisionList = new ArrayList<>();
        for (ProvisionVariant variant : provisionVariants) {
            AllReceiverPointsProvisionForSeller receiverPointProvision = new AllReceiverPointsProvisionForSeller();
            receiverPointProvision.setLevelCode(variant.getProvisionLevelDescription());

            BigDecimal provisionPercentageValue = variant.getProvisionPercentageValue();
            BigDecimal traderContractProvision = totalEstimatedContractProfitValue
                    .multiply(provisionPercentageValue);
            receiverPointProvision.setTraderProvisionInContractScaleForAllPoint(traderContractProvision);
            receiverPointProvision.setTraderProvisionInYearScaleForAllPoint(
                    totalEstimatedContractProfitValueInYearScale
                            .multiply(provisionPercentageValue));
            receiverPointProvision.setPartnerProvisionInContractScaleForAllPoint(totalEstimatedContractProfitValue
                            .subtract(traderContractProvision));

            receiverPointProvisionList.add(receiverPointProvision);
        }
        return receiverPointProvisionList;
    }
}
