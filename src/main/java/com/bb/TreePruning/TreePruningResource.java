package com.bb.TreePruning;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
 
import javax.ws.rs.core.MediaType;

 

import java.io.IOException;
 

@Path("/tree-pruning")
@Produces(MediaType.APPLICATION_JSON)
public class TreePruningResource {
	
     private final String defaultId = "root";
 

    public TreePruningResource( ) {
      }

    @GET
    @Timed
    public NavigationInfo getWebsiteNavigationData(@QueryParam("ID") Optional<String> id) throws IOException {
        final String value =   id.or(defaultId);
   
        BodyBuilderDataNavigation dataNavigation = new BodyBuilderDataNavigation();
    	
        return  dataNavigation.getPruneTree(value);
 
    }

}
