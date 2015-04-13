package com.bb.TreePruning;

import junit.framework.TestCase;

public class TreeNodeTest extends TestCase {
	/**
	 * This tests that multiple levels of the tree work correctly.  Each
	 * level has only 1 child.  This is a simple test to verify depth
	 * works before moving onto more complex trees.
	 */
	public void testMultipleLevels ()
	{
	  TreeNode root = new TreeNode();
	  TreeNode depth1 = new TreeNode();
	  TreeNode depth2 = new TreeNode();
	  TreeNode depth3 = new TreeNode();
	  
	  // Add the children.
	  root.add( depth1 );
	  depth2.add( depth3 );
	  depth1.add( depth2 );

	  // Verify the properties of the nodes.
	  assertTrue("The root should have 1 child.", root.hasChildren() == true);
	  assertEquals("The root should have a depth of 0.", 0, root.depth());
	  assertEquals("The root should not have a parent.", null, root.getParent());
	  
	  assertTrue("depth1 should have 1 child.", depth1.hasChildren() == true);
	  assertEquals("depth1 should have a depth of 1.", 1, depth1.depth());
	  assertEquals("depth1 should have root as its parent.", root, depth1.getParent());
	  
	  assertTrue("depth2 should have 1 child.", depth2.hasChildren() == true);
	  assertEquals("depth2 should have a depth of 2.", 2, depth2.depth());
	  assertEquals("depth2 should have depth1 as its parent.", depth1, depth2.getParent());
	  
	  assertTrue("depth3 should have not have any children.", depth3.hasChildren() == false);
	  assertEquals("depth3 should have a depth of 3.", 3, depth3.depth());
	  assertEquals("depth3 should have depth2 as its parent.", depth2, depth3.getParent());
	  


	}

	/**
	 * This tests a tree with multiple varying depths and multiple
	 * varying amounts of children at each depth.
	 */
	public void testBigTree ()
	{
	  String testData = "";
	  
	  TreeNode root = new TreeNode();
	  TreeNode d1c0 = new TreeNode( testData );
	  TreeNode d1c1 = new TreeNode( testData );
	  TreeNode d1c2 = new TreeNode( testData );
	  TreeNode d2c1c0 = new TreeNode( testData );
	  TreeNode d2c1c1 = new TreeNode( testData );
	  TreeNode d2c1c2 = new TreeNode( testData );
	  TreeNode d2c2c0 = new TreeNode( testData );
	  TreeNode d2c2c1 = new TreeNode( testData );
	  TreeNode d3c2c0c0 = new TreeNode( testData );
	  TreeNode d3c2c0c1 = new TreeNode( testData );
	  
	  root.add( d1c0 );
	  root.add( d1c1 );
	  root.add( d1c2 );
	  
	  d1c1.add( d2c1c1 );  // Second child
	  d1c1.add( d2c1c0, 0 );  // First child
	  d1c1.add( d2c1c2 );  // Third child
	  
	  d1c2.add( d2c2c0 ); 
	  d2c2c0.add( d3c2c0c0 );
	  d2c2c0.add( d3c2c0c1 );

	  d1c2.add( d2c2c1 ); 
	  
	  // Verify the tree structure.
	  assertEquals("root should be the parent of d1c0", root, d1c0.getParent());
	  assertEquals("root should be the parent of d1c1", root, d1c1.getParent());
	  assertEquals("root should be the parent of d1c2", root, d1c2.getParent());
	  
	  assertEquals("d1c1 should be the parent of d2c1c0", d1c1, d2c1c0.getParent());
	  assertEquals("d1c1 should be the parent of d2c1c1", d1c1, d2c1c1.getParent());
	  assertEquals("d1c1 should be the parent of d2c1c2", d1c1, d2c1c2.getParent());
	  
	  assertEquals("d1c2 should be the parent of d2c2c0", d1c2, d2c2c0.getParent());
	  assertEquals("d1c2 should be the parent of d2c2c1", d1c2, d2c2c1.getParent());
	  
	  assertEquals("d2c2c0 should be the parent of d3c2c0c0", d2c2c0, d3c2c0c0.getParent());
	  assertEquals("d2c2c0 should be the parent of d3c2c0c1", d2c2c0, d3c2c0c1.getParent());
	  
	  // Verify the depths of some of the nodes.
	  assertEquals("root should have a depth of 0", 0, root.depth());
	  assertEquals("d1c1 should have a depth of 1", 1, d1c1.depth());
	  assertEquals("d2c2c0 should have a depth of 2", 2, d2c2c0.depth());
	  assertEquals("d3c2c0c1 should have a depth of 3", 3, d3c2c0c1.depth());
	  
	  // Verify we can traverse the tree from root to d3c2c0c0.
	  TreeNode node = root.children()[2];  // d1c2
	  node = node.children()[0];  // d2c2c0
	  node = node.children()[0];  // d3c2c0c0
	  assertEquals("We should have tranversed the tree to d3c2c0c0.", d3c2c0c0, node);
	  
	  assertEquals("Should have test data as user object from node d3c2c0c0.", testData, node.getUserObject());
	}

}
