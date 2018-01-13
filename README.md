**Date: 23/11/2017**  
**Edited: 20/12/2017**

Object Oriented
===

Project source can be downloaded from:
--- 
https://github.com/Kineruth/Task1.git  
Program is a project for Ariel University's Object Oriented course.

Authors:
--
Kineret Ruth Nahary  
Yakir Amar


**Intoduction**
==

About the project:
--
The program merges CSV files from exported android app called "WiGLE WiFi Wardriving" into one file – taking Wi-Fi networks only and arranging them by time and place. 
For every timestamp it takes the top 10 networks with the strongest signals and arranges them in an ascending order. 

```
"WiGLE WiFi Wardriving" app – it is an open source network observation, positioning and display client from the world's largest queryable database of wireless networks. 
This app can be used for site survey, security analysis and competition with friends. 
It collects networks for personal research (information was taken from the android app store).
https://play.google.com/store/apps/details?id=net.wigle.wigleandroid&hl=en
```

The program also takes an arranged file and creates a KML file using Jak API so that it can be used on the "Google Earth" site.
By uploading  the file to "Google Earth" site, we can look up all the Wi-Fi networks we wanted to see on the map with a timeline option. 
We can choose in the program either to filter the networks' samples list by a specific date and hour, by a specific device ID, or by choosing a point and a radius to show all the networks within this specific radius.

```
"Google Earth" – is a geobrowser that accesses satellite, aerial imagery and other geographic data over the internet to represent the Earth as a three dimensional globe. 
This product has many features one of them is the ability to show mappable data by reading KML files that had been uploaded to it.
https://serc.carleton.edu/sp/library/google_earth/what.html
```
The program gives two another options:
Create a new CSV file with all the strongest locations for every Mac from a merged CSV file.
Create a new CSV file with all the user locations for every sample's scan using a merged CSV file as database and other merged CSV file with no coordinates specified in it. The samples' scans are taken from the second file.

**File list:**
--  

```  
.: 
KML API  
doc 
docs
img  
src  
uml  
Wigle Wifi App - Exports - Examples  
.classpath  
.gitignore  
.project  
README  
Task 0 - Document
```

```  
.:
KML API - has the KML Jak API files.  
doc - has the JavaDoc files, specificaly the ones called *allclasses* .
docs - has all the files wanted for Task 3 of the project.
src - has all the java classes and Unit testing.  
Wigle Wifi App - Exports - Examples - has exports files from the Wigle app.  
uml - has a class diagram of the whole program.  
img - has images used as icons in the GUI part of the program.  
```

Further look into the classes:
--
1. **Main** - The main class, where we call the `MergeCSVfiles()` & `ConvertCSVToKML()` to create CSV & KML files or 'ArrangeCSV()' to create CSV files of the two algorithms options.
2. **MergeCSVfiles** – This class gets a folder path, takes only CSV files and creates a new CSV file that contains all the top ten strongest (signal) Wi-Fi networks and their information for every timestamp from all those files, arranging them in an ascending order.
3. **WiFiNetwork** – This class represents an object called WiFiNetwork, it contains the Wi-Fi network's Mac, SSID, frequency and signal. There's a function that converts a channel into frequency, because the app only gives us the channel and we wanted to know and save it as frequency. 
4. **Sample** - This class represents a sample of Wi-Fi networks = a sample has all the networks that were scanned in the same time and location. It contains the device ID, longitude, latitude, altitude ,its timestamp - the scanning time and all the networks that were scanned in an arrayList.
5. **PI_Comparator** - This class represents a comparator that sorts Samples by thie PI values.
6. **ConvertCSVToKML** – This class gets an arranged CSV file (a file that was created with `mergeCSVfiles()`) and converts it into a KML format. It gives the user 3 filter options to chose from, to filter by time- specific date and hour, by a point on the map and a radius to get the networks that are within this radius, or by the device ID. 
7. **Filter** – This class gets the user's choice of filter and his/her input (using Scanner) and filters the unwanted networks.
8. **Coordinate** – This class creates a point(latitude, longitude, altitude). It has a function pointInCircle() that checks if a given point is within the radius from the current point.
9. **TimeCorrector** - This class corrects the time format to (yyyy-mm-dd hh-mm-ss) for future use writing the KML file. Also contains a function `setTimeKMLFormat()` that converts the time to timeStamp as used in Google Earth timeline.
10. **ArrangeCSV** - This class creates a CSV file with all the strongest location for every Mac, or a CSV file with the user locations for every Sample's scan.
11. **LinesToSamples** - This class gets a file path, reads from the file (CSV format) and converts all the lines into Samples.
12. **Algorithms** - This class has the two algorithms for Task 3 of the project. Algo 1 - mac strongest location. Algo 2 - user location.
13. **Calculate** - This class has all the calculations needed for class 'Algorithms'.  
14. **TimeFilter** - This class filters the database by a given max-min date & time.    
15. **LocationFilter** - This class filters the database by a given max-min cooardinates' values.  
16. **DeviceFilter** - This class filters the database by a given device type.  
17.**DataBase** - This class represents a database = list of samples, and some methods to it.  
18. **Wraper** - This class acts as a bridge from the GUI to the others classes for performing some methods that exist in them like reading from a given file or creating a new one.  
19. *There are more classes in the GUI directory that are visulaize the whole GUI process.*  

*How to run the program:*
--
Clone the repository to your computer, add all the java files (classes) to your eclipse, or some other tool used for java. 
You need to add the KML API library files from its folder to your project or the program won't run at all.  
Now after adding the GUI option, you can run it as the main one - run the class **MainFrame** to run the program.  

*How to create CSV & KML files:*
--  

In class Main:  
* *Creating CSV file:* create an object 'MergeCSVfiles' - Call function sortDirFiles in class MergeCSVfiles and put in its constructor a specific path to a directory with those CSV files.
* *Creating Google Earth's KML file:* - Call function createFile in class ConvertCSVToKML and put in its constructor a specific path to the merged CSV file.
* *Create CSV file with Macs strongest location* - give a merged CSV file's path and a number of samples to be taken for the calculation. Call function macStrongestLocation in class ArrangeCSV. 
* *Create CSV file with user locations* - give a merged CSV file's path, a merged CSV file's path without coordinates and a number of samples to be taken for the calculation. Call function userLocation in class ArrangeCSV.
 
```
public static void main(String[] args) {

		/* ONE: */
		
	        String diractory_Path = "PUT_DIRACTORY_PATH_HERE";
		MergeCSVfiles t = new MergeCSVfiles(diractory_Path);
		t.sortDirFiles();

		
		
		/* TWO: */
	
		String csv_Path = "PUT_CSV_FILE_PATH_HERE";
		ConvertCSVToKML k = new ConvertCSVToKML(csv_Path);
		k.createFile();

		
		
		/* THREE & FOUR: */
		
		ArrangeCSV a = new ArrangeCSV();
		String filePath ="PUT_MERGED_CSV_FILE_PATH_HERE";
		String fileOutput="PUT_MERGED_CSV_FILE_WITH_NO_COORDINATES_PATH_HERE";
		int number =4;
		
		a.macStrongestLocation(filePath, number); //algo 1
		a.userLocation(filePath, fileOutput, number); //algo 2
	}
  
```


