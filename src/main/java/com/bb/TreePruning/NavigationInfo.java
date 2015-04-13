package com.bb.TreePruning;

import java.util.ArrayList;

public class NavigationInfo extends NavigationData {
 
	
	ArrayList<NavigationInfo> children;

 
 	public void setChildren(ArrayList<NavigationInfo> children) {
		this.children = children;
	}
	public ArrayList<NavigationInfo> getChildren( ) {
		return children;
	}
	
	public String toString()
	{
		return id.toString() + " " + name + " " + url ;
	}
	
}
