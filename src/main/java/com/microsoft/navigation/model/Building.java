package com.microsoft.navigation.model;

public class Building implements INode {
	
	private String id;
	
	public Building(String id)
	{
		this.id = id;
	}

	@Override
	public String getId() {
		
		return id;
	}
	

}
