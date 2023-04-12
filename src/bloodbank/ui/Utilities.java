package bloodbank.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
}
