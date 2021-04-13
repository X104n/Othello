package inf101;

import inf101.sem2.GUI.MainMenu;

public class Main {

	public static void main(String[] args) {
		MainMenu menu = new MainMenu();
		while(true) {
			if(menu.game == null) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				try {
					menu.game.run();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
