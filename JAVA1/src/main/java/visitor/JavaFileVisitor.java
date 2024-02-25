package visitor;

import java.io.File;

public class JavaFileVisitor implements Visitor {

	@Override
	public void visitDir(File dir) {
		// TODO Auto-generated method stub
		System.out.println("Visit Dir" + dir);
	}

	@Override
	public void visitFile(File file) {
		// TODO Auto-generated method stub
		if (file.getName().endsWith(".java")) {
			System.out.println("Found Java File" + file);
		}
	}

}
