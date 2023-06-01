package bloodbank.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bloodbank.ifaces.*;
import bloodbank.pojos.*;
import bloodbank.jdbc.*;

public abstract class ManagerMenu {

	private static NurseManager nurseMan;
	private static ContractManager contractMan;
	private static BloodManager bloodMan;
	private static UserManager userMan;
	private static DonorManager donorMan;
	private static DoneeManager doneeMan;
	private static BloodRetrievalLimitManager retrievalMan;
	private static XMLManager xmlMan;

	public static void menu(User user, UserManager man) {

		ConnectionManager conMan = new ConnectionManager(); // creamos la conexion con el jdbc

		nurseMan = conMan.getNurseMan();
		contractMan = conMan.getContractMan();
		donorMan = conMan.getDonorMan();
		doneeMan = conMan.getDoneeMan();
		bloodMan = conMan.getBloodMan();
		retrievalMan = conMan.getRetrievalMan();
		xmlMan = conMan.getXmlMan();
		userMan = man;

		while (true) {
			
			System.out.println("\nBlood bank storage unit (manager menu):" 
					+ "\n 1. Register nurse"
					+ "\n 2. Establish contract" 
					+ "\n 3. View blood storage" 
					+ "\n 4. Access nurse information"
					+ "\n 5. Change blood retreival limit" 
					+ "\n 6. Change password"
					+ "\n 7. Save blood information to file" 
					+ "\n 8. Save nurse information to file"
					+ "\n 9. Load information from XML file"
					+ "\n 0. Log out");
			int option = Utilities.readInteger("Choose an option: ");

			switch (option) {
				case 1: {
					System.out.println("\nRegistration of a nurse: ");
					registerNurse();
					break;
				}
				case 2: {
					System.out.println("\nStablish contract:");
					registerContract();
					break;
				}
				case 3: {
					System.out.println("\nCheck blood storage:");
					String bloodType = Utilities.askBloodType(" -Choose blood type: ");
					float amount = bloodMan.getTotalAmountOfBlood(bloodType);
					System.out.println(" -Total amount of " + bloodType + " blood: " + amount);
					break;
				}
				case 4: {
					System.out.println("\nAccess nurse information:");
					Integer id = selectNurse();
					if(id != null){
						checkNurseInfo(id);
					}
					break;
				}
				case 5: {
					System.out.println("\nChange blood retreival limit: ");
					float limit = Utilities.readFloat(" -Limit: ");
					retrievalMan.updateBloodRetrievalLimit(limit);
					System.out.println(" -Blood retreival limit changed to " + limit + " mL");
					break;
				}
				case 6: {
					System.out.println("\nChange password:");
					String password = Utilities.readString(" -Type new password: ");
					user = man.changePassword(user, password);
					System.out.println(" -Password changed correctly to " + user.getPassword());
					break;
				}
				case 7: {
					System.out.println("\nSave blood information to file:");
					saveBloodToFile();
					break;
				}
				case 8: {
					System.out.println("\nSave nurse information to file:");
					saveNurseToFile();
					break;
				}
				case 9: {
					System.out.println("\nLoad information from XML file: ");
					List<String> fileNames = getXMLFilenamesInFolder();
					if (fileNames.size() != 0) {
						loadFileFromXml(fileNames);
					}else {
						System.out.println(" ERROR: there are no XML files available.");
					}
					break;
				}
				case 0: {
					System.out.println("Program terminated.");
					return;
				}
				default: {
					System.out.println(" ERROR: Invalid option.");
				}
			}
		}
	}

	// OPTION 1:
	private static void registerNurse() {

		System.out.println("Input nurse data: ");
		String name = Utilities.readString(" -Name: ");
		String surname = Utilities.readString(" -Surname: ");
		String email = Utilities.readString(" -Email: ");
		System.out.println(" -Default contract stablished");
		Contract contract = contractMan.getContract(1);
		contract.setNurses(nurseMan.getListOfNursesOfContract(1));

		System.out.println(" -Stablish login information: ");
		String username = Utilities.readString("   Username: ");
		String password = Utilities.readString("   Password: ");

		Nurse n = new Nurse(name, surname, email, contract);
		nurseMan.insertNurse(n);

		User user = new User(username, password, email);
		userMan.register(user);
		Role role = userMan.getRole("nurse");
		userMan.assignRole(user, role);
	}

