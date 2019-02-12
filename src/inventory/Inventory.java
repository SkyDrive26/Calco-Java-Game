package inventory;


import javax.swing.JFrame;

import Main.CalcoJavaGame;
import Main.Handler;

public class Inventory {
	
	public boolean isOpen = false;
	
	public static void openInventory(){
		JFrame Inventory = new JFrame("Inventory");
		Inventory.setSize(100,100);
		Inventory.setLocationRelativeTo(null);
		Inventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Inventory.setVisible(true);     
	}
	
	public static void closeInventory() {
		System.out.print("poepie");
	}
}

