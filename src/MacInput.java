package src;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MacInput {
	private String mac;
	private int signal;

	public MacInput() {
		this.mac = "";
		this.signal = 0;
	}

	public MacInput(String mac, int signal) {
		this.mac = mac;
		this.signal = signal;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public int getSignal() {
		return this.signal;
	}

	public void setSignal(int signal) {
		this.signal = signal;
	}
/*
 * returns a list of macs that the user has entered
 */
	public List<MacInput> createMacInputList() {
		Scanner sc = new Scanner(System.in);
		MacInput mac = new MacInput();
		List<MacInput> macList = new ArrayList<MacInput>();
		String m = "";
		int s;
		boolean flag = true;
		
		try {
			System.out.println("Enter macs ang signals, enter 1 to exit.");
			
			while (flag) {
				System.out.println("please enter mac");
				m = sc.nextLine();
				while (m.length() != 12 || !m.matches("\\.{2}:\\.{2}:\\.{2}:\\.{2}:\\.{2}:\\.{2}")) {
					System.out.println("Invalid mac!  Enter again");
					m = sc.nextLine();
				}
				mac.setMac(m);
				
				System.out.println("please enter signal");
				s = sc.nextInt();
				while (s < -120 || s > -30) {
					System.out.println("Invalid signal! Enter again");
					s = sc.nextInt();
				}
				mac.setSignal(s);
				
				System.out.println("Choose: 1. exit \n2.continue");
				s = sc.nextInt();
				while (s != 1 && s != 2) {
					System.out.println("Invalid signal! Enter again");
					s = sc.nextInt();
				}
				macList.add(mac);
				if (s == 1)
					flag = false;
			}
		} catch (InputMismatchException e) {
			System.out.println("Entered invalid input!");
		}
		return macList;
	}


}
