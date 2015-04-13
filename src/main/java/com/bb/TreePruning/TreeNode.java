package com.bb.TreePruning;


public class TreeNode
{
  /**
   * This node's parent node.  If this is the root of the tree then
   * the parent will be <code>null</code>.
   */
  private TreeNode parent;
  
  /**
   * An array of all this node's child nodes.  The array will always
   * exist (i.e. never <code>null</code>) and be of length zero if this is
   * a leaf node.

   */
  private TreeNode[] children = new TreeNode[0];
  
  /**
   * Constructs a tree node object.  It can become the root of a tree.
   * Or it can become a child of another node by calling the other node's
   * <code>add</code> method.
   * <p>

   */
  public TreeNode ()
  {
    // Nothing needed.
  }
  
  /**
   * Constructs a tree node object.  It can become the root of a tree.
   * Or it can become a child of another node by calling the other node's
   * <code>add</code> method.
   * 
   * @param userObject is an object this node encapsulates.  It is up to
   *  the developer to maintain its type.  To get the object back out
   *  call <code>getUserObject</code>.
   */
  public TreeNode (Object userObject)
  {
    m_userData = userObject;
  }

  /**
   * Adds the <code>child</code> node to this container making this its parent.
   * 
   * @param child is the node to add to the tree as a child of <code>this</code>
   * 
   * @param index is the position within the children list to add the
   *  child.  It must be between 0 (the first child) and the
   *  total number of current children (the last child).  If it is
   *  negative the child will become the last child.
   */
  public TreeNode add (TreeNode child, int index)
  {
    // Add the child to the list of children.
    if ( index < 0 || index == children.length )  // then append
    {
      TreeNode[] newChildren = new TreeNode[ children.length + 1 ];
      System.arraycopy( children, 0, newChildren, 0, children.length );
      newChildren[children.length] = child;
      children = newChildren;
    }
    else if ( index > children.length )
    {
      throw new IllegalArgumentException("Cannot add child to index " + index + ".  There are only " + children.length + " children.");
    }
    else  // insert
    {
      TreeNode[] newChildren = new TreeNode[ children.length + 1 ];
      if ( index > 0 )
      {
        System.arraycopy( children, 0, newChildren, 0, index );
      }
      newChildren[index] = child;
      System.arraycopy( children, index, newChildren, index + 1, children.length - index );
      children = newChildren;
    }
    
    // Set the parent of the child.
    child.parent = this;
    
    return child;
  }
  
  /**
   * Adds the <code>child</code> node to this container making this its parent.
   * The child is appended to the list of children as the last child.
   */
  public TreeNode add (TreeNode child)
  {
    return add( child, -1 );
  }
  

  /**
   * Gets the parent node of this one.
   * 
   * @return The parent of this node.  This will return <code>null</code>
   *  if this node is the root node in the tree.
   */
  public TreeNode getParent ()
  {
    return parent;
  }
  
  /**
   * Returns if this node has children or if it is a leaf
   * node.
   * 
   * @return <code>true</code> if this node has children; <code>false</code>
   *  if it does not have any children.
   */
  public boolean hasChildren ()
  {
    if ( children.length == 0 )
    {
      return false;
    }
    else
    {
      return true;
    }
  }
  
  /**
   * Gets this node's depth in the tree.  The root node will
   * have a depth of 0, first-level nodes will have a depth
   * of 1, and so on.
   * 
   * @return The depth of this node in the tree.
   */
  public int depth ()
  {
    int depth = recurseDepth( parent, 0 );
    return depth;
  }
  private int recurseDepth (TreeNode node, int depth)
  {
    if ( node == null )  // reached top of tree
    {
      return depth;
    }
    else
    {
      return recurseDepth( node.parent, depth + 1 );
    }
  }
  /**
   * Returns if this node is the root node in the tree or not.
   * 
   * @return <code>true</code> if this node is the root of the tree;
   *  <code>false</code> if it has a parent.
   */
  public boolean isRoot ()
  {
    if ( parent == null )
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Gets a list of all the child nodes of this node.
   * 
   * @return An array of all the child nodes.  The array will
   *  be the size of the number of children.  A leaf node
   *  will return an empty array, not <code>null</code>.
   */
  public TreeNode[] children ()
  {
    return children;
  }
  


  /**
   * A handle to the programmer assigned object encapsulated by this
   * node.  This will be <code>null</code> when the user has not assigned
   * any data to this node.
   */
  private Object m_userData;
  
  /**
   * Attaches a user defined object to this node.  Only one
   * object can be attached to a node.
   * 
   * @param userObject is the programmer defined object to
   *  attach to this node in the tree.  Set it to <code>null</code>
   *  to clear any objects.
   */
  public void setUserObject (Object userObject)
  {
    m_userData = userObject;
  }
  
  /**
   * Gets the user defined object attached to this node.  
   * 
   * @return The programmer defined object attached to this
   *  node in the tree.  Returns <code>null</code> if no object is
   *  attached.
   */
  public Object getUserObject ()
  {
    return m_userData;
  }
}