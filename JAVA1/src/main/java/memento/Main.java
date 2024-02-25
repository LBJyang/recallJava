package memento;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextEditor editor = new TextEditor();
		editor.add("Hello");
		editor.add(',');
		editor.delete();
		editor.add(' ');
		editor.add("world");
		// 保存状态:
		String state = editor.getState();
		// 继续编辑:
		editor.add("!!!");
		editor.delete();
		editor.print();
		// 恢复状态:
		editor.setState(state);
		editor.print();
	}

}
