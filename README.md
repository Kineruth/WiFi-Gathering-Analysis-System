#Object-Oriented-Task1
##Project source can be downloaded from: 
https://github.com/Kineruth/Task1.git
Program is a project for Ariel University's Object Oriented course.
###Authors:
Kineret Ruth Nahary
Yakir Amar

##File list:
'''
.:
KML API
doc
src
Wigle Wifi App - Exports - Examples
.classpath
.gitignore
.project
README
'''

##About the project:
The program merges CSV files from exported android app called "WiGLE WiFi Wardriving" into one file – taking Wi-Fi networks only and arranging them by time and place. 
For every timestamp it takes the top 10 networks with the strongest signals and arranges them in an ascending order.
'''
"WiGLE WiFi Wardriving"  app – it is an open source network observation, positioning and display client from the world's largest queryable database of wireless networks. 
This app can be used for site survey, security analysis and competition with friends. 
It collects networks for personal research (information was taken from the android app store).
https://play.google.com/store/apps/details?id=net.wigle.wigleandroid&hl=en
'''

The program also takes an arranged file and converts it into a KML file that can be used on the "Google Earth" site.
By uploading  the file to "Google Earth" site, we can look up all the Wi-Fi networks we wanted to see on the map. 
We can choose in the program either to filter the network's list by a specific date and hour, by a specific ID (=device), or by choosing a point and a radius to show all the networks inside this specific radius.
'''
"Google Earth" – is a geobrowser that accesses satellite, aerial imagery and other geographic data over the internet to represent the Earth as a three dimensional globe. 
This product has many features one of them is the ability to show mappable data by reading KML files that had been uploaded to it.
https://serc.carleton.edu/sp/library/google_earth/what.html
'''
---
##Further look into the classes:
1.	'*mergeCSVfiles*' – This class gets a folder path, takes only CSV files and creates a new CSV file that contains all the top ten strongest (signal) Wi-Fi networks and their information for every timestamp from all those files, arranging them in an ascending order.
2.	'*WiFiNetwork*' – This class represent an object called WiFiNetwork, it contains all the information of a specific Wi-Fi network that had been scanned by the app and added into the file. The information has the device ID, the network's name, its MAC, signal, frequency, longitude, latitude, altitude and its timestamp- the time it had been scanned. The class has a function that converts a channel into frequency, because the app only gives us the channel and we wanted to know and save it as frequency for later use with "Google Earth". 
3.	'*convertToKML*' – This class gets an arranged CSV file (a file that was created with mergeCSVfiles) and converts it into a KML format. It gives the user 3 filter options of what he wants the file to have, to filter by time- specific date and hour, filter by a point on the map and a radius to get the networks that are within this radius, or to filter by ID – the name of the device. There is a function called getColor() that gives every pin a color that represent its top network (with the strongest signal between the networks from the same timestamp). A green color is given when the signal is higher/equal to -70, a yellow when it's between -70 and -90, and a red when it is lower than -90.
4.	'*Filter*' – This class gets the uses choice of filter and his/her input (using Scanner).
5.	'*LocPoint*' – This class creates a point(latitude, longitude). It has a function pointInCircle() that checks if a given point is within the radius from the current point.
6.	'*Main*' - The main class.


##How to run the program:
Clone the directory to your local machine.

##How to create CSV & KML files:
In the Main class:
*Creating CSV file: create an object 'MergeCSVfiles' - give it a directory's path, and call for '.sortDirFiles()'.
*Creating Google Earth's KML file: create an object 'ConvertCSVToKML' - give it a CSV file's path (file that was created by 'MergeCSVfiles'),
then call for '.createFile()'.
