package paxxa.com.ees.service.offerStorage;

import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.offer.AbstractOfferDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;


@Service
public class OfferStorageService {

    public void saveOfferToOfferStorage(AbstractOfferDTO abstractOfferDTO, final int userId) {

    }

    public byte[] marshall(Class className, Object input) throws JAXBException, ClassNotFoundException {
        JAXBContext contextObj = JAXBContext.newInstance(className);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        marshallerObj.marshal(input, byteArrayOut);
        return byteArrayOut.toByteArray();

    }


}
