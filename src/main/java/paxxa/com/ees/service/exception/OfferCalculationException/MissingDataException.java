package paxxa.com.ees.service.exception.OfferCalculationException;


public class MissingDataException extends RuntimeException {

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }
    public MissingDataException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public MissingDataException() {
        super();
    }

}
