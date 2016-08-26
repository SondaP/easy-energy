package paxxa.com.ees.service.exception.OfferCalculationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class MissingDataException extends RuntimeException {

    public MissingDataException(String attributeName, String objectName) {
        super("Value for attribute: " + attributeName + ", from Object: " + objectName + " is required");
    }

}
