package paxxa.com.ees.service.utils;

import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    public Integer countDaysBetweenTwoDates(Date startDate, Date endDate) {
        Long difference = endDate.getTime() - startDate.getTime();
        Long convert = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
        return convert.intValue();

    }

    public Date getDateObjectForPattern(String yyy_MM_dd) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = yyy_MM_dd;
        Date dateObject = null;
        try {
            dateObject = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateObject;
    }
}
