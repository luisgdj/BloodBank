package bloodbank.xml.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import bloodbank.ifaces.XMLManager;
import bloodbank.pojos.Blood;

public class XMLManagerImpl implements XMLManager {

	@Override
	public File makeBloodXML(Blood blood) {

		try {
			// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Blood.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/Blood.xml");
			marshaller.marshal(blood, file);
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Blood getBloodXML(File xml) {

		try {
			// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Blood.class);

			// Get the unmarshaller
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			// Use the Unmarshaller to unmarshal the XML document from a file
			Blood blood = (Blood) unmarshaller.unmarshal(xml);
			return blood;

		} catch (JAXBException e) {
			System.out.println("Error: unable to load the XML file");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void makeBloodHTML(Blood blood) {

		File file = makeBloodXML(blood);
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File("./xmls/Blood-Style.xslt")));
			transformer.transform(new StreamSource(file), new StreamResult(new File("./xmls/External-Blood.html")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
