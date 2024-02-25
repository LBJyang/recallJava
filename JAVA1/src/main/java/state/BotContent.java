package state;

public class BotContent {
	private State s = new DisconnectedState();

	public String chat(String input) {
		if (input.equalsIgnoreCase("hello")) {
			s = new ConnectedState();
			return s.init();
		} else if (input.equalsIgnoreCase("bye")) {
			s = new DisconnectedState();
			return s.init();
		} else if (input.equalsIgnoreCase("busy")) {
			s = new BusyState();
			return s.init();
		}
		return s.reply(input);
	}
}
