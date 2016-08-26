package paxxa.com.ees.controller.exception.offerStorage;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OfferNotFountException extends RuntimeException {

    public OfferNotFountException(Integer offerStorageId) {
        super("could not find offer '" + offerStorageId + "'.");
    }

}
