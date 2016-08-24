package paxxa.com.ees.service.utils;

import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class UtilsService {

    public byte[] marshall(Class className, Object input) {
        JAXBContext jaxContextObj;
        try {
            jaxContextObj = JAXBContext.newInstance(className);
            Marshaller marshallerObj = jaxContextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            marshallerObj.marshal(input, byteArrayOut);
            return byteArrayOut.toByteArray();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Error while marshaling");
    }

    public Object unMarshall(Class className, byte[] bytes) {
        JAXBContext jaxContextObj = null;
        try {
            jaxContextObj = JAXBContext.newInstance(className);
            Unmarshaller jaxbUnmarshaller = jaxContextObj.createUnmarshaller();

            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            return jaxbUnmarshaller.unmarshal(bis);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Error while unMarshaling");
    }
}
