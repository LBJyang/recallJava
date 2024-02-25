package state;

public class BusyState implements State {

	@Override
	public String init() {
		// TODO Auto-generated method stub
		return "I'm Busy!";
	}

	@Override
	public String reply(String input) {
		// TODO Auto-generated method stub
		return "I am busy!" + input;
	}

}
