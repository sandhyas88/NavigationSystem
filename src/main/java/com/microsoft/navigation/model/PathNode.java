package com.microsoft.navigation.model;

public class PathNode {
	
	private INode node;
	private double minDistance;
	private PathNode previous;
	
	public PathNode(INode node)
	{
		this.setNode(node);
	}

	public INode getNode() {
		return node;
	}

	public void setNode(INode node) {
		this.node = node;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	public PathNode getPrevious() {
		return previous;
	}

	public void setPrevious(PathNode uNode) {
		this.previous = uNode;
	}
	
	

}
