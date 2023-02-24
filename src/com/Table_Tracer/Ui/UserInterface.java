package com.Table_Tracer.Ui;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class UserInterface extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public void init() {
		Toolkit Kit = Toolkit.getDefaultToolkit();
		int width=Kit.getScreenSize().width/2 , height =Kit.getScreenSize().height/2;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Table Tracer");	
		this.setLayout(new GridLayout());
		this.setSize(width,height);
		this.setLocationRelativeTo(null);	
	}
	
	
}
