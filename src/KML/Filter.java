package KML;

import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import MergedCSV.Sample;
/**
 * Date: 23-11-2017
 * This class represents a filter tool. It gets the user choice to filter the networks or not.
 * If the user choose to filter, then he has 3 options to choose from: time, a certain radius from location and the ID of a device.
 * It filters the networks by the user choice.
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class Filter {
	private int choice;
/**
 * This function gets the user choice of filtering and filters the list of lines, every line is a sample.
 * @param linesUnited a given list of lines from the CSV file. 
 * @exception Exception e if the program fails running..
 */
	public void filterFile(List<Sample> linesUnited) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Do you want to filter your file information?\n1. Yes\n2. No");
			this.choice = sc.nextInt();

			while (this.choice != 1 && this.choice != 2) { // if invalid input
				System.out.println("Not in the options! Enter again");
				this.choice = sc.nextInt();
			}

			if (this.choice == 1) {

				System.out.println("Filter by :\n1. Time 2. Location 3. ID ");
				this.choice = sc.nextInt();

				while (this.choice < 1 && this.choice > 3) { // if invalid input
					System.out.println("Not in the options! Enter again");
					this.choice = sc.nextInt();
				}
//				filterLines(linesUnited);
			}
		} catch (Exception e) {
			System.out.println("Entered invalid input! Converting file without filtering");
		}

	}

/**
 * This function filter the lines by the user choice of time.
 * Filters using Predicate - checks if the user's date and hour is as in the line, if not - removes the line.
 * Predicate removeIf: https://www.concretepage.com/java/jdk-8/java-8-list-example-with-foreach-removeif-replaceall-and-sort
 * @param linesUnited a given list of lines from the CSV file. 
 */
	public void filterByTime(List<Sample> samples,int choice, int maxY, int maxM, int maxD, int minY, int minM, int minD, int maxH, int maxMin, int minH, int minMin) {		
			if(choice ==1){ //samples within this time
		Predicate<Sample> samplePredicate = s -> !(Integer.parseInt(s.getTime().split(" ")[0].split("-")[0])<=maxY && Integer.parseInt(s.getTime().split(" ")[0].split("-")[0])>=minY
					&& Integer.parseInt(s.getTime().split(" ")[0].split("-")[1])<=maxM && Integer.parseInt(s.getTime().split(" ")[0].split("-")[1])>=minM
					&& Integer.parseInt(s.getTime().split(" ")[0].split("-")[2])<=maxD && Integer.parseInt(s.getTime().split(" ")[0].split("-")[2])>=minD
					&& Integer.parseInt(s.getTime().split(" ")[1].split(":")[0])<=maxH &&Integer.parseInt(s.getTime().split(" ")[1].split(":")[0])>= minH
					&& Integer.parseInt(s.getTime().split(" ")[1].split(":")[1]) <= maxMin && Integer.parseInt(s.getTime().split(" ")[1].split(":")[1])>= minMin);
			samples.removeIf(samplePredicate);
			}
			else{ //samples outside this time
				Predicate<Sample> samplePredicate = s -> (Integer.parseInt(s.getTime().split(" ")[0].split("-")[0])<=maxY && Integer.parseInt(s.getTime().split(" ")[0].split("-")[0])>=minY
						&& Integer.parseInt(s.getTime().split(" ")[0].split("-")[1])<=maxM && Integer.parseInt(s.getTime().split(" ")[0].split("-")[1])>=minM
						&& Integer.parseInt(s.getTime().split(" ")[0].split("-")[2])<=maxD && Integer.parseInt(s.getTime().split(" ")[0].split("-")[2])>=minD
						&& Integer.parseInt(s.getTime().split(" ")[1].split(":")[0])<=maxH &&Integer.parseInt(s.getTime().split(" ")[1].split(":")[0])>= minH
						&& Integer.parseInt(s.getTime().split(" ")[1].split(":")[1]) <= maxMin && Integer.parseInt(s.getTime().split(" ")[1].split(":")[1])>= minMin);
				samples.removeIf(samplePredicate);
			}
	}
/**
  * This function filters the lines by the user choice of location and radius.
 * Filters using Predicate - checks if  the line's point is within the user's point's radius , if not - removes the line.
 * Predicate removeIf: https://www.concretepage.com/java/jdk-8/java-8-list-example-with-foreach-removeif-replaceall-and-sort
 * @param linesUnited a given list of lines from the CSV file. 
 * @exception Exception e if the user entered invalid input.
 */
	public void filterByPlace(List<Sample> samples, double maxLAT,double maxLON, double maxALT, double minLAT, double minLON, double minALT) {
//			Coordinate p1 = new Coordinate(userLat, userLon,0+""); 
//			Predicate<Sample> samplePredicate = s-> !(p1.pointInCircle(new Coordinate(s.getLAT(), s.getLON(),s.getALT()),Double.parseDouble(userRadius)));
	// do we need a radius?! 
		Predicate<Sample> samplePredicate = s-> !(Double.parseDouble(s.getLAT())<=maxLAT && Double.parseDouble(s.getLAT())>=minLAT
				&&Double.parseDouble(s.getLON())<= maxLON && Double.parseDouble(s.getLON())>= minLON
				&&Double.parseDouble(s.getALT())<= maxALT && Double.parseDouble(s.getALT())>= minALT);
			samples.removeIf(samplePredicate);
	}
/**
 * This function filters the lines by the user choice of device ID.
 * Filters using Predicate - checks if  the user's device ID is as stated in the line , if not - removes the line.
 * Predicate removeIf: https://www.concretepage.com/java/jdk-8/java-8-list-example-with-foreach-removeif-replaceall-and-sort
 * @param linesUnited a given list of lines from the CSV file. 
 * @exception Exception e if the user entered invalid input.
 */
	public void filterByID(List<Sample> samples, int num, String device) {
			//1 for only specific given device
			if(num==1){
				Predicate<Sample> samplePredicate = s-> !(s.getID().equals(device));
			samples.removeIf(samplePredicate);
			}
			else{ //2 for all excluding specific given device
				Predicate<Sample> samplePredicate = s-> (s.getID().equals(device));
				samples.removeIf(samplePredicate);
			}			
	}
///**
// *This function checks the user'd choice of filtering and sends to the wanted filter.
// * @param linesUnited a given list of lines from the CSV file. 
// */
//	private void filterLines(List<Sample> linesUnited) {
//		if (this.choice == 1)
//			filterByTime(linesUnited);
//		else if (this.choice == 2)
//			filterByPlace(linesUnited);
//		else
//			filterByID(linesUnited);
//
//	}

}
