package HongZe.review.IO;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathReview {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path p1 = Paths.get(".", "project", "study");
		System.out.println(p1);
		System.out.println(p1.getFileName());
		Path p2 = p1.toAbsolutePath();
		System.out.println(p2);
		Path p3 = p1.normalize();
		System.out.println(p3);
		File f = p1.toFile();
		System.out.println(f);
		Path p4 = Paths.get("..").toAbsolutePath();
		System.out.println(p4);
		for (Path path : p4) {
			System.out.println("   " + path);
		}
	}

}