	// OPTION 2:
	private static void registerContract() {

		System.out.println("Imput contract data: ");
		int duration = Utilities.readInteger(" -Duration (months): ");
		float salary = Utilities.readInteger(" -Salary: ");

		Contract c = new Contract(duration, salary);
		contractMan.insertContract(c);
	}

	// OPTION 4,8:
	private static Integer selectNurse() {

		String name = Utilities.readString(" -Search nurse by name: ");
		List<Nurse> list = nurseMan.getNursesByName(name);
		Iterator<Nurse> it = list.iterator();
		while(it.hasNext()) {
			Nurse n = it.next();
			System.out.println("   (" + n.getId() + ") "+ n.getName() + " " + n.getSurname());
		}
		
		Integer id = Utilities.readInteger(" -Choose an id: ");
		if (nurseMan.getNurse(id) != null){
			return id;
		}
		return null;
	}

	// OPTION 4: (NURSE INFO)
	private static void checkNurseInfo(Integer id) {

		while (true) {
			System.out.println("\nCheck nurse info:" + "\n 1. Check personal information" + "\n 2. Show patients"
					+ "\n 3. Change contract" + "\n 0. Return to menu");
			int option = Utilities.readInteger("Option: ");

			switch (option) {
				case 1: {
					System.out.println("\nCheck personal information");
					Nurse n = nurseMan.getNurse(id);
					System.out.println(n.toString());
					break;
				}
				case 2: {
					System.out.println("\nShow patients:");
					Iterator<Donor> donorIt = donorMan.getListOfDonors(id).iterator();
					Iterator<Donee> doneeIt = doneeMan.getListOfDonees(id).iterator();
	
					System.out.println(" -Donors attended: ");
					while (donorIt.hasNext()) {
						Donor donor = donorIt.next();
						System.out.println("   (" + donor.getId() + ")   " + donor.getName() + " " + donor.getSurname());
					}
					System.out.println(" -Donees attended: ");
					while (doneeIt.hasNext()) {
						Donee donee = doneeIt.next();
						System.out.println("   (" + donee.getId() + ")   " + donee.getName() + " " + donee.getSurname());
					}
					break;
				}
				case 3: {
					System.out.println("\nChange contract:");
					List<Contract> contracts = contractMan.getListOfContracts();
					Iterator<Contract> it = contracts.iterator();
					System.out.println(" -List of contracts: ");
					while (it.hasNext()) {
						System.out.println("  Contract " + it.next().toString());
					}
					int contract_id = Utilities.readInteger(" -Choose a contract: ");
					nurseMan.assignContractToNurse(contract_id, option);
					System.out.println(" -Updated correctly to contract " + contract_id);
					break;
				}
				case 0: {
					System.out.println("Return to manager menu.");
					return;
				}
				default: {
					System.out.println(" ERROR: Invalid option.");
				}
			}
		}
	}
	
	//OPTION 7: (STORE BLOOD FILES)
	private static void saveBloodToFile() {
		
		System.out.println(" -Choose file type: "
				+ "\n   1. XML file"
				+ "\n   2. HTML file");
		int fileType = Utilities.readInteger(" -File type: ");
		//String name = Utilities.askBloodType(" -Choose a blood type to store in file: ");
		
		switch(fileType) {
			case 1:
				System.out.println("Save to XML file:");
				saveBloodToXml();
				break;
			case 2:
				System.out.println("Save to HTML file:");
				saveBloodToHtml();
				break;
			default:
				System.out.println(" ERROR: invalid option.");
		}
	}
	
	//OPTION 7.1:
	private static void saveBloodToXml() {
		
		Integer id = selectBlood();
		if(id != null){
			Blood blood = bloodMan.getBlood(id);
			System.out.println(blood.toString());
			xmlMan.makeBloodXML(blood);
		}
	}
	
