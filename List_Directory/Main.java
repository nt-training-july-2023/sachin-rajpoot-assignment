package List_Directory;

import java.io.File;

class Main {
	public static void main(String[] args) {
		String path = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\List_Directory";

		File directory = new File(path);

		if (directory.exists() && directory.isDirectory()) {
			listFilesAndDirectories(directory);
		} else {
			System.out.println("The path is not valid.");
		}
	}

	public static void listFilesAndDirectories(File directory) {
		File[] filesAndDirs = directory.listFiles();

		if (filesAndDirs != null) {
			for (File file : filesAndDirs) {
				if (file.isDirectory()) {
					System.out.println("Directory: " + file.getName());
					listFilesAndDirectories(file);
				} else {
					System.out.println("File: " + file.getName());
				}
			}
		}
	}
}
