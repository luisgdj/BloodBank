package bloodbank.ifaces;

import java.io.File;

import bloodbank.pojos.*;

public interface XMLManager {

	public File makeBloodXML(Blood blood);
	public Blood getBloodXML(File xml);
	public void makeBloodHTML(Blood blood);
	
	public File makeNurseXML(Nurse nurse);
	public Nurse getNurseXML(File xml);
	public void makeNurseHTML(Nurse nurse);
}
