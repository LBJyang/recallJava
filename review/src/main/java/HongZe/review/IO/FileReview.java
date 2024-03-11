package HongZe.review.IO;

import java.io.File;
import java.io.IOException;

public class FileReview {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("C:\\Users\\Yang\\Desktop\\新建 文本文档.txt");
		System.out.println(f);
		System.out.println(f.length());
		System.out.println(f.isFile());
		System.out.println(f.isDirectory());
		System.out.println("canExecute:" + f.canExecute());
		System.out.println("canRead:" + f.canRead());
		System.out.println(File.separator);
//		pathTest();
//		createFile();
		createTempFile();
	}

	private static void pathTest() {
		File f = new File(".");
		System.out.println(f.getPath());
		System.out.println(f.getAbsolutePath());
		try {
			System.out.println(f.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void createFile() {
		File f = new File("C:\\Users\\Yang\\Desktop\\createFile.txt");
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(f.isFile());
		System.out.println(f.getName());
		System.out.println(f.getAbsolutePath());
	}

	private static void createTempFile() {
		File f = null;
		try {
			f = File.createTempFile("temp-", ".txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f.deleteOnExit();
		System.out.println(f.getName());
		System.out.println(f.isFile());
		try {
			System.out.println(f.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
