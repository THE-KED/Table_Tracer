package com.Table_Tracer.models;

public class Field {
	
	private String name;
	private String type;
	private boolean nullable;
	private boolean isKey;
	private String DefaultValue;
	private String Extra;
	
	
	public Field(	String name , String type ,String nullable , 
					String isKey , String DefaulValue , String Extra	) {
		
		this.name=name;
		this.type=type;
		if(nullable.equals("YES"))
			this.nullable=true;
		else
			this.nullable=false;
		if(isKey.equals("PRI"))
			this.isKey=true;
		else
			this.isKey=false;
		if(DefaulValue==null)
			this.DefaultValue="NULL";
		else 
			this.DefaultValue=DefaulValue;
		this.Extra=Extra.toUpperCase();
		
	}
	
	public Field() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	public boolean isKey() {
		return isKey;
	}
	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}
	public String getDefaultValue() {
		return DefaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		DefaultValue = defaultValue;
	}
	public String getExtra() {
		return Extra;
	}
	public void setExtra(String extra) {
		Extra = extra.toUpperCase();
	}
	
	
	public String Sql_synstax() {
		
		if(nullable)
			return this.name+" "+type+" DEFAULT "+DefaultValue;
		
		else
			return this.name+" "+type+" NOT NULL "+Extra;
		
	}
	

}
