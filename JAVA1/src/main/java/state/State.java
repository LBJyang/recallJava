package state;

public interface State {
	String init();

	String reply(String input);
}
