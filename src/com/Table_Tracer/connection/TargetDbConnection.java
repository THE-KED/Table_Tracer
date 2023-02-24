package com.Table_Tracer.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.Table_Tracer.utils.PropertiesReader;

public class TargetDbConnection {

	
	private static String host;
	private static String port;
	private static String user;
	private static String pass;
	private static String Db;
	private static String driver;
	
	private static Connection connection;

	private TargetDbConnection() {
		
	}

	public static Connection getconnection() throws IOException, ClassNotFoundException, SQLException {
		
		if(connection==null) {
			
			Properties prop = PropertiesReader.Reader("target.properties");
			
			driver=prop.getProperty("driver");
			host=prop.getProperty("host");
			user=prop.getProperty("user");
			pass=prop.getProperty("pass");
			port=prop.getProperty("port");
			Db=prop.getProperty("Db");;
			
			Class.forName(driver);
			
			String connectionURL = "jdbc:mysql://" + host + ":"+port+"/" + Db;
			
			connection = DriverManager.getConnection(connectionURL,user,pass);
			
		}
	
		return connection;
	}
	
	
}
