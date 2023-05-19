package bloodbank.ifaces;

import java.io.File;

import javax.xml.bind.JAXBException;

import bloodbank.pojos.*;


public interface XMLManager {

	public File makeBloodXML(Blood blood);
	public void makeBloodHTML(Blood blood);
	public Blood getBloodXML(File xml);
}
