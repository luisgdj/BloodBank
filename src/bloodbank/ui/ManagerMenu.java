package bloodbank.ui;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBException;

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

	public static void menu(UserManager man) {
		
		ConnectionManager conMan = new ConnectionManager(); //creamos la conexion con el jdbc
		//xmlMan = new XMLManager();
		
		nurseMan = conMan.getNurseMan();
		contractMan = conMan.getContractMan();
		donorMan = conMan.getDonorMan();
		doneeMan = conMan.getDoneeMan();
		bloodMan = conMan.getBloodMan();
		retrievalMan = conMan.getRetrievalMan();
		
		while (true) {
			System.out.println("\nBlood bank storage unit (manager menu):" 
					+ "\n 1. Register nurse"
					+ "\n 2. Establish contract"
					+ "\n 3. View blood storage" 
					+ "\n 4. Access nurse information" 
					+ "\n 5. Change blood retreival limit"
					+ "\n 6. Change password"
					+ "\n 7. Save blood information in an XML file"
					+ "\n 8. Save blood information in an HTML file"
					+ "\n 8. Load blood information"
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
					String bloodType= Utilities.askBloodType();
					float amount = bloodMan.getAmountOfBlood(bloodType);
					System.out.println(" Amount of " + bloodType + " blood: " + amount);
					break;
				}
				case 4: {
					System.out.println("\nAccess nurse information:");
					selectNurse();
					break;
				}
				case 5: {
					System.out.println("\nChange blood retreival limit: ");
					float limit= Utilities.readFloat(" -Limit: ");
					retrievalMan.modifyBloodRetrievalLimit(limit);
					break;
				}
				case 6: {
					System.out.println("Change password:");
					//crear funcion para cambiar la contraseña establecida por defecto
					break;
				}
				case 7: {
					System.out.println("Save blood information:");
					xmlMan.blood2Xml(null);
					break;
				}
				case 8: {
					System.out.println("Save blood information:");
					xmlMan.blood2Html(null);
					break;
				}
				case 9: {
					System.out.println("Load blood information:");
					try {
						xmlMan.xml2Blood(null);
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				case 0: {
					System.out.println("Program terminated");
					return;
				}
				default: {
					System.out.println("ERROR: Invalid option");
				}
			}
		}
	}

	//OPTION 1:
	private static void registerNurse() {

		System.out.println("Input nurse data: ");
		String name = Utilities.readString(" -Name: ");
		String surname = Utilities.readString(" -Surname: ");
		System.out.println(" -Default contract stablished");
		Contract contract = contractMan.getContract(1);
		
		Nurse n = new Nurse(name, surname, contract);
		System.out.println(n.toString());
		nurseMan.insertNurse(n); 
		
		String username = Utilities.readString(" -Username: ");
		String password = Utilities.readString(" -Password: ");
		
		User user = new User(username, password);
		userMan.register(user);
		Role role = userMan.getRole("nurse");
		userMan.assignRole(user, role);
	}
	
	//OPTION 2:
	private static void registerContract() {

		System.out.println("Imput contract data: ");
		int duration = Utilities.readInteger(" -Duration (months): ");
		float salary = Utilities.readInteger(" -Salary: ");
		
		Contract c = new Contract(duration, salary);
		contractMan.insertContract(c);
	}
						
	
	//OPTION 4:
	private static void selectNurse() {

		System.out.println("Search nurse by name: ");
		String name= Utilities.readString(" -Name: ");
		List<Nurse> listNur = nurseMan.searchNurseByName(name);
		System.out.println(listNur);
		System.out.println("Please choose a Nurse.");
		Integer id = Utilities.readInteger(" -Id: ");
		
		checkNurseInfo(id);
	}
	
	//OPTION 4: (NURSE INFO)
	private static void checkNurseInfo(Integer id) {
		
		while (true) {
			System.out.println("Check nurse info:" 
					+ "\n 1. Check personal information" 
					+ "\n 2. Show patients"
					+ "\n 3. Change contract" 
					+ "\n 0. Return to menu");
			int option = Utilities.readInteger("Option: ");

			switch (option) {
				case 1: {
					System.out.println("Check personal information");
					Nurse n = nurseMan.getNurse(id);
					System.out.println(n.toString());
					break;
				}
				case 2: {
					System.out.println("Show patients:");
					Iterator<Donor> donorIt = donorMan.getListOfDonors(id).iterator();
					Iterator<Donee> doneeIt = doneeMan.getListOfDonees(id).iterator();
					
					System.out.println(" -List of donors: ");
					while(donorIt.hasNext()) {
						System.out.println("   " + donorIt.next().getName() + " " + donorIt.next().getSurname());
					}
					System.out.println(" -List of donees: ");
					while(doneeIt.hasNext()) {
						System.out.println("   " + doneeIt.next().getName() + " " + doneeIt.next().getSurname());
					}
					break;
				}
				case 3: {
					System.out.println("Change contract:");
					List<Contract> contracts = contractMan.getListOfContracts();
					Iterator<Contract> it = contracts.iterator();
					while(it.hasNext()) {
						System.out.println("  Contract " + it.next().toString());
					}
					int contract_id = Utilities.readInteger("Choose a contract: ");
					nurseMan.updateContract(contract_id, id);
					break;
				}
				case 0: {
					return;
				}
				default: {
					System.out.println("ERROR: Invalid option");
				}
			} 
		}
	}
}