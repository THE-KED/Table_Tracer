package com.Table_Tracer.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class JournalMangager {
	
	
	private static File journal = new File("journal.txt");
	
	public JournalMangager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void Write(String Trace) throws IOException {
		
		if(!journal.exists()) {
			try {
				journal.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		FileWriter fileWrite = new FileWriter(journal,true);
		BufferedWriter bw = new BufferedWriter(fileWrite);
		
		bw.write(Trace);
		bw.newLine();
		bw.close();
		fileWrite.close();
		
	}
	
	public Date LastUpdate() {
//		FileReader fileReader = new FileReader(“journal.txt”);

		return null;
		
	}

}
