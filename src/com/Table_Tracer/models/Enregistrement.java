package com.Table_Tracer.models;

import java.util.ArrayList;

public class Enregistrement {

	private Table table;
	private ArrayList<String> fieldsValues;
	
	
	public Enregistrement() {
		super();
		this.fieldsValues = new ArrayList<String>();
		// TODO Auto-generated constructor stub
	}
	
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public ArrayList<String> getfieldsValues() {
		return fieldsValues;
	}
	public void setfieldsValues(ArrayList<String> fieldsValues) {
		this.fieldsValues = fieldsValues;
	}
	
	
	public void addValue(String value) {
		
		fieldsValues.add(value);
	}
	
	public String Sql_syntaxe() {
		
		String fields = "";
		String values = "";
		
		for (int i=0;i<fieldsValues.size();i++) {
			
			if (!fieldsValues.get(i).equals("NULL")) {
					values+="'"+fieldsValues.get(i)+"',";
					fields+=table.getFields().get(i).getName()+",";
				}
			}
		
		//remove the last ","
		fields = fields.substring(0, fields.length()-1);
		values = values.substring(0, values.length()-1);

	
		return "INSERT INTO "+table.getName()+" ("+fields+") VALUES ("+values+")";

	}
	
	public String insertSyntaxe() {
		
		String fields = "";
		String values = "";
		
		for (int i=0;i<fieldsValues.size();i++) {
			
			if (!fieldsValues.get(i).equals("NULL")) {
					values+="?,";
					fields+=table.getFields().get(i).getName()+",";
				}
			}
		
		//remove the last ","
		fields = fields.substring(0, fields.length()-1);
		values = values.substring(0, values.length()-1);

		return "INSERT INTO "+table.getName()+" ("+fields+") VALUES ("+values+")";

	}
	
	public String updateSyntaxe() {
		
		String str = "";
		
		for (int i=0;i<fieldsValues.size();i++) {
			
			if (!fieldsValues.get(i).equals("NULL")) {
					str+=table.getFields().get(i).getName()+"=?,";
				}
			}
		
		//remove the last ","
		str = str.substring(0, str.length()-1);

		return "UPDATE "+table.getName()+" SET "+str+" WHERE "+table.getPrimaryKey()+"=?";

	}

	public String getid() {
		// TODO Auto-generated method stub
		
		int i =0;
		String key = table.getPrimaryKey(); 
		while(i<table.getFields().size() && !table.getFields().get(i).getName().equals(key)) {
			i++;
		}
		
		return fieldsValues.get(i);
	}
	
	public boolean compar(Enregistrement elt) {
		
		int i=0;
		while(i<fieldsValues.size()) {
			
			if(!(fieldsValues.get(i).equals(elt.fieldsValues.get(i))))
				return false;
			i++;
		}
		
		return true;
		
	}
	
}
