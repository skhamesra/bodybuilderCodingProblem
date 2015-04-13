package com.bb.TreePruning;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.WebApplicationException;

import org.apache.commons.io.IOUtils;
 

import org.eclipse.jetty.server.Response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BodyBuilderDataNavigation
 
{
	static HashMap <String , TreeNode> bodyBuilderGraph = new HashMap(); 
	static NavigationInfo newJsonNode = new NavigationInfo() ;
 
	/**
	 * This Operataion  is run at the time of initialization. This would read json file, maps JSON to NavigationInfo
	 * and populate a hashMap which store a direct path from a node to Root ( prune tree ) for a given ID. This is build 
	 * recursivelly
	 */
	synchronized public static  void buildDirectPathTreeforId(String fileName) throws IOException
	{
		
 

	    FileReader jsonFileToRead = new FileReader(fileName);


	    // Get the contents of json as a string using commons IO IOUTils class.
	    String genreJson = IOUtils.toString(jsonFileToRead);
 

	    // create an ObjectMapper instance.
	    ObjectMapper mapper = new ObjectMapper();
	    // use the ObjectMapper to read the json string and create a tree
	    JsonNode node = mapper.readTree(genreJson );
 
	    newJsonNode = mapper.treeToValue(node, NavigationInfo.class);
	    TreeNode root = new TreeNode();
	    root.setUserObject( new NavigationData(newJsonNode.getId(),newJsonNode.getName(),newJsonNode.getUrl())); 
	    bodyBuilderGraph.put(((NavigationData)root.getUserObject()).getId(), root);
	

	    buildTreeGraph(newJsonNode.getChildren() , root);
 

	}
	/**
	 * A recursive call to build tree for a given ID
	 * 
	 **/
	protected static void buildTreeGraph( ArrayList<NavigationInfo> childrenArray , TreeNode Parent)
	{

		for (NavigationInfo n : childrenArray)
		{
 		    TreeNode newNode = new TreeNode();
 		    newNode.setUserObject( new NavigationData(n.getId(),n.getName(),n.getUrl())); 
		    Parent.add(newNode);
		    bodyBuilderGraph.put(((NavigationData)newNode.getUserObject()).getId(), newNode);
		    buildTreeGraph(n.getChildren(),newNode);
		    //curArray++ ;
		}
	}
 
	protected static HashMap <String , TreeNode> getBodyBuilderGraph ()
	{
		return bodyBuilderGraph;
	}
	/**
	 * This Operataion takes ID sent as part of RestCall. This search the hashMap with the Id to find a tree node with that id
	 * It then traverse through all its parent ( not including Root) and consolidate all NavigationInfo including Root and its childs   
	 *  
	 * @return A NavigationInfo with Root , its direct children and direct path to the leaf node
	 *  It throws 404 exception  if ID does not exist  .
	 */
public NavigationInfo getPruneTree (String id ) throws IOException
{

     TreeNode traverse = bodyBuilderGraph.get(id);
     if (traverse == null)
    	 throw new WebApplicationException(Response.SC_NOT_FOUND);
 	
    ArrayList<NavigationInfo> storeTraversedNavigationInfo = new ArrayList<NavigationInfo>();
    int currArrayListIndex = 0 ;
    // Traverse through all its parent till you hit Root . Create a navigationInfo for each Tree Node 
    while (  !traverse.isRoot() ) 
    {
     	
    	NavigationInfo returnInfo = new NavigationInfo();
        returnInfo.setId( ((NavigationData)traverse.getUserObject()).getId());
        returnInfo.setName(((NavigationData)traverse.getUserObject()).getName());
        returnInfo.setUrl(((NavigationData)traverse.getUserObject()).getUrl());
        returnInfo.setChildren(new ArrayList<NavigationInfo>());
        if (currArrayListIndex > 0) 
        {
        	ArrayList<NavigationInfo> child = new ArrayList<NavigationInfo>();
        	child.add(storeTraversedNavigationInfo.get(currArrayListIndex-1));
        	returnInfo.setChildren(child);
        	
        }
        storeTraversedNavigationInfo.add(returnInfo);
        traverse = traverse.getParent();
        currArrayListIndex++;
    }
    NavigationInfo buildJson = null ;
    if (storeTraversedNavigationInfo.size() > 0 )
    {
    	buildJson = copyJsonRoot( storeTraversedNavigationInfo.get(storeTraversedNavigationInfo.size() - 1));
    }
    else
    {
    	buildJson = copyJsonRoot( null );
    }
    
    return buildJson ;
}
 
 
/**
 * This Operataion takes the NavigationInfo and its Direct parent. This then merge with Root and its direct children  
 * 
 * @return A NavigationInfo which has Root, its direct children and prune tree
 */

public NavigationInfo copyJsonRoot(  NavigationInfo leafPath)
{
	NavigationInfo rootInfoWithChild = new NavigationInfo ();
	rootInfoWithChild.setId(newJsonNode.getId());
	rootInfoWithChild.setName(newJsonNode.getName());
	rootInfoWithChild.setUrl(newJsonNode.getUrl());
	ArrayList<NavigationInfo> children = new ArrayList<NavigationInfo>() ;
	for (NavigationInfo n : newJsonNode.getChildren())
	{
		if ((leafPath!= null ) && (leafPath.getId().equalsIgnoreCase(n.getId())))
		{
			 n.setChildren(leafPath.getChildren());
		}
		else
			n.setChildren(null);
		
		children.add(n);
	}
	rootInfoWithChild.setChildren(children);
	return rootInfoWithChild ; 
}
 

}
