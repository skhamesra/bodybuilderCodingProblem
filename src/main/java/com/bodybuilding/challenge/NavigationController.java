package com.bodybuilding.challenge;

import com.bodybuilding.challenge.data.NavigationDao;
import com.bodybuilding.challenge.data.NavigationNode;
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
public class NavigationController {
    private static final Logger log = LoggerFactory.getLogger(NavigationController.class);

    private NavigationDao dao;

    @Autowired
    public NavigationController(NavigationDao dao) {
        this.dao = dao;
    }

    /**
     * Returns the full Navigation tree
     *
     * @return full navigation tree
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<NavigationNode> root() {
        log.debug("Calling Navigation Service for the root node");


        NavigationNode root = dao.getRoot();
        log.debug("Returning the full tree {}", root);
        return new ResponseEntity<>(root, HttpStatus.OK);
    }

    /**
     * This returns a tree that has the full path to the node with the supplied id, plus the root node and its
     * direct children. Returns 404 if the node cannot be found
     *
     * @param id id of the node being requested
     * @return Navigation tree or null if id is not found
     */
    // TODO implement this method, remember to return 404 if the node isn't found
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<NavigationNode> select(@PathVariable("id") String id) {
        return new ResponseEntity<>((NavigationNode)null, HttpStatus.OK);
    }
}
