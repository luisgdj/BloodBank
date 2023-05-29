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
import bloodbank.pojos.*;

public class XMLManagerImpl implements XMLManager {

	/**
	 * Convert the Blood object to a xml file
	 *
	 * @param Blood object to convert
	 * @return XML File containing a blood object
	 */
	@Override
	public File makeBloodXML(Blood blood) {

		try {
			// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Blood.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/External-Blood.xml");
			marshaller.marshal(blood, file);
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Convert the XML file to a Blood object
	 *
	 * @param XML File containing a blood object
	 * @return Blood object converted
	 */
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
	
	/**
	 * Convert the Blood object to a HTML file
	 *
	 * @param Blood object to convert
	 */
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
	
	
	/**
	 * Convert the Nurse object to a xml file
	 *
	 * @param Nurse object to convert
	 * @return XML File containing a nurse object
	 */
	@Override
	public File makeNurseXML(Nurse nurse) {

		try {
			// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Nurse.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/External-Nurse.xml");
			marshaller.marshal(nurse, file);
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Convert the XML file to a Nurse object
	 *
	 * @param XML File containing a nurse object
	 * @return Blood object converted
	 */
	@Override
	public Nurse getNurseXML(File xml) {

		try {
			// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Nurse.class);

			// Get the unmarshaller
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			// Use the Unmarshaller to unmarshal the XML document from a file
			Nurse nurse = (Nurse) unmarshaller.unmarshal(xml);
			return nurse;

		} catch (JAXBException e) {
			System.out.println("Error: unable to load the XML file");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Convert the Nurse object to a HTML file
	 *
	 * @param Nurse object to convert
	 */
	@Override
	public void makeNurseHTML(Nurse nurse) {

		File file = makeNurseXML(nurse);
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File("./xmls/Nurse-Style.xslt")));
			transformer.transform(new StreamSource(file), new StreamResult(new File("./xmls/External-Nurse.html")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
