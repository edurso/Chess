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

import chess.Board;
import chess.Game;

//TODO - Javadoc
public class OnlineMenu extends JFrame {

    protected final static int PORT = 6666;

    static OnlineMenu menu;

    JFrame other;

    JPanel loading;

    JPanel success;

    JPanel fail;

    JPanel main;

    JPanel join;

    JPanel create;

    JButton joinIt;

    JButton createNew;

    JButton cancel;

    static JTextField joinAddress;

    public static boolean isHost = false;

    public OnlineMenu() {
        this.setVisible(false);
        this.setTitle("Chess Server Launch");
        main = new JPanel(new GridLayout(2, 1));
        main.setBackground(Color.BLACK);
        this.setBounds(100, 100, 500, 250);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initPanels();
        join = new JPanel(new FlowLayout());
        join.setBackground(Color.BLACK);
        create = new JPanel(new FlowLayout());
        create.setBackground(Color.BLACK);
        createNew = new JButton("Host Game");
        createNew.setBackground(Color.RED);
        createNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*
                 * Stuff to start a new game somehow
                 */
                isHost = true;
                changeFrame();
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
                isHost = false;
                dispose();
        		Client.joinServer();
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

    private void changeFrame() {
        dispose();
        other = new JFrame();
        other.setTitle("Chess");
        other.setBounds(100, 100, 200, 200);
        JPanel p = new JPanel(new FlowLayout());
        p.setBackground(Color.BLACK);
        JButton b = new JButton("Cancel");
        b.setBackground(Color.RED);
        b.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                Game.menu.start();
                dispose(); 
            } 
        });
        JTextField t = new JTextField(" . . . Loading . . . ");
        t.setEditable(false);
        t.setBackground(Color.BLACK);
        t.setForeground(Color.RED);
        p.add(t);
        p.add(b);
        other.add(p);
        other.setVisible(true);
    }

    private void initPanels() {
        loading = new JPanel(new FlowLayout());
        loading.setBackground(Color.BLACK);
        JButton c = new JButton("Cancel");
        c.setBackground(Color.RED);
        c.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                Game.menu.start();
                dispose(); 
            } 
        });
        loading.add(c);
        success = new JPanel();
        success.setBackground(Color.BLACK);
        JButton l = new JButton("Start Game");
        l.setBackground(Color.RED);
        l.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                //Game.board = new Board();//loads from file
                Game.board.play();
                dispose(); 
            } 
        });
        success.add(l);
        success.add(c);
        fail = new JPanel();
        fail.setBackground(Color.BLACK);
        JButton r = new JButton("Restart");
        r.setBackground(Color.RED);
        r.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                Game.menu.start();
                dispose(); 
            } 
        });
        fail.add(r);
    }

}
