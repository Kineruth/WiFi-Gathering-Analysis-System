package src;
/**
 * Date: 23-11-2017
 * This class converts a given time to match the KML format in google earth.
 * It also corrects the time pattern to match this pattern: (yyyy-mm-dd hh:mm:ss).
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class TimeCorrector {
/**
 * This function converts the given time format to match the KML TimeStamp format. 
 * @param time a given time.
 */
public String setTimeKMLFormat(String time){
	 return time=time.replaceAll(" ", "T");
}
/**
 * This function corrects a given time to be in this pattern:  (yyyy-mm-dd hh:mm:ss), if not it corrects it.
 * @param time a given time.
 */
public void correctAndSetTime(String time){
	if(time.contains("/"))
		time=time.replaceAll("/", "-");
	String [] timeStamp = time.split(" ");
	String [] date = timeStamp[0].split("-");
	if(date[0].length()==2){ //in dd-mm format
		if(date[2].length()==2) //in dd-mm-yy format
			time= 20+date[2]+"-"+date[1]+"-"+date[0]+" "+timeStamp[1]+":00";
		else //in dd-mm-yyyy format
			time= date[2]+"-"+date[1]+"-"+date[0]+" "+timeStamp[1]+":00";
	}
		
}

}
