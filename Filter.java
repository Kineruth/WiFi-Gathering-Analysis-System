import java.util.*;
import java.util.List;
import java.util.Scanner;

public class Filter {
	private int choice;
	private String input = "";

	public void filterFile(List<String[]> linesUnited) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Do you want to filter your file information?\n1. Yes\n2. No");
			this.choice = sc.nextInt();

			while (this.choice != 1 && this.choice != 2) { // if invalid input
				System.out.println("Invalid Input! Enter again");
				this.choice = sc.nextInt();
			}

			if (this.choice == 1) {

				System.out.println("Filter by :\n1. Time 2. Location 3. ID ");
				this.choice = sc.nextInt();

				while (this.choice < 1 && this.choice > 3) { // if invalid input
					System.out.println("Invalid Input! Enter again");
					this.choice = sc.nextInt();
				}
				filterLines(linesUnited);
			}
		} catch (Exception e) {
			System.out.println("Entered invalid input! Converting file without filtering");
		}

	}

	public int getChoice() {
		return choice;
	}

	// ***************************PRIVATE*****************************

	// https://stackoverflow.com/questions/9146224/arraylist-filter -
	// List.remove & Predicate

	private void filterByTime(List<String[]> linesUnited) {
		Scanner sc = new Scanner(System.in);
		try {
			int time = 0;
			System.out.println("Enter : date (dd/mm/yyyy) : ");
			this.input = sc.nextLine();
			System.out.println("Enter : hour (1 to 12) : ");
			time = sc.nextInt();
			System.out.println("Choose: \n1.AM \n2.PM");
			if (sc.nextInt() == 1)
				this.input += " " + time + ":00";
			else {
				if (time < 12)
					this.input += " " + (time + 12) + ":00";
				else
					this.input += " 00:00";
			}
			linesUnited.removeIf(line -> !(line[0].split(" ")[0].equals(this.input.split(" ")[0])
					&& (line[0].split(" ")[1].split(":")[0].equals(this.input.split(" ")[1].split(":")[0]))));
		} catch (Exception e) {
			System.out.println("Entered invalid input! Converting file without filtering");
		}
	}

	private void filterByPlace(List<String[]> linesUnited) {
		Scanner sc = new Scanner(System.in);
		try {
			this.input = "";
			System.out.println("We'll get from you a point(LAN,LON) & a km radius for the location\nEnter Latitude : ");
			this.input = sc.nextDouble() + "";
			System.out.println("Enter Longitude : ");
			this.input += "," + sc.nextDouble() + "";
			System.out.println("Enter Radius (km) : ");
			this.input += "," + sc.nextDouble() + "";

			LocPoint p1 = new LocPoint(this.input.split(",")[0], this.input.split(",")[1]);
			linesUnited.removeIf(line -> !(p1.pointInCircle(new LocPoint(line[2], line[3]),
					Double.parseDouble(this.input.split(",")[2]))));
		} catch (Exception e) {
			System.out.println("Entered invalid input! Converting file without filtering");
		}
	}

	private void filterByID(List<String[]> linesUnited) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter your chosen ID : ");
			this.input = sc.nextLine();

			linesUnited.removeIf(line -> !(this.input.equals(line[1])));
		} catch (Exception e) {
			System.out.println("Entered invalid input! Converting file without filtering");
		}
	}

	private void filterLines(List<String[]> linesUnited) {
		if (this.choice == 1)
			filterByTime(linesUnited);
		else if (this.choice == 2)
			filterByPlace(linesUnited);
		else
			filterByID(linesUnited);

	}

}
