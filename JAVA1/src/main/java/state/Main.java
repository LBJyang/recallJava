package state;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		BotContent bot = new BotContent();
		for (;;) {
			System.out.print("> ");
			String input = scanner.nextLine();
			String output = bot.chat(input);
			System.out.println(output.isEmpty() ? "no reply" : "< " + output);
		}
	}

}
