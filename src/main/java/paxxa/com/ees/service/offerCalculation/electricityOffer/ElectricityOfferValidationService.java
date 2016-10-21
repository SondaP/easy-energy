package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ActualZone;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.Invoice;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.ReceiverPoint;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.InvoiceZoneConsumption;
import paxxa.com.ees.service.exception.OfferCalculationException.IncorrectDataException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectricityOfferValidationService {


    public void validateReceiverPointList(List<ReceiverPoint> receiverPointList) {
        for (ReceiverPoint receiverPoint : receiverPointList) {
            // ReceiverPointDescription
            if (receiverPoint.getReceiverPointDescription() == null) {
                String message = "Value for attribute: receiverPointDescription from Object: ReceiverPoint at "
                        + receiverPoint.getReceiverPointDescription() + ", is required";
                throw new IncorrectDataException(message);
            }
            // ActualNumberOfZones
            if (receiverPoint.getActualNumberOfZones() == null) {
                String message = "Value for attribute: actualNumberOfZones from Object: ReceiverPoint at "
                        + receiverPoint.getReceiverPointDescription() + ", is required";
                throw new IncorrectDataException(message);
            }
            // TariffCode
            if (receiverPoint.getTariffCode() == null) {
                String message = "Value for attribute: tariffCode from Object: ReceiverPoint at "
                        + receiverPoint.getReceiverPointDescription() + ", is required";
                throw new IncorrectDataException(message);
            }
            // InvoiceList size
            if (receiverPoint.getInvoiceList().size() == 0) {
                String message = "Value for attribute: invoiceList from Object: ReceiverPoint at "
                        + receiverPoint.getReceiverPointDescription() + ", is required and cannot be empty";
                throw new IncorrectDataException(message);
            }
            // InvoiceList -> Invoice data
            for (Invoice invoice : receiverPoint.getInvoiceList()) {
                // DocumentNumber
                if (invoice.getDocumentNumber() == null) {
                    String message = "Value for attribute: documentNumber from Object: ReceiverPoint at "
                            + receiverPoint.getReceiverPointDescription() + ", is required";
                    throw new IncorrectDataException(message);
                }
                //
                if (invoice.getPeriodStart() == null) {
                    String message = "Value for attribute: periodStart from Object: ReceiverPoint at "
                            + receiverPoint.getReceiverPointDescription() + ", is required";
                    throw new IncorrectDataException(message);
                }
                if (invoice.getGetPeriodStop() == null) {
                    String message = "Value for attribute: periodStop from Object: ReceiverPoint at "
                            + receiverPoint.getReceiverPointDescription() + ", is required";
                    throw new IncorrectDataException(message);
                }
                for (InvoiceZoneConsumption invoiceZoneConsumption : invoice.getInvoiceZoneConsumptionList()) {
                    if (invoiceZoneConsumption.getUnitConsumption() == null) {
                        String message = "Value for attribute: unitConsumption from Object: ReceiverPoint at "
                                + receiverPoint.getReceiverPointDescription() + ", is required";
                        throw new IncorrectDataException(message);
                    }
                    if (invoiceZoneConsumption.getActualZoneCode() == null) {
                        String message = "Value for attribute: actualZoneCode from Object: ReceiverPoint at "
                                + receiverPoint.getReceiverPointDescription() + ", is required";
                        throw new IncorrectDataException(message);
                    }
                    List<ActualZone> actualZoneList = receiverPoint.getActualZoneList();
                    List<String> actualZonesCodes = getActualZonesCodes(actualZoneList);
                    if (!actualZonesCodes.contains(invoiceZoneConsumption.getActualZoneCode())) {
                        String message = "Value for attribute: actualZoneCode from InvoiceZoneConsumption, at ReceiverPoint: "
                                + receiverPoint.getReceiverPointDescription() + ", does not match witch provided ActualZoneCodes for receiver point";
                        throw new IncorrectDataException(message);
                    }
                }
            }
        }
    }

    private List<String> getActualZonesCodes(List<ActualZone> actualZoneList){
        return actualZoneList.stream().map(x -> x.getActualZoneCodeCode()).collect(Collectors.toList());
    }





  /*  public void validateTariffPeriodsConsumptions(List<ActualTariff> actualTariffList, String receiverPointDescription) {
        for (ActualTariff actualTariff : actualTariffList) {

            if (actualTariff.getActualTariffCode() == null || actualTariff.getActualTariffCode().isEmpty()) {
                String message = "Value for attribute: actualTariffCode from Object: ActualTariff at "
                        + receiverPointDescription + ", is required";
                throw new IncorrectDataException(message);
            }
            if (actualTariff.getActualUnitPrice() == null) {
                String message = "Value for attribute: actualUnitPrice from Object: ActualTariff at "
                        + receiverPointDescription + ", is required";
                throw new IncorrectDataException(message);
            }


            List<TariffPeriodConsumptionDTO> tariffPeriodConsumptionDTOList =
                    actualTariff.getTariffPeriodConsumptionDTOList();

            for (TariffPeriodConsumptionDTO tariffPeriodConsumptionDTO : tariffPeriodConsumptionDTOList) {

                if (tariffPeriodConsumptionDTO.getPeriodStart() == null) {
                    String message = "Value for attribute: periodStart from Object: TariffPeriodConsumptionDTO at "
                            + receiverPointDescription + ", is required";
                    throw new IncorrectDataException(message);
                }
                if (tariffPeriodConsumptionDTO.getPeriodEnd() == null) {
                    String message = "Value for attribute: periodStop from Object: TariffPeriodConsumptionDTO at "
                            + receiverPointDescription + ", is required";
                    throw new IncorrectDataException(message);
                }
                if (tariffPeriodConsumptionDTO.getUnitConsumption() == null) {
                    String message = "Value for attribute: unitConsumption from Object: TariffPeriodConsumptionDTO at "
                            + receiverPointDescription + ", is required";
                    throw new IncorrectDataException(message);
                }
                if (tariffPeriodConsumptionDTO.getDocumentNumber() == null || tariffPeriodConsumptionDTO.getDocumentNumber().isEmpty()) {
                    String message = "Value for attribute: documentNumber from Object: TariffPeriodConsumptionDTO at "
                            + receiverPointDescription + ", is required and cannot be empty";
                    throw new IncorrectDataException(message);
                }

            }
        }
    }

    public void validateNumberOfActualTariffs(Integer expectedNumberOfTariffs, List<ActualTariff> actualTariffList,
                                               String receiverPointDescription) {
        if (actualTariffList.size() != expectedNumberOfTariffs) throw new IncorrectDataException(
                "Incorrect number of tariffs at actualTariffList, should be " + expectedNumberOfTariffs
                        + " positions. Receiver point: " + receiverPointDescription);
    }

    public void validateReceiverPoint(ReceiverPoint receiverPointDTO) {
        if (receiverPointDTO.getReceiverPointDescription() == null || receiverPointDTO.getReceiverPointDescription().isEmpty()) {
            String message = "Value for attribute: receiverPointDescription from Object: ReceiverPoint at "
                    + receiverPointDTO.getReceiverPointDescription() + ", is required and cannot be empty";
            throw new IncorrectDataException(message);
        }
        if (receiverPointDTO.getActualTradeFee() == null) {
            String message = "Value for attribute: actualTradeFee from Object: ReceiverPoint at "
                    + receiverPointDTO.getReceiverPointDescription() + ", is required";
            throw new IncorrectDataException(message);
        }
        if (receiverPointDTO.getActualNumberOfTariffs() == null) {
            String message = "Value for attribute: actualNumberOfTariffs from Object: ReceiverPoint at "
                    + receiverPointDTO.getReceiverPointDescription() + ", is required";
            throw new IncorrectDataException(message);
        }
        if (receiverPointDTO.getProposalNumberOfTariffs() == null) {
            String message = "Value for attribute: proposalNumberOfTariffs from Object: ReceiverPoint at "
                    + receiverPointDTO.getReceiverPointDescription() + ", is required";
            throw new IncorrectDataException(message);
        }
    }

    public void validateElectricityOfferRoot(ElectricityOfferRoot electricityOfferRootDTO) {
        if (electricityOfferRootDTO.getProposalContractMonthLength() == null) {
            String message = "Value for attribute: proposalContractMonthLength from Object: ElectricityOfferRoot" +
                    ", is required";
            throw new IncorrectDataException(message);
        }
    }

    public void validateProposalSellers(List<ProposalSeller> proposalSellerList, String receiverPointDescription, Integer proposalNumberOfTariffs) {
        for (ProposalSeller proposalSeller : proposalSellerList) {
            if (proposalSeller.getSellerCode() == null || proposalSeller.getSellerCode().isEmpty()) {
                String message = "Value for attribute: sellerCode from Object: ProposalSeller at "
                        + receiverPointDescription + ", is required";
                throw new IncorrectDataException(message);
            }
            if (proposalSeller.getProposalTradeFee() == null) {
                String message = "Value for attribute: proposalTradeFee from Object: ProposalSeller at "
                        + receiverPointDescription + ", is required";
                throw new IncorrectDataException(message);
            }
            if (proposalSeller.getSellerTariffPublicationDate() == null) {
                String message = "Value for attribute: sellerTariffPublicationDate from Object: ProposalSeller at "
                        + receiverPointDescription + ", is required";
                throw new IncorrectDataException(message);
            }
            if (proposalSeller.getProposalZoneDetailsList() == null || proposalSeller.getProposalZoneDetailsList().size() != proposalNumberOfTariffs) {
                String message = "Value for attribute: proposalTariffList from Object: ProposalSeller at "
                        + receiverPointDescription + ", is required and list size must be equals to proposalNumberOfTariffs";
                throw new IncorrectDataException(message);
            }
        }
    }

    public void validateProposalTariffs(List<ProposalSeller> proposalSellerList, String receiverPointDescription,
                                         Integer proposalNumberOfTariffs, List<String> actualTariffCodesList) {
        for (ProposalSeller proposalSeller : proposalSellerList) {
            for (ProposalZoneDetails proposalTariff : proposalSeller.getProposalZoneDetailsList()) {
                if (proposalTariff.getActualZoneCode() == null) {
                    String message = "Value for attribute: actualTariffCode from Object: ProposalSeller at "
                            + receiverPointDescription + ", is required";
                    throw new IncorrectDataException(message);
                }
                if (proposalTariff.getSellerMinimalUnitPrice() == null) {
                    String message = "Value for attribute: sellerMinimalUnitPrice from Object: ProposalSeller at "
                            + receiverPointDescription + ", is required";
                    throw new IncorrectDataException(message);
                }
                if (proposalTariff.getProposalZoneCode() == null) {
                    String message = "Value for attribute: proposalTariffCode from Object: ProposalSeller at "
                            + receiverPointDescription + ", is required";
                    throw new IncorrectDataException(message);
                }
                if (proposalTariff.getProposalUnitPrice() == null) {
                    String message = "Value for attribute: proposalUnitPrice from Object: ProposalSeller at "
                            + receiverPointDescription + ", is required";
                    throw new IncorrectDataException(message);
                }
                if (!actualTariffCodesList.contains(proposalTariff.getActualZoneCode())) {
                    String message = "Value for attribute: actualTariffCode from Object: ProposalSeller at "
                            + receiverPointDescription + ", is incorrect. Value should be the same as one of the " +
                            "actualTariffCode from ActualTariff ";
                    throw new IncorrectDataException(message);
                }
            }
        }
    }*/
}
