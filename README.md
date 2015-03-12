This is a REST API that returns a website navigation tree. The API is queried with the ID of a page and it returns the
 proper navigation to show for that page. The `NavigationDao` class will load the entire tree from a JSON file once
 when the application is started. When the navigation for a page is requested the tree must be *pruned* using the
 following rules:

* The root and it's direct children should *always* be included
* The path from the selected ID (node) to the root should be included
* The children of other nodes should *not* be included

Given this initial tree:


![Initial Tree](start_tree.jpg)


The result when querying the red node should be:


![Result Tree](result_tree.jpg)

Similarly for this node:

![Initial Tree](start_tree2.jpg)

Resulting in:

![Initial Tree](result_tree2.jpg)



The project contains *TODO* comments throughout the code for areas that need to be finished. There are also unit tests
that currently fail, when the tests pass the project should be ready for submission and a pull request
should be submitted.


# Running the API

To run the API you will need Java 8 and Maven 3. Using an IDE you can run the `Application#main` method
or you can run `mvn exec:java`. This will launch a REST API that you can open in your browser at:
 http://127.0.0.1:8080/ you can then pass the ID of the node you're looking for in the URL path,
 ex: http://127.0.0.1:8080/30day
