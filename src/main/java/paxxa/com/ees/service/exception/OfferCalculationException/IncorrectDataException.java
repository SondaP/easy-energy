package paxxa.com.ees.service.exception.OfferCalculationException;


public class IncorrectDataException extends RuntimeException {

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }
    public IncorrectDataException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public IncorrectDataException() {
        super();
    }

}
