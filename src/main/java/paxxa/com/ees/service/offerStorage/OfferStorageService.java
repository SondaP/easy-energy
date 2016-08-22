package paxxa.com.ees.service.offerStorage;

import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.offer.AbstractOfferDTO;

import java.io.*;


@Service
public class OfferStorageService {

    public void saveOfferToOfferStorage(AbstractOfferDTO abstractOfferDTO, final int userId){


    }

    public String cokolwiek(){
        return "oooo";
    }




    public static byte[] serialize(Object obj) {
        if (obj == null) {
            return new byte[0];
        }
        try (ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream()) {
            try (ObjectOutputStream objectOut = new ObjectOutputStream(byteArrayOut)) {
                objectOut.writeObject(obj);
                return byteArrayOut.toByteArray();
            }
        } catch (final IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static <T> T deserialize(byte[] objectBytes, Class<T> type) {
        if (objectBytes == null || objectBytes.length == 0) {
            return null;
        }
        try (ByteArrayInputStream byteArrayIn = new ByteArrayInputStream(objectBytes);
             ObjectInputStream objectIn = new ObjectInputStream(byteArrayIn)) {
            return (T) objectIn.readObject();
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
