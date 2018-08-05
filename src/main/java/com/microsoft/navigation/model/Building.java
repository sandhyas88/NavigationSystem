package com.microsoft.navigation.model;

public class Building implements INode {
	
	private String name;
	
	public Building(String name)
	{
		this.name = name;
	}

	@Override
	public String getName() {
		
		return name;
	}
	

}
