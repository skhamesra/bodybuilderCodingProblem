package com.bb.TreePruning;


import junit.framework.*;

public class BodyBuilderDataNavigationTest extends TestCase {

	
	/**
	 * This tests for succesful load of hashmap  
	 */
	public void testbuildDirectPathTreeforId () 
	{
	 
		try
		{
			BodyBuilderDataNavigation.buildDirectPathTreeforId("./Navigations.json");
			assertEquals("The file has wrong number of nodes", 1067, 
					BodyBuilderDataNavigation.getBodyBuilderGraph().size());
			
			
		}
		catch(Exception e)
		{
			
			System.out.print(e.getMessage());
		}
		
	}
	/**
	 * This tests for a root element 
	 */
	public void testgetRootPruneTree () 
	{
		try
		{
			BodyBuilderDataNavigation.buildDirectPathTreeforId("./Navigations.json");
			BodyBuilderDataNavigation b= new BodyBuilderDataNavigation();
			NavigationInfo n = b.getPruneTree("root");
			assertEquals("The ID is wrong ", "root", n.getId());
			assertEquals("The Name is worong", "Home", n.getName());	
			assertEquals("The Url is wrong", "http://www.bodybuilding.com", n.getUrl());
			assertEquals("The children count is wrong", 6, n.getChildren().size());
		}
		catch(Exception e)
		{
			
			System.out.print(e.getMessage());
		}
		
	}
	/**
	 * This tests for a given ID and checks all root children and direct path tree 
	 */
	public void testgetIDPruneTree () 
	{
		try
		{
			BodyBuilderDataNavigation.buildDirectPathTreeforId("./Navigations.json");
			BodyBuilderDataNavigation b= new BodyBuilderDataNavigation();
			NavigationInfo n = b.getPruneTree("30day");
			
			
			assertEquals("The ID is wrong ", "Training_Main", n.getChildren().get(1).getId());
			assertEquals("The ID is wrong ", "find-a-plan", n.getChildren().get(1).getChildren().get(0).getId());
			assertEquals("The ID is wrong ", "30day", n.getChildren().get(1).getChildren().get(0).getChildren().get(0).getId());

			assertEquals("The ID is wrong ", "store", n.getChildren().get(0).getId());
			assertEquals("The children count ", null, n.getChildren().get(0).getChildren());
			assertEquals("The children count ", null, n.getChildren().get(2).getChildren());
			assertEquals("The children count ", null, n.getChildren().get(3).getChildren());
			assertEquals("The children count ", null, n.getChildren().get(4).getChildren());
			assertEquals("The children count ", 6, n.getChildren().size());

 		}
		catch(Exception e)
		{
			
			System.out.print(e.getMessage());
		}
		
	}

}
