package HongZe.review.IO;

import java.io.File;
import java.io.FilenameFilter;

public class traverseFiles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("C:\\Users\\Yang\\Downloads");
		File[] files = f.listFiles();
		printFiles(files);
		File[] files2 = f.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(".exe");
			}
		});
		printFiles(files2);
	}

	private static void printFiles(File[] files) {
		// TODO Auto-generated method stub
		System.out.println("==========================");
		if (files != null) {
			for (File file : files) {
				System.out.println(file.getName() + "direction:" + file);
			}
		}
	}

}
