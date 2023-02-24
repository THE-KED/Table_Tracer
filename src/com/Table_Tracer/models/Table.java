package com.Table_Tracer.models;

import java.util.ArrayList;

public class Table {
	
	private String name;
	private ArrayList<Field> fields;
	
	
	public Table() {
		this.fields = new ArrayList<Field>(); 
	}
	public Table(String name,ArrayList<Field> fields) {
		this.name=name;
		this.fields=fields;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Field> getFields() {
		return fields;
	}
	public void setFields(ArrayList<Field> fields) {
		this.fields = fields;
	}
	
	
	public void addField(Field field) {
		this.fields.add(field);
	}
	
	public String getPrimaryKey() {
		
		for(Field field:fields) {
			
			if(field.isKey())
				return field.getName();
			
		}
		
		return null;
		
	}
	
	public int getIndexPrimaryKey() {
		
		int i=0;
		while (i<fields.size() && !fields.get(i).isKey()){
			
			i++;
			
		}
		
		return i;
		
	}
	
	public String Sql_syntaxe() {
		
		String syntaxe = "CREATE TABLE "+name+" ( ";
		
		for(Field field:fields) {
			
			syntaxe+=field.Sql_synstax()+", ";
		}
		
		syntaxe+="PRIMARY KEY ("+getPrimaryKey()+"));";
		
		return syntaxe;
		
	}
}
