package com.bodybuilding.challenge.data;

import org.springframework.stereotype.Repository;

import java.io.IOException;

import static com.bodybuilding.challenge.data.NavigationUtil.loadNavigationTree;

/**
 * DAO object that returns navigation trees. This DAO should load the tree from the JSON only once at construction time
 */
// THIS CLASS SHOULD NOT NEED TO BE MODIFIED
@Repository
public class NavigationDao {

    private final NavigationNode rootNode;

    public NavigationDao() throws IOException {
        this.rootNode = loadNavigationTree();
    }

    /**
     * Returns the root of the NavigationNode tree
     *
     * @return root to the tree
     */
    public NavigationNode getRoot() {
        return rootNode;
    }


    /**
     * Returns a pruned tree for the requested id, returns null if the id isn't found in the tree
     * @param id of the node
     * @return pruned tree or null if the node is not found
     */
    public NavigationNode findAndPrune(String id) {
        return NavigationUtil.pruneTree(rootNode, id);
    }
}
