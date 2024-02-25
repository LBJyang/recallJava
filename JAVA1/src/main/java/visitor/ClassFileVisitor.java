package visitor;

import java.io.File;

public class ClassFileVisitor implements Visitor {

	@Override
	public void visitDir(File dir) {
		// TODO Auto-generated method stub
		System.out.println("Visit Dir" + dir);
	}

	@Override
	public void visitFile(File file) {
		// TODO Auto-generated method stub
		if (file.getName().endsWith(".class")) {
			System.out.println("Found Class File" + file);
		}
	}

}
