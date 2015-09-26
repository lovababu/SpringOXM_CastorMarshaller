package org.avol.springoxm.castor.test;

import org.avol.springoxm.castor.beans.StudentBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.castor.CastorMarshaller;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lovababu on 9/13/2015.
 */

public class OXMappingTest {

    private static final CastorMarshaller castorMarshaller = new CastorMarshaller();

    public static void main(String[] args) throws IOException {

        castorMarshaller.setTargetClass(StudentBean.class);
        castorMarshaller.setUseXSITypeAtRoot(true);
        castorMarshaller.afterPropertiesSet(); //must be invoked prior to call marshall/unmarshall.
        marshaller();

        unMarshaller("./student-bean-input.xml");
    }

    private static void marshaller() {
        StudentBean studentBean = new StudentBean();
        studentBean.setName("Srilekha");
        studentBean.setAddress("Pgvm");
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("./student-bean-output.xml");
            castorMarshaller.marshal(studentBean, new StreamResult(outputStream));
            //you can also use DOMResult/SAXResult in place of StreamResult.
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Successfully created xml from bean..");
    }

    private static void unMarshaller(String inputXML) {
        InputStream fis = null;
        try {
            fis = new FileInputStream(inputXML);
            StudentBean studentBean = (StudentBean) castorMarshaller.unmarshal(new StreamSource(fis));
            System.out.println("\n Data read from input xml: ");
            System.out.print(studentBean.getName() + " : " + studentBean.getAddress());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
