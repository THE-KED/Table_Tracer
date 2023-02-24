package com.Table_Tracer.Ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	public void init() {
		
		this.setLayout(getLayout());
		this.setLayout(new GridLayout());
		this.setSize(100,200);
		JLabel label = new JLabel("<html>First line<br>Second line</html>");
		this.add(label);
		this.setBackground(new Color(0, 0, 0));
		this.setVisible(true);

		
	}
	
}
