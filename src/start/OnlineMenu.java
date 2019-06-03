package start;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class OnlineMenu extends JFrame{

    static OnlineMenu menu;

    JPanel main;

    JPanel join;

    JPanel create;

    JButton joinIt;

    JButton createNew;

    public OnlineMenu() {
    	this.setVisible(false);
    	main = new JPanel(new GridLayout(1, 2));
	    join = new JPanel(new FlowLayout());
	    create = new JPanel(new FlowLayout());
	    createNew = new JButton("Create Game");
	    joinIt = new JButton("Join Server");

    }

    public static void launch() {
        menu = new OnlineMenu();
        menu.setVisible(true);
    }

}
