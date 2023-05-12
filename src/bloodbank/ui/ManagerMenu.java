package bloodbank.ui;

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

	public static void menu(UserManager man) {
		
		ConnectionManager conMan = new ConnectionManager(); //creamos la conexion con el jdbc
		
		nurseMan = conMan.getNurseMan();
		contractMan = conMan.getContractMan();
		donorMan = conMan.getDonorMan();
		doneeMan = conMan.getDoneeMan();
		bloodMan = conMan.getBloodMan();
		retrievalMan = conMan.getRetrievalMan();
		
		while (true) {
			System.out.println("Blood bank storage unit (manager menu):" 
					+ "\n 1. Register nurse" 
					+ "\n 2. Establish contract"
					+ "\n 3. View blood storage" 
					+ "\n 4. Access nurse information" 
					+ "\n 5. Change blood retreival limit"
					+ "\n 6. Change password"
					+ "\n 0. Log out");
			int option = Utilities.readInteger("Choose an option: ");

			switch (option) {
				case 1: {
					System.out.println("Registration of a nurse: ");
					registerNurse();
					break;
				}
				case 2: {
					System.out.println("Stablish contract:");
					registerContract();
					break;
				}
				case 3: {
					System.out.println("Check blood storage:");
					String bloodType= Utilities.askBloodType();
					bloodMan.getAmountOfBlood(bloodType);
					break;
				}
				case 4: {
					System.out.println("Access nurse information:");
					selectNurse();
					break;
				}
				case 5: {
					System.out.println("Change blood retreival limit: ");
					float limit= Utilities.readFloat("Insert the limit: ");
					retrievalMan.modifyBloodRetrievalLimit(limit);
					break;
				}
				case 6: {
					System.out.println("Change password:");
					//crear funcion para cambiar la contraseña establecida por defecto
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
		Contract contract = new Contract();
		
		Nurse n = new Nurse(name, surname, contract);
		nurseMan.insertNurse(n); 
		//meter la nurse en la base de datos mediante la javadatabaseconection(jdbc)
		
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
		int duration = Utilities.readInteger("-Duration (months): ");
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
					nurseMan.getNurse(id);
					break;
				}
				case 2: {
					System.out.println("Show patients:");
					Iterator donorIt = donorMan.getListOfDonors(id).iterator();
					Iterator doneeIt = doneeMan.getListOfDonees(id).iterator();
					
					System.out.println(" -List of donors: ");
					while(donorIt.hasNext()) {
						System.out.println(donorIt.next().toString());
					}
					System.out.println(" -List of donees: ");
					while(doneeIt.hasNext()) {
						System.out.println(doneeIt.next().toString());
					}
					break;
				}
				case 3: {
					System.out.println("Change contract:");
					
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