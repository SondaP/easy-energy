package paxxa.com.ees.service.offerStorage;

import org.springframework.stereotype.Service;
import paxxa.com.ees.dto.offer.AbstractOfferDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;


@Service
public class OfferStorageService {

    public void saveOfferToOfferStorage(AbstractOfferDTO abstractOfferDTO, final int userId) {

    }

    public byte[] marshall(Class className, Object input) throws JAXBException, ClassNotFoundException {
        JAXBContext jaxContextObj = JAXBContext.newInstance(className);

        Marshaller marshallerObj = jaxContextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        marshallerObj.marshal(input, byteArrayOut);
        return byteArrayOut.toByteArray();

    }

    public Object unMarshall(Class className, byte[] bytes) throws JAXBException {
        JAXBContext jaxContextObj = JAXBContext.newInstance(className);
        Unmarshaller jaxbUnmarshaller = jaxContextObj.createUnmarshaller();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        return jaxbUnmarshaller.unmarshal(bis);
    }


}
