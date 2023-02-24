package com.Table_Tracer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;

import com.Table_Tracer.connection.SourceDbConnection;
import com.Table_Tracer.connection.TargetDbConnection;
import com.Table_Tracer.models.Enregistrement;
import com.Table_Tracer.models.Table;
import com.Table_Tracer.utils.JournalMangager;

public class Copy {


	public static void CopyTable(Table table) throws ClassNotFoundException, SQLException, IOException {
		
		Statement statement = SourceDbConnection.getconnection().createStatement();
		ResultSet result ;
		result = statement.executeQuery("SELECT * FROM "+table.getName());
				
		while(result.next()) {
			
			
			copyLine(result, table);
			
		}
		result.close();		
		statement.close();
	}
	
	
	public static void copyLine(ResultSet result , Table Sourcetable) throws SQLException, ClassNotFoundException, IOException {
		
		System.out.println("YOOOOO");

			Enregistrement element = new Enregistrement();
			
			element.setTable(Sourcetable);

			
			for (int i=1;i<=Sourcetable.getFields().size();i++) {
				
				try {
					element.addValue(result.getObject(i).toString());
				} catch (NullPointerException e) {
					// TODO: handle exception
					element.addValue("NULL");
				}
				
			}
				PreparedStatement Targetstatement = TargetDbConnection.getconnection().prepareStatement(element.insertSyntaxe());

				int n=1;
			for (int i=1; i<=element.getfieldsValues().size();i++) {
				
				if(!element.getfieldsValues().get(i-1).equals("NULL")) {
					Targetstatement.setObject(n,result.getObject(i));
					n++;
				}	
					
			}
			Targetstatement.executeUpdate();
			JournalMangager.Write(element.insertSyntaxe());
			Targetstatement.close();
	}
	
	
}
