package com.Table_Tracer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.Table_Tracer.connection.SourceDbConnection;
import com.Table_Tracer.connection.TargetDbConnection;
import com.Table_Tracer.models.Field;
import com.Table_Tracer.models.Table;
import com.Table_Tracer.utils.JournalMangager;
import com.Table_Tracer.utils.PropertiesReader;

public class Initialization {

	public static Table initTable(String tableName) throws ClassNotFoundException, SQLException, IOException {
		
		
		Statement statement = SourceDbConnection.getconnection().createStatement();
		ResultSet result ;
		String query = "DESC "+tableName;
		
		result = statement.executeQuery(query);
		Table table = new Table();
		table.setName(tableName);
		
		
		while (result.next()) {
			Field field = new Field(result.getNString(1),result.getNString(2),result.getNString(3),
			result.getNString(4),result.getNString(5),result.getNString(6));
			table.addField(field);
		}
		statement.close();
		try {
			Statement targetstatement = TargetDbConnection.getconnection().createStatement();
		
			targetstatement.executeUpdate(table.Sql_syntaxe());
			JournalMangager.Write(table.Sql_syntaxe());
			targetstatement.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return table;
	}
	
	
	public static boolean isEmptyTable(String table) throws ClassNotFoundException, SQLException, IOException {
		
		Statement targetstatement = TargetDbConnection.getconnection().createStatement();
		
		ResultSet result = targetstatement.executeQuery("SELECT * FROM "+table);

		boolean isEmpty = false;
		
		while(result.next()) {
			if(result.wasNull())
				isEmpty = true;
		}
		
		result.close();
		targetstatement.close();
		
		return isEmpty;
		
	}
	
	public static void CreateDbIfNotExist() throws IOException, ClassNotFoundException, SQLException {
		
		Properties prop = PropertiesReader.Reader("target.properties");

		
		
		try {
			Statement s = TargetDbConnection.getconnection().createStatement();
			s.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			Class.forName(prop.getProperty("driver"));

			Connection conn = DriverManager.getConnection("jdbc:mysql://" + prop.getProperty("host") + ":"+prop.getProperty("port"),prop.getProperty("user"),prop.getProperty("pass"));
			
			Statement s = conn.createStatement();
			s.executeUpdate("CREATE DATABASE "+prop.getProperty("Db"));
			s.close();
			conn.close();
			
		}
		
	}
}
