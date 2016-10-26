package paxxa.com.ees.service.offerCalculation.electricityOffer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.*;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.ActualReceiverPointFees;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.InvoiceZoneConsumption;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.OfferCalculation;
import paxxa.com.ees.dto.offer.electricityOffer.receiverPoint.offerCalculation.ProposalSeller;
import paxxa.com.ees.service.exception.OfferCalculationException.IncorrectDataException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ElectricityOfferValidationService {

    final static Logger LOG = Logger.getLogger(ElectricityOfferValidationService.class);


    public void validateReceiverPointList(List<ReceiverPoint> receiverPointList) {
        for (ReceiverPoint receiverPoint : receiverPointList) {
            // ReceiverPointDescription
            if (receiverPoint.getReceiverPointDescription() == null) {
                String message = "Value for attribute: receiverPointDescription from Object: ReceiverPoint at "
                        + receiverPoint.getReceiverPointDescription() + ", is required";
                LOG.debug(message);
                String messageView = "Należy podać: Numer licznika / opis punktu odbioru";
                throw new IncorrectDataException(messageView);
            }
            // ActualNumberOfZones
            if (receiverPoint.getActualNumberOfZones() == null) {
                String message = "Value for attribute: actualNumberOfZones from Object: ReceiverPoint at "
                        + receiverPoint.getReceiverPointDescription() + ", is required";
                LOG.debug(message);

                throw new IncorrectDataException(message);
            }
            // ActualZoneList
            if (receiverPoint.getActualZoneList().size() != receiverPoint.getActualNumberOfZones()) {
                String message = "Incorrect size of actualZoneList: from Object: ReceiverPoint at "
                        + receiverPoint.getReceiverPointDescription() + ". Expected size: "
                        + receiverPoint.getActualNumberOfZones();
                LOG.debug(message);
                throw new IncorrectDataException(message);
            }
            // ActualZoneList validate if zoneCodes are unique
            validateActualZoneCodes(receiverPoint.getActualNumberOfZones(), receiverPoint.getActualZoneList(),
                    receiverPoint.getReceiverPointDescription());

            // TariffCode
            if (receiverPoint.getTariffCode() == null) {
                String message = "Value for attribute: tariffCode from Object: ReceiverPoint at "
                        + receiverPoint.getReceiverPointDescription() + ", is required";
                LOG.debug(message);
                throw new IncorrectDataException(message);
            }
            // InvoiceList size
            if (receiverPoint.getInvoiceList().size() == 0) {
                String message = "Value for attribute: invoiceList from Object: ReceiverPoint at "
                        + receiverPoint.getReceiverPointDescription() + ", is required and cannot be empty";
                LOG.debug(message);
                throw new IncorrectDataException(message);
            }
            // InvoiceList -> Invoice data
            for (Invoice invoice : receiverPoint.getInvoiceList()) {
                // DocumentNumber
                if (invoice.getDocumentNumber() == null) {
                    String message = "Value for attribute: documentNumber from Object: ReceiverPoint at "
                            + receiverPoint.getReceiverPointDescription() + ", is required";
                    LOG.debug(message);
                    throw new IncorrectDataException(message);
                }
                // PeriodStart
                if (invoice.getPeriodStart() == null) {
                    String message = "Value for attribute: periodStart from Object: ReceiverPoint at "
                            + receiverPoint.getReceiverPointDescription() + ", is required";
                    LOG.debug(message);
                    throw new IncorrectDataException(message);
                }
                // PeriodStop
                if (invoice.getGetPeriodStop() == null) {
                    String message = "Value for attribute: periodStop from Object: ReceiverPoint at "
                            + receiverPoint.getReceiverPointDescription() + ", is required";
                    LOG.debug(message);
                    throw new IncorrectDataException(message);
                }
                // PeriodStart compare to PeriodStop
                if (invoice.getGetPeriodStop().compareTo(invoice.getPeriodStart()) < 0 ||
                        invoice.getGetPeriodStop().compareTo(invoice.getPeriodStart()) == 0) {
                    String message = "Value for attribute: periodStop from Object: ReceiverPoint at "
                            + receiverPoint.getReceiverPointDescription() + ", must be greater then periodStart";
                    LOG.debug(message);
                    String messageView = "Data początku okresu powinna być wcześniejsza od daty końca okresu. " +
                            "Punkt odbioru: " + receiverPoint.getReceiverPointDescription() + " , dokument: "
                            + invoice.getDocumentNumber();
                    throw new IncorrectDataException(messageView);
                }
                // InvoiceZoneConsumption
                BigDecimal tempTotalZoneConsumptionSummary = BigDecimal.ZERO;
                for (InvoiceZoneConsumption invoiceZoneConsumption : invoice.getInvoiceZoneConsumptionList()) {
                    // ZoneUnitConsumption
                    if (invoiceZoneConsumption.getUnitConsumption() == null) {
                        String message = "Value for attribute: unitConsumption from Object: ReceiverPoint at "
                                + receiverPoint.getReceiverPointDescription() + ", is required";
                        LOG.debug(message);
                        throw new IncorrectDataException(message);
                    }
                    // Consumption ActualZoneCode
                    if (invoiceZoneConsumption.getActualZoneCode() == null) {
                        String message = "Value for attribute: actualZoneCode from Object: ReceiverPoint at "
                                + receiverPoint.getReceiverPointDescription() + ", is required";
                        LOG.debug(message);
                        throw new IncorrectDataException(message);
                    }
                    List<ActualZone> actualZoneList = receiverPoint.getActualZoneList();
                    List<String> actualZonesCodes = getActualZonesCodes(actualZoneList);
                    if (!actualZonesCodes.contains(invoiceZoneConsumption.getActualZoneCode())) {
                        String message = "Value for attribute: actualZoneCode from InvoiceZoneConsumption, " +
                                "at ReceiverPoint: " + receiverPoint.getReceiverPointDescription()
                                + ", does not match witch provided ActualZoneCodes for receiver point";
                        LOG.debug(message);
                        throw new IncorrectDataException(message);
                    }
                    tempTotalZoneConsumptionSummary = tempTotalZoneConsumptionSummary
                            .add(invoiceZoneConsumption.getUnitConsumption());
                }
                // Invoice total Zone consumption
                if (tempTotalZoneConsumptionSummary.compareTo(BigDecimal.ZERO) == 0) {
                    String message = "Provide unitConsumption greater then zero for at least one zone at invoice: "
                            + invoice.getDocumentNumber() + "from ReceiverPoint: "
                            + receiverPoint.getReceiverPointDescription();
                    LOG.debug(message);
                    String messageView = "Należy podać zużycie większe od zera dla conajmniej jednej strefy - dokument: "
                            + invoice.getDocumentNumber() + " , w punkcie: " + receiverPoint.getReceiverPointDescription();
                    throw new IncorrectDataException(messageView);
                }
            }

            OfferCalculation receiverPointOfferCalculation = receiverPoint.getReceiverPointOfferCalculation();
            ActualReceiverPointFees actualReceiverPointFees = receiverPointOfferCalculation.getActualReceiverPointFees();

            //Actual Zones fees
            ValidateActualZonesFees(receiverPoint, actualReceiverPointFees.getActualZoneFeeList());

            //ProposalMonthLength
            validateProposalMonthLength(receiverPoint, receiverPointOfferCalculation);

            //Validate Seller proposal zone data
            List<ProposalSeller> proposalSellerList = receiverPointOfferCalculation.getProposalSellerList();
            for (ProposalSeller proposalSeller : proposalSellerList) {
                List<ProposalZoneDetails> proposalZoneDetailsList = proposalSeller.getProposalZoneDetailsList();
                for (ProposalZoneDetails proposalZoneDetails : proposalZoneDetailsList) {
                    BigDecimal sellerMinimalUnitPrice = proposalZoneDetails.getSellerMinimalUnitPrice();

                    if (sellerMinimalUnitPrice == null || sellerMinimalUnitPrice.compareTo(BigDecimal.ZERO) == 0) {
                        String messageView = "Cena operatora z cennika jest wymagana i powinna być większa od zera. "
                                + "Kalkulacja dla: " + proposalSeller.getSellerCode() + " , Strefa: " +
                                proposalZoneDetails.getActualZoneCode() + ", punkt odbioru: "
                                + receiverPoint.getReceiverPointDescription() + ".";
                        LOG.debug(messageView);
                        throw new IncorrectDataException(messageView);
                    }

                }
            }


        }
    }


    private List<String> getActualZonesCodes(List<ActualZone> actualZoneList) {
        return actualZoneList.stream().map(x -> x.getActualZoneCodeCode()).collect(Collectors.toList());
    }

    private void validateActualZoneCodes(final int actualNumberOfZones, List<ActualZone> actualZoneList,
                                         final String receiverPointDescription) {
        Set<String> distinctZoneCodes = actualZoneList
                .stream()
                .map(ActualZone::getActualZoneCodeCode)
                .collect(Collectors.toSet());
        if (actualNumberOfZones != distinctZoneCodes.size()) {
            String message = "ActualZoneList at ReceiverPoint: "
                    + receiverPointDescription + ", does not contain unique Zone Codes";
            LOG.debug(message);
            String messageView = "Należy podać unikalne nazwy stref dla licznika: " + receiverPointDescription;
            throw new IncorrectDataException(message);
        }

    }

    private void ValidateActualZonesFees(ReceiverPoint receiverPoint, List<ActualZoneFee> actualZoneFeeList) {
        for (ActualZoneFee actualZoneFee : actualZoneFeeList) {
            BigDecimal actualUnitPrice = actualZoneFee.getActualUnitPrice();
            if (actualUnitPrice == null || actualUnitPrice.compareTo(BigDecimal.ZERO) == 0) {
                String actualZoneCode = actualZoneFee.getActualZoneCode();
                String messageView = "Aktualna cena dla sterfy: " + actualZoneCode + " , jest wymagana. Punkt odbioru: "
                        + receiverPoint.getReceiverPointDescription();
                LOG.debug(messageView);
                throw new IncorrectDataException(messageView);
            }
        }
    }

    private void validateProposalMonthLength(ReceiverPoint receiverPoint, OfferCalculation receiverPointOfferCalculation) {
        BigDecimal proposalContractMonthLength = receiverPointOfferCalculation.getOfferParameters().getProposalContractMonthLength();
        if (proposalContractMonthLength == null || proposalContractMonthLength.compareTo(BigDecimal.ZERO) == 0) {
            String messageView = "Proponowana długość kontraktu jest wymagana. Punkt odbioru: "
                    + receiverPoint.getReceiverPointDescription();
            LOG.debug(messageView);
            throw new IncorrectDataException(messageView);
        }
    }


}
