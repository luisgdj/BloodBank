package bloodbank.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;

import bloodbank.ifaces.*;
import bloodbank.pojos.*;

public abstract class Utilities {

	private static InputStreamReader isr = new InputStreamReader(System.in);
	private static BufferedReader br = new BufferedReader(isr);

	public static int readInteger(String question) {

		int num;
		String line;
		System.out.print(question);
		
		while (true) {
			try {
				line = br.readLine();
				num = Integer.parseInt(line);
				return num;
			} catch (IOException ioe) {
				System.out.println("ERROR: unable to read");
				
			} catch (NumberFormatException nfe) {
				System.out.println("Error: must be a whole number.");
			}
		}
	}
	
	public static long readLong(String question) {

		long num;
		String line;
		System.out.print(question);
		
		while (true) {
			try {
				line = br.readLine();
				num = Long.parseLong(line);
				return num;
			} catch (IOException ioe) {
				System.out.println("ERROR: unable to read");
				
			} catch (NumberFormatException nfe) {
				System.out.println("Error: must be a whole number.");
			}
		}
	}

	public static float readFloat(String question) {

		float num;
		String line;
		System.out.print(question);

		while (true) {
			try {
				line = br.readLine();
				num = Float.parseFloat(line);
				return num;

			} catch (IOException ioe) {
				System.out.println("ERROR: unable to read");
				
			} catch (NumberFormatException nfe) {
				System.out.println("Error: must be a real number.");
			}
		}
	}

	public static String readString(String question) {

		String line;
		System.out.print(question);

		while (true) {
			try {
				line = br.readLine();
				return line;

			} catch (IOException ioe) {
				System.out.println("ERROR: unable to read");
			}
		}
	}
	
	public static Date askDate(String question) {
		
		while(true) {
			System.out.println(question);
			int day = Utilities.readInteger("   Day: ");
			int month = Utilities.readInteger("   Month: ");
			int year = Utilities.readInteger("   Year: ");
			return new Date(year,month,day);
		}
	}
	
	public static String askBloodType() {
		
		while(true){
			System.out.println(" -Choose blood type:"
					+ "\n   1. A+" 
					+ "\n   2. A-" 
					+ "\n   3. B+" 
					+ "\n   4. B-" 
					+ "\n   5. AB+" 
					+ "\n   6. AB-" 
					+ "\n   7. 0+" 
					+ "\n   8. 0-" );
			int option = Utilities.readInteger("  Option: ");
			
			switch(option) {
				case 1: 
					return "A+";
				case 2: 
					return "A-";
				case 3: 
					return "B+";
				case 4: 
					return "B-";
				case 5: 
					return "AB+";
				case 6: 
					return "AB-";
				case 7: 
					return "0+";
				case 8: 
					return "0-";
					
				default:
					System.out.println("ERROR: Option not valid");
			}
		}
	}
}
