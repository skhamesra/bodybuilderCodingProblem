package com.bb.TreePruning;

import java.io.IOException;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
 
public class TreePruningApplication extends Service<TreePruningConfiguration> {
    public static void main(String[] args) throws Exception {
        new TreePruningApplication().run(args);
    }

    /**
     * This initialize method would build the hashmap of Id and direct path to root for a tree node 
     * 
     **/
 
    @Override
    public void initialize(Bootstrap<TreePruningConfiguration> bootstrap) {
      
    	try {
    		BodyBuilderDataNavigation.buildDirectPathTreeforId("./Navigations.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * This  method register Rest resource with Server and set port as 9090 to run on 
     * 
     **/
    @Override
    public void run(TreePruningConfiguration configuration,
                    Environment environment) {
    	
    	configuration.getHttpConfiguration().setAdminPort(9091);
        configuration.getHttpConfiguration().setPort(9090);
        
    	final TreePruningResource resource = new TreePruningResource(  );
    	environment.addResource(resource);
    	     
    }

}