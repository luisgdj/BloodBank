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
}
