package com.bodybuilding.challenge.data;

import org.springframework.stereotype.Repository;

import java.io.IOException;

import static com.bodybuilding.challenge.data.TaxonomyNodeUtils.loadTaxonomyTree;

@Repository
public class TaxonomyDao {

    private final TaxonomyNode rootNode;

    public TaxonomyDao() throws IOException {
        this.rootNode = loadTaxonomyTree();
    }

    /**
     * Returns the root of the TaxonomyNode tree
     *
     * @return root to the tree
     */
    public TaxonomyNode getRoot() {
        return rootNode;
    }


}
