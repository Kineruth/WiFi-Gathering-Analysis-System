**Date: 23/11/2017**

Object-Oriented-Task1
===

Project source can be downloaded from:
--- 
https://github.com/Kineruth/Task1.git  
Program is a project for Ariel University's Object Oriented course.

Authors:
--
Kineret Ruth Nahary  
Yakir Amar

**File list:**
--  

```  
.:  
KML API  
doc  
src  
Wigle Wifi App - Exports - Examples  
.classpath  
.gitignore  
.project  
README  
```

```  
.:
KML API - has the KML Jak API files.  
doc - has the JavaDoc files, specificaly the ones called *allclasses* .  
src - has all the java classes and Unit testing.  
Wigle Wifi App - Exports - Examples - has exports files from the Wigle app.  
```

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

Further look into the classes:
--
1. 	**Main** - The main class, where we call the `MergeCSVfiles()` & `ConvertCSVToKML()` to create CSV & KML files.
2.  **MergeCSVfiles** – This class gets a folder path, takes only CSV files and creates a new CSV file that contains all the top ten strongest (signal) Wi-Fi networks and their information for every timestamp from all those files, arranging them in an ascending order.
3.  **WiFiNetwork** – This class represents an object called WiFiNetwork, it contains the Wi-Fi network's Mac, SSID, frequency and signal. There's a function that converts a channel into frequency, because the app only gives us the channel and we wanted to know and save it as frequency. 
4.  **Sample** - This class represents a sample of Wi-Fi networks = a sample has all the networks that were scanned in the same time and location. It contains the device ID, longitude, latitude, altitude ,its timestamp - the scanning time and all the networks that were scanned in an arrayList.
5.  **SamplesList** - This class represents a list of all the Wi-Fi networks samples from the CSV files.
6.	 **ConvertCSVToKML** – This class gets an arranged CSV file (a file that was created with `mergeCSVfiles()`) and converts it into a KML format. It gives the user 3 filter options to chose from, to filter by time- specific date and hour, by a point on the map and a radius to get the networks that are within this radius, or by the device ID. 
7.	 **Filter** – This class gets the user's choice of filter and his/her input (using Scanner) and filters the unwanted networks.
8.	 **LocPoint** – This class creates a point(latitude, longitude). It has a function pointInCircle() that checks if a given point is within the radius from the current point.
9.  **TimeCorrector** - This class corrects the time format to (yyyy-mm-dd hh-mm-ss) for future use writing the KML file. Also contains a function `setTimeKMLFormat()` that converts the time to timeStamp as used in Google Earth timeline.

*How to run the program:*
--
Clone the repository to your computer, add all the java files (classes) to your eclipse, or some other tool used for java.

*How to create CSV & KML files:*
--  

In class Main:  
* *Creating CSV file:* create an object 'MergeCSVfiles' - give it a directory's path, and call for '.sortDirFiles()'. 
* *Creating Google Earth's KML file:* create an object 'ConvertCSVToKML' - give it a CSV file's path (file that was created by 'MergeCSVfiles'),
then call for '.createFile()'. 
 
```
public class Main {

	public static void main(String[] args) {  
  
		// put a specific path's directory to make CSV file  
		MergeCSVfiles t = new MergeCSVfiles("C:\\Users\\admin\\Desktop\\Task0");  
		t.sortDirFiles();  
		// put a specific path to CSV file to make KML file   
		ConvertCSVToKML k =	new ConvertCSVToKML("C:\\Users\\admin\\Desktop\\Task0 - 2017.11.23.15.29.28.csv");  
		k.createFile();  
```


