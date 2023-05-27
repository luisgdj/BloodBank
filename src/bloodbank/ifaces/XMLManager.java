package bloodbank.ifaces;

import java.io.File;

import javax.xml.bind.JAXBException;

import bloodbank.pojos.*;

public interface XMLManager {

	/**
	 * Convert the Blood object to a xml file
	 *
	 * @param Blood object to convert
	 * @return XML File containing a blood object
	 */
	public File makeBloodXML(Blood blood);

	/**
	 * Convert the Blood object to a HTML file
	 * 
	 * @param Blood object to convert
	 */
	public void makeBloodHTML(Blood blood);

	/**
	 * Convert the XML file to a Blood object
	 *
	 * @param XML File containing a blood object
	 * @return Blood object converted
	 */
	public Blood getBloodXML(File xml);

	/**
	 * Convert the Nurse object to a xml file
	 *
	 * @param Nurse object to convert
	 * @return XML File containing a nurse object
	 */
	File makeNurseXML(Nurse nurse);

	/**
	 * Convert the XML file to a Nurse object
	 *
	 * @param XML File containing a nurse object
	 * @return Blood object converted
	 */
	Nurse getNurseXML(File xml);

	/**
	 * Convert the Nurse object to a HTML file
	 *
	 * @param Nurse object to convert
	 */
	void makeNurseHTML(Nurse nurse);
}
