package com.Table_Tracer.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	public static Properties Reader(String file) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream(file);
		prop.load(input);
		input.close();
		
		return prop;
	}
}
