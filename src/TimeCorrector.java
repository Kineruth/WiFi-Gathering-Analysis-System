package src;

public class TimeCorrector {

public TimeCorrector(){
	
}
public void setTimeKMLFormat(String time){
	 time.replaceAll(" ", "T");
}

public void correctAndSetTime(String time){
	if(time.contains("/"))
		time.replaceAll("/", "-");
	String [] timeStamp = time.split(" ");
	String [] date = timeStamp[0].split("-");
	if(date[0].length()==2)
		time= date[2]+"-"+date[1]+"-"+date[0]+" "+timeStamp[1]+":00";
	
}

}
