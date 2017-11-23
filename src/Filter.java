package src;

import java.util.*;
import java.util.List;
import java.util.Scanner;

public class Filter {
	private int choice;

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

			System.out.println("Enter : date (yyyy-mm-dd) : ");
			String userDate = sc.nextLine();
			System.out.println("Enter hour (1 to 24) : ");
			int userHour = sc.nextInt();

			linesUnited.removeIf(line -> !(line[0].split(" ")[0].equals(userDate)
					&& (line[0].split(" ")[1].split(":")[0].equals(userHour))));
		} catch (Exception e) {
			System.out.println("Entered invalid input! Converting file without filtering");
		}
	}

	private void filterByPlace(List<String[]> linesUnited) {
		Scanner sc = new Scanner(System.in);
		try {

			System.out.println("We'll get from you a point(LAN,LON) & a km radius for the location\nEnter Latitude : ");
			String userLat = sc.nextDouble() + "";
			System.out.println("Enter Longitude : ");
			String userLon = sc.nextDouble() + "";
			System.out.println("Enter Radius (km) : ");
			String userRadius = sc.nextDouble() + "";

			LocPoint p1 = new LocPoint(userLat, userLon);
			linesUnited
					.removeIf(line -> !(p1.pointInCircle(new LocPoint(line[2], line[3]), Double.parseDouble(userLat))));
		} catch (Exception e) {
			System.out.println("Entered invalid input! Converting file without filtering");
		}
	}

	private void filterByID(List<String[]> linesUnited) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter your chosen ID : ");
			String userDeviceID = sc.nextLine();

			linesUnited.removeIf(line -> !(userDeviceID.equals(line[1])));
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
