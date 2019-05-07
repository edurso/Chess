package base;
import javax.swing.*;
import java.awt.event.*;
public class Menu {
	private static JFrame main = new JFrame("Chess");
	public static void start() {
		/*
		 * All the buttons on the main menu
		 */
		JButton singlePlay = new JButton();
		JButton multiPlay = new JButton();
		JButton setting = new JButton();
		JButton quit = new JButton();
		JPanel panel = new JPanel();
		/*
		 * Main menu configuration
		 */
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setSize(1000, 1002);
		main.setResizable(false);
		/*
		 * Quit button config
		 */
		quit.setText("Quit Game");
		quit.setBounds(400,500,95,30);
		panel.add(quit);
		panel.setLocation(500,500);
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		/*
		 * Menu being shown w/buttons being added
		 */
		main.add(panel);
		main.setVisible(true);
	}
}
