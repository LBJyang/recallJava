package visitor;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileStructure f = new FileStructure(new File("."));
		Visitor v = new ClassFileVisitor();
		f.handle(v);

	}

}
