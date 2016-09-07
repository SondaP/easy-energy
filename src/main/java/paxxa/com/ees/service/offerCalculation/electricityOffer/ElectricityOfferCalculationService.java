package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRoot;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ReceiverPoint;
import paxxa.com.ees.service.utils.UtilsService;

import java.util.List;

@Service
public class ElectricityOfferCalculationService {

    @Autowired
    private UtilsService utilsService;
    @Autowired
    private ElectricityOfferValidationService electricityOfferValidationService;

   public ElectricityOfferRoot calculateElectricityOffer(ElectricityOfferRoot electricityOfferRoot, String userName) {
       List<ReceiverPoint> receiverPointList = electricityOfferRoot.getReceiverPointList();
       electricityOfferValidationService.validateReceiverPointList(receiverPointList);

       if(electricityOfferRoot.isOfferCalculationPerReceiverPointSet()){



       } else {

       }

        return electricityOfferRoot;
    }

    public void calculateReceiverPoint(List<ReceiverPoint> receiverPointList){
        for (ReceiverPoint receiverPoint : receiverPointList) {

        }

    }



}
