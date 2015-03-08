package com.bodybuilding.challenge;

import com.bodybuilding.challenge.data.TaxonomyDao;
import com.bodybuilding.challenge.data.TaxonomyNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxonomyService {
    private static final Logger log = LoggerFactory.getLogger(TaxonomyService.class);
    private final TaxonomyDao taxonomyDao;

    @Autowired
    public TaxonomyService(TaxonomyDao taxonomyDao) {
        this.taxonomyDao = taxonomyDao;
    }

    /**
     * Returns a references to the root of the TaxonomyNode Tree
     *
     * @return root of the tree
     */
    public TaxonomyNode get() {
        log.debug("Calling the Taxonomy Dao to get the Root");
        return taxonomyDao.getRoot();
    }

    // TODO
    // Add a function that finds the node in the TaxonomyNode Tree
    //
    // The function should accept a selector and always return an Optional<TaxonomyNode>
    //
    // Here's a suggested function
    // public Optional<TaxonomyNode> find(String selector);



}
