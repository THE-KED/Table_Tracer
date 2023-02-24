package com.Table_Tracer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.Table_Tracer.connection.SourceDbConnection;
import com.Table_Tracer.connection.TargetDbConnection;
import com.Table_Tracer.models.Enregistrement;
import com.Table_Tracer.models.Table;
import com.Table_Tracer.utils.JournalMangager;

public class Reviser {
	
	
	
	public static void correctTable(Table source , Table target) throws ClassNotFoundException, SQLException, IOException {
		
		PreparedStatement TargetStatement = TargetDbConnection.getconnection().prepareStatement("SELECT * FROM "+target.getName());
		ResultSet TargetResult = TargetStatement.executeQuery() ;
		
		PreparedStatement SourceStatement = SourceDbConnection.getconnection().prepareStatement("SELECT * FROM "+source.getName());
		ResultSet SourceResult = SourceStatement.executeQuery() ;
		
		HashMap<String, Enregistrement> targetElements = new HashMap<String,Enregistrement>();
		
		
		while(TargetResult.next()) {
			
			Enregistrement element = new Enregistrement();
			
			element.setTable(target);

			
			for (int i=1;i<=target.getFields().size();i++) {
				
				try {
					element.addValue(TargetResult.getObject(i).toString());
				} catch (NullPointerException e) {
					// TODO: handle exception
					element.addValue("NULL");
				}
				
			}
			
			targetElements.put(element.getid(), element);
			
		}
		TargetResult.close();
		TargetStatement.close();
		
		
		
			int indexKey = source.getIndexPrimaryKey();
			while(SourceResult.next()) {
				
				Enregistrement elt = new Enregistrement();
				
				elt.setTable(source);

				
				for (int i=1;i<=source.getFields().size();i++) {
					
					try {
						elt.addValue(SourceResult.getObject(i).toString());
					} catch (NullPointerException e) {
						// TODO: handle exception
						elt.addValue("NULL");
					}
				}

				if(targetElements.containsKey(SourceResult.getObject(indexKey+1).toString())) {
					Enregistrement targetElm = targetElements.get(SourceResult.getObject(indexKey+1).toString());

					if(!targetElm.compar(elt)) {
						TargetStatement = TargetDbConnection.getconnection().prepareStatement(elt.updateSyntaxe());

						int n=1;
						for(int i=1;i<=elt.getfieldsValues().size();i++) {
							
							if(!elt.getfieldsValues().get(i-1).equals("NULL")) {
								TargetStatement.setObject(n,SourceResult.getObject(i));
								n++;
							}	
						}
						
						TargetStatement.setObject(n,SourceResult.getObject(indexKey+1));
						
						TargetStatement.executeUpdate();
						JournalMangager.Write(elt.updateSyntaxe());
						TargetStatement.close();
					
					}
						
					}
				else {

					TargetStatement = TargetDbConnection.getconnection().prepareStatement(elt.insertSyntaxe());

					int n=1;
					int i=1;
					while(i<=elt.getfieldsValues().size()) {
						
						if(!elt.getfieldsValues().get(i-1).equals("NULL")) {
							TargetStatement.setObject(n,SourceResult.getObject(i));
							n++;
						}	
						i++;
					}
					TargetStatement.executeUpdate();
					JournalMangager.Write(elt.insertSyntaxe());
					TargetStatement.close();
						
				}
				
			}
			
			SourceStatement.close();
			TargetStatement.close();
			
		}
	
}
