package start;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import java.net.*;
import java.io.*;

import chess.Game;

public class OnlineMenu extends JFrame{
	
	protected final static int PORT = 6666;
	
    static OnlineMenu menu;

    JPanel main;

    JPanel join;

    JPanel create;

    JButton joinIt;

    JButton createNew;

    JButton cancel;

    private static JTextField joinAddress;

    public static JTextField getJoinAddress() { return joinAddress; }

	public static void setJoinAddress(JTextField joinAddress) { OnlineMenu.joinAddress = joinAddress; }

	public OnlineMenu() {
        this.setVisible(false);
        this.setTitle("Chess Server Launch");
        main = new JPanel(new GridLayout(2, 1));
        main.setBackground(Color.BLACK);
        this.setBounds(100, 100, 500, 250);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        join = new JPanel(new FlowLayout());
        join.setBackground(Color.BLACK);
        create = new JPanel(new FlowLayout());
        create.setBackground(Color.BLACK);
        createNew = new JButton("Host Game");
        createNew.setBackground(Color.RED);
        createNew.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                /*
                Stuff to start a new game somehow
                */ 
            	dispose();
        		Game.setErrorText("Loading Server  . . .  Hit Restart to Restart Application");
        		Game.revealErrorWindow();
        		Host.startServer();
        }});
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.RED);
        cancel.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                Game.menu.start();
                dispose(); 
            } 
        });
        joinIt = new JButton("Join Server");
        joinIt.setBackground(Color.RED);
        joinAddress = new JTextField();
        joinAddress.setText("Enter Address");
        joinAddress.setBackground(Color.BLACK);
        joinAddress.setForeground(Color.RED);
        joinIt.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) {
        		/*
        		Joins server  
        		*/
        		dispose();
        		Client.var();
        		
        	}
        });
        join.add(joinAddress);
        join.add(joinIt);
        create.add(createNew);
        create.add(cancel);
        main.add(join);
        main.add(create);
        this.add(main);
    }

    public static void launch() {
        menu = new OnlineMenu();
        menu.setVisible(true);
    }

}
