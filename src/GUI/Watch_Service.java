package GUI;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;



public class Watch_Service {

	public static void main(String[] args) throws IOException{
		Watch_Service watchservice=FileSystems.getDefault().newWatchService();
		Path directory =Paths.get("C:\\alive\\");
		WatchKey watchkey =directory.register(watchservice,StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);
		while(true){
			for(WatchEvent<?> event :watchkey.pollEvents()){
				System.out.println(event.kind());
			}
		}


	}

}
