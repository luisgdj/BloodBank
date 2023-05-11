package bloodbank.ui;

import java.util.List;

import bloodbank.ifaces.*;
import bloodbank.pojos.*;
import bloodbank.jdbc.*;
import bloodbank.jpa.JPAUserManager;

public abstract class ManagerMenu {

	private static NurseManager nurseMan;
	private static ContractManager contractMan;
	private static BloodManager bloodMan;
	private static UserManager userMan;
<<<<<<< HEAD
=======
	private static DonorManager donorMan;
	private static DoneeManager doneeMan;
	private static BloodRetrievalLimitManager retrievalMan;
>>>>>>> branch 'master' of https://github.com/luisgdj/BloodBank

	public static void menu(UserManager man) {
		
		ConnectionManager conMan = new ConnectionManager(); //creamos la conexion con el jdbc
		nurseMan = new JDBCNurseManager(conMan.getConnection());
		contractMan = new JDBCContractManager(conMan.getConnection());
<<<<<<< HEAD
		bloodMan = new JDBCBloodManager(conMan.getConnection());
		userMan = man;
=======
		donorMan = new JDBCDonorManager(conMan.getConnection());
		doneeMan = new JDBCDoneeManager(conMan.getConnection());
		bloodMan = new JDBCBloodManager(conMan.getConnection(), donorMan, doneeMan);
		retrievalMan = new JDBCBloodRetrievalLimitManager(conMan.getConnection());
>>>>>>> branch 'master' of https://github.com/luisgdj/BloodBank

		retrievalMan.setBloodRetrievalLimit(0);
		while (true) {
			System.out.println("Blood bank storage unit (manager menu):" 
					+ "\n 1. Register nurse" 
					+ "\n 2. Establish contract"
					+ "\n 3. View blood storage" 
					+ "\n 4. Access nurse information" 
					+ "\n 5. Change blood retreival limit" 
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
<<<<<<< HEAD
					
					//funcion que no permita sacar mas sangre de x
=======
					float limit= Utilities.readFloat("Insert the limit: ");
					retrievalMan.modifyBloodRetrievalLimit(limit);
>>>>>>> branch 'master' of https://github.com/luisgdj/BloodBank
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
					
					break;
				}
				case 2: {
	
					break;
				}
				case 3: {
	
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
<<<<<<< HEAD
		
	}
	
	private static void personalInfoNurse(Integer id) {
			Nurse nurse= nurseMan.getNurse(id);
			System.out.println(nurse.toString());
	

	}
	
	private static void setBloodRetivalLimit() {
		
		bloodMan.retreiveBlood(0);
		
		
=======
>>>>>>> branch 'master' of https://github.com/luisgdj/BloodBank
	}
}
