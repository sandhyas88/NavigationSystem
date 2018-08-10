package com.microsoft.navigation.model;

import java.util.HashSet;
import java.util.Set;

public class Building implements INode {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Building other = (Building) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	private String id;
	Set<IEdge> adjacencySet;
	
	public Building(String id)
	{
		this.id = id;
		adjacencySet = new HashSet<IEdge>();
	}

	@Override
	public String getId() {
		
		return id;
	}

	@Override
	public String toString() {
		return id;
	}

	public Set<IEdge> getAdjacencySet() {
		return adjacencySet;
	}

	@Override
	public void setAdjacencySet(Set<IEdge> adjacencySet) {
		this.adjacencySet = adjacencySet;
	}
	

	
}
