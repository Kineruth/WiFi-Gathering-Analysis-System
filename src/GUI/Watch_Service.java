package GUI;

import java.io.File;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import GUI_Filter.DataBase;
import GUI_Filter.Wraper;

/**
 * Credit: Yuval Mizrahi.
 * 
 * @author admin
 *
 */
public class Watch_Service {
	/**
	 * 
	 */
	private static void checkChangeInFiles() {
		int pathsAmount[] = { DataBase.getFilePaths().size() };
		ExecutorService service = Executors.newCachedThreadPool();
		List<Long> lastModified = new ArrayList<>();
		for (int i = 0; i < DataBase.getFilePaths().size(); i++) {
			lastModified.add(new File(DataBase.getFilePaths().get(i)).lastModified());
		}
		service.submit(new Runnable() {

			@Override
			public void run() {
				while (Thread.interrupted() == false) {
					if (pathsAmount[0] < DataBase.getFilePaths().size()) {
						//a file has been removed
						try {
							resetDataBase();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (pathsAmount[0] > DataBase.getFilePaths().size()) {
						//a file has been added
						for (int i = pathsAmount[0]; i < DataBase.getFilePaths().size(); i++) {
							lastModified.add(new File(DataBase.getFilePaths().get(i)).lastModified());
						}
						pathsAmount[0] = DataBase.getFilePaths().size();
					}
					for (int i = 0; i < lastModified.size(); i++) {
						if (lastModified.get(i) != new File(DataBase.getFilePaths().get(i)).lastModified()) {
							//a file has been modified
							try {
								resetDataBase();
							} catch (IOException e) {
								e.printStackTrace();
							}
							lastModified.set(i, new File(DataBase.getFilePaths().get(i)).lastModified());
						}
					}

				}
			}
		});
	}

	/**
	 * A shell function for listen for files
	 * 
	 * @throws InterruptedException
	 */
	public static void fileslisten() throws InterruptedException {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				checkChangeInFiles();
			}
		});
		t.start();

	}

	/**
	 * A function that check if the folder source database has changed
	 * 
	 * @param database
	 * @param temp
	 * @param paths
	 * @param file
	 * @throws Exception
	 */
	private static void checkChangeInFolder() throws Exception {
		int amountofpaths[] = { DataBase.getFolderPaths().size() };
		ExecutorService service = Executors.newCachedThreadPool();
		final java.nio.file.FileSystem fs = FileSystems.getDefault();
		final java.nio.file.WatchService ws = fs.newWatchService();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		final Map<WatchKey, Path> keys = new ConcurrentHashMap();
		for (int i = 0; i < DataBase.getFolderPaths().size(); i++)
			reg(fs.getPath(DataBase.getFolderPaths().get(i)), keys, ws);
		service.submit(new Runnable() {
			@Override
			public void run() {
				while (Thread.interrupted() == false) {
					WatchKey key;
					try {
						key = ws.poll(10, TimeUnit.MILLISECONDS);
					} catch (InterruptedException | ClosedWatchServiceException e) {
						break;
					}
					if (key != null) {
						try {
							resetDataBase();
						} catch (IOException e) {
							e.printStackTrace();
						}
						key.reset();
					} else if (amountofpaths[0] != DataBase.getFolderPaths().size()) {
						for (int i = amountofpaths[0]; i < DataBase.getFolderPaths().size(); i++) {
							try {
								reg(fs.getPath(DataBase.getFolderPaths().get(i)), keys, ws);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						amountofpaths[0] = DataBase.getFolderPaths().size();
					}
				}
			}
		});
	}

	private static void reg(Path dir, Map<WatchKey, Path> keys, java.nio.file.WatchService ws) throws IOException {
		WatchKey key = dir.register(ws, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);
		keys.put(key, dir);
	}

	/**
	 * A sheel function for listen to folder
	 * 
	 * @param database
	 * @param temp
	 * @param folder
	 * @param file
	 */
	public static void folderlisten() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					checkChangeInFolder();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		t.start();
	}

	/**
	 * Resets the database if have change in the file or the folder source
	 * database
	 * 
	 * @throws IOException
	 */
	private static void resetDataBase() throws IOException {
		synchronized (DataBase.dataBase) {
			DataBase.dataBase.clear();
			DataBase.copyDataBase.clear();
			for (int i = 0; i < DataBase.getFolderPaths().size(); i++) {
				Wraper.folderAdded(DataBase.getFolderPaths().get(i));
			}
			for (int i = 0; i < DataBase.getFilePaths().size(); i++) {
				Wraper.mergedFileAdded(DataBase.getFilePaths().get(i));
			}
		}
	}

}
