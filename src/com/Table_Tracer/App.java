package com.Table_Tracer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;

import com.Table_Tracer.Ui.Panel;
import com.Table_Tracer.Ui.UserInterface;
import com.Table_Tracer.models.Table;
import com.Table_Tracer.utils.PropertiesReader;


public class App {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		UserInterface ui = new UserInterface();
		Panel pan = new Panel();
		pan.init();
		ui.init();
		ui.add(pan);
		ui.setVisible(true);
		
		LocalTime hour = LocalTime.parse(LocalTime.now().toString());

		Initialization.CreateDbIfNotExist();
		
		while(true) {
		if(LocalTime.now().isAfter(hour)) {
			
			System.out.println(hour);
			System.out.println("it's time");
		
			Properties prop = PropertiesReader.Reader("ressource.properties");
		
			ArrayList<String> tables = new ArrayList<String>();
		
			for(int i=1;i<prop.size();i++) {
			
				tables.add(prop.getProperty("tab"+i));
			}
		
		
			for (String tab : tables) {
		
				Table table = Initialization.initTable(tab);
		
			if(Initialization.isEmptyTable(tab))
				Copy.CopyTable(table);
			else 
				Reviser.correctTable(table, table);
		
			}
			
			hour = hour.plusHours(Long.parseLong(prop.getProperty("Cycle")));
			
		}		
	}
		
	}

}
