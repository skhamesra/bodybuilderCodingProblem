package com.bodybuilding.challenge;

import com.bodybuilding.challenge.data.TaxonomyNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxonomyController {
    private static final Logger log = LoggerFactory.getLogger(TaxonomyController.class);
    private final TaxonomyService taxonomyService;

    @Autowired
    public TaxonomyController(TaxonomyService taxonomyService) {
        this.taxonomyService = taxonomyService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<TaxonomyNode> root() {
        log.debug("Calling Taxonomy Service for the root node");
        TaxonomyNode taxonomyNode = taxonomyService.get();

        log.debug("Returning the full tree {}", taxonomyNode);
        return new ResponseEntity<>(taxonomyNode, HttpStatus.OK);
    }

    @RequestMapping(value = "/{selector}", method = RequestMethod.GET)
    public ResponseEntity<TaxonomyNode> select(@PathVariable("selector") String selector){

        log.debug("Looking for selector {}", selector);

        // TODO
        // Add a function call to taxonomy service to find the requested node
        //
        // Optional<TaxonomyNode> node = taxonomyService.find(selector)

        // if (node.isPresent()) {
        //    log.debug("Didn't find node {}", selector);
        // } else {
        //    log.debug("Found node {} for selector {}", node.get(), selector);
        // }

        // Replace the following returns statement with the following logic:
        //
        // Return status HTTP 404 (NotFound), when the node optional is empty
        // Return status HTTP 200 (OK) and the node, when the node optional is present
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
}
