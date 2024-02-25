package state;

public class ConnectedState implements State {

	@Override
	public String init() {
		// TODO Auto-generated method stub
		return "Hello!I'm Bob!";
	}

	@Override
	public String reply(String input) {
		// TODO Auto-generated method stub
		if (input.endsWith("?")) {
			return "Yes!" + input.substring(0, input.length() - 1) + "!";
		}
		if (input.endsWith(".")) {
			return input.substring(0, input.length() - 1) + "!";
		}
		return input.substring(0, input.length() - 1) + "?";
	}

}
