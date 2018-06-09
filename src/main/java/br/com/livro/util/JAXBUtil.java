package br.com.livro.util;

import br.com.livro.models.Carro;
import br.com.livro.domain.ListaCarros;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;
import javax.xml.stream.XMLStreamWriter;


public class JAXBUtil {
    private static JAXBUtil instance;

    public static JAXBUtil getInstance() {
        return instance;
    }

    private  static JAXBContext context;

    static {
        try{
            // informa ao JAXB quais são as classes para gerar o XML
            context = JAXBContext.newInstance(ListaCarros.class, Carro.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toXML(Object object) throws IOException {
        try {
            StringWriter stringWriter = new StringWriter();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, stringWriter);
            String xml = stringWriter.toString();
            return xml;
        }catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJSON (Object object) throws IOException {

        try {
            StringWriter stringWriter = new StringWriter();
            Marshaller marshaller = context.createMarshaller();
            MappedNamespaceConvention convention = new MappedNamespaceConvention();
            XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(convention, stringWriter);
            marshaller.marshal(object, xmlStreamWriter);
            String json = stringWriter.toString();
            return json;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

}
