

import java.util.Scanner;

public class Filter {
	private int choice;

	public String chooseFilter() {
		String ans;
		System.out.println(
				"Please enter how you want to filter your file information:\n1. By Time\n2. By Location\n3. By ID");

		Scanner sc = new Scanner(System.in);
		this.choice = sc.nextInt();
		while (this.choice < 1 && this.choice > 3) {

			this.choice = sc.nextInt();
			System.out.println("Invalid Input! this number is not in the options - Enter again");
		}
		return getInputUser(this.choice);
	}

	public int getChoice() {
		return choice;
	}

	// ***************************PRIVATE*****************************

	private String getInputUser(int choice) {
		String ans = "";
		int time = 0;
		Scanner sc = new Scanner(System.in);
		if (this.choice == 1) {
			System.out.println("Enter : date (dd/mm/yyyy) : ");
			ans = sc.nextLine();
			System.out.println("Enter : hour (1 to 12) : ");
			time = sc.nextInt();
			System.out.println("Choose: \n1.AM \n2.PM");
			if (sc.nextInt() == 1)
				ans += " " + time + ":00";
			else {
				if (time < 12)
					ans += " " + (time + 12) + ":00";
				else
					ans += " 00:00";
			}

			return ans;
		} else if (this.choice == 2) {
			System.out.println("We'll get from you a point(LAN,LON) & a km radius for the location\nEnter Latitude : ");
			ans = sc.nextDouble() + "";
			System.out.println("Enter Longitude : ");
			ans += "," + sc.nextDouble() + "";
			System.out.println("Enter Radius (km) : ");
			ans += "," + sc.nextDouble() + "";
			return ans;

		} else {
			System.out.println("Enter your chosen ID : ");
			return ans = sc.nextLine();
		}

	}

}