	//OPTION 7.2:
	private static void saveBloodToHtml() {
		
		Integer id = selectBlood();
		if(id != null){
			Blood blood = bloodMan.getBlood(id);
			System.out.println(blood.toString());
			xmlMan.makeBloodHTML(blood);
		}
	}
	
	//OPTION 7: (aux)
	private static Integer selectBlood() {

		String bloodType = Utilities.askBloodType(" -Search blood by type: ");
		List<Blood> list = bloodMan.getBloodByBloodType(bloodType);
		Iterator<Blood> it = list.iterator();
		while(it.hasNext()) {
			Blood b = it.next();
			System.out.println("  " + b.toString());
		}
		
		Integer id = Utilities.readInteger(" -Choose an id: ");
		if (bloodMan.getBlood(id) != null){
			return id;
		}
		return null;
	}
	
	
	//OPTION 8: (STORE NURSE FILES)
	private static void saveNurseToFile() {
		
		System.out.println(" -Choose file type: "
				+ "\n   1. XML file"
				+ "\n   2. HTML file");
		int fileType = Utilities.readInteger(" -File type: ");
		switch(fileType) {
			case 1:
				System.out.println("Save to XML file:");
				saveNurseToXml();
				break;
			case 2:
				System.out.println("Save to HTML file:");
				saveNurseToHtml();
				break;
			default:
				System.out.println(" ERROR: Invalid option.");
		}
	}
	
	//OPTION 8.1:
	private static void saveNurseToXml() {
		
		Integer id = selectNurse();
		if(id != null){
			Nurse nurse = nurseMan.getNurse(id);
			System.out.println(nurse.toString());
			xmlMan.makeNurseXML(nurse);
		}
	}
	
	//OPTION 8.2:
	private static void saveNurseToHtml() {
			
		Integer id = selectNurse();
		if(id != null){
			Nurse nurse = nurseMan.getNurse(id);
			System.out.println(nurse.toString());
			xmlMan.makeNurseHTML(nurse);
		}
	}
	
	
	//OPTION 9:
	private static void loadFileFromXml(List<String> fileNames) {
		
		int cont = 1;
		System.out.println(" -Witch file do you want to load: ");
		Iterator<String> it = fileNames.iterator();
		while(it.hasNext()) {
			System.out.println("   " + cont + ". " + it.next());
			cont++;
		}
		
		int option = 0;
		do {
			option = Utilities.readInteger(" -Choose file: ") - 1;
			if(option < 0 || option >= fileNames.size()) {
				System.out.println(" ERROR: Invalid option.");
			}
		} while(option < 0 || option >= fileNames.size());
		
		// extract the name from the list and create the file
		File fileName = new File("./xmls/" + fileNames.get(option));
		
		if(fileNames.get(option).endsWith("-Nurse.xml")) {
			Nurse n = xmlMan.getNurseXML(fileName);
			//ERROR: NO ESTAMOS GUARDANDO EL ID DE LOS CONTRACTS (LO NECESITAMOS)
			nurseMan.insertNurse(n);		
		} 
		if(fileNames.get(option).endsWith("-Blood.xml")){
			Blood b = xmlMan.getBloodXML(fileName);
			//ERROR: NO ESTAMOS GUARDANDO LOS IDS DE LOS DONORS Y DONEES (LOS NECESITAMOS)
			bloodMan.insertBloodDonation(b);
		}				
	}

	//OPTION 9: (aux)
	private static List<String> getXMLFilenamesInFolder() {
		
		List<String> xmlFileNames = new ArrayList<>();
		File folder = new File("./xmls");

		if (folder.isDirectory()) {
			File[] files = folder.listFiles();

			if (files != null) {
				for (File file : files) {
					if (file.isFile() && file.getName().toLowerCase().endsWith(".xml")) {
						xmlFileNames.add(file.getName());
					}
				}
			}
		}
		return xmlFileNames;
	}
}
