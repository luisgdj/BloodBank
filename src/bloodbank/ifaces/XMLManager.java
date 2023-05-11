package bloodbank.ifaces;

import java.io.File;

import javax.xml.bind.JAXBException;

import bloodbank.pojos.*;


public interface XMLManager {

	public File blood2Xml(Blood blood);
	public Blood xml2Blood(File xml)throws JAXBException;
	public void blood2Html(Blood blood);
}
