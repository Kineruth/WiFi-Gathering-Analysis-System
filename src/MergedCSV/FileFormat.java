package MergedCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * This class represents a checking tool to check whether the file is in a spesific format we want,
 * a Wigle CSF file or a merged CSV file.
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class FileFormat {

	/**
	 * Checks if a given file is in the right Wigle app file format.
	 * Checks if it is CSV type, if header has 8 variables - one of them is the app name,
	 * Checks if the second header has 11 variables - first one is MAC.
	 * @param f a given file.
	 * @return true if it has the right format, false if not.
	 * @throws FileNotFoundException
	 */
	public boolean checkWigleFormat(File f) throws FileNotFoundException{
		FileReader fr = new FileReader(f.getPath());
		BufferedReader br = new BufferedReader(fr);
		String str[];
		if (f.isFile() && f.getName().endsWith(".csv")){
			try {		
				String line = br.readLine();
				if(line!=null){
					str = line.split(",");
					//check header
					if(str.length!=8 || !str[0].contains("WigleWifi"))
						return false;
					else{
						line = br.readLine();
						if(line!=null){
						str = line.split(",");
						if(str.length!=11 || !str[10].equals("Type"))
							return false;
						}
						else return false;
					}
				}
				else return false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return false;
		return true;
	}
	
	/**
	 * This function checks if the file is a merged CSV file. 
	 * It checks if there are at least 10 values or at most 46 values in the file's line.
	 * @param f a given file
	 * @return true if the file has the right format, false if not.
	 * @throws IOException 
	 */
	public boolean checkMergedCSVFormat(File f) throws IOException{
		FileReader fr = new FileReader(f.getPath());
		BufferedReader br = new BufferedReader(fr);
		String str[];
		if (f.isFile() && f.getName().endsWith(".csv")){
			String line = br.readLine();
			if(line!=null){
				str = line.split(",");
				if(str.length==46 && str[1].equals("ID")){
					line = br.readLine();//skip header
					if(str.length<10 || str.length>46) return false;
					}
				if(str.length<10 || str.length>46) return false;
			}
			else return false;
		}
		else return false;
		return true;
	}
}
