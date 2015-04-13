This is a solution to the coding problem. This runs on port 9090. It is a self contained application which uses DropWizard as Web server. 
This application reads the file Navigation.json from root project directory. It Parses JSON data to java objects (NavigationInfo) and create a hashMap with key= id and prune tree 
( leaf to root path).This is done at the time of initialization when application comes. This is a one time activity

I ran using eclipse. Please import the maven project and run as java application . It starts up the service .  

From Maven use following command ( go into project directory and run)

mvn clean

mvn test 

mvn package

Above command would create a FAT jar file , run the application


java -jar target\TreePruning-0.0.1-SNAPSHOT.jar server

Now http server is up listening on port 9090. 

---------------------------------------------TEST RUN ----------------------------------------------------------------------------------------------
 
It exposes a rest full api tree-pruning. This takes one parameter = ID. Here are test results 


Test Scenario 1 --> ID = test   (does not exist)
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
http://localhost:9090/tree-pruning?ID=test
HTTP ERROR 404

Problem accessing /tree-pruning. Reason:

    Not Found

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


Test Scenario 2 --> No ID provided. It returns Root and its direct children

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

http://localhost:9090/tree-pruning
{"id":"root","name":"Home","url":"http://www.bodybuilding.com","children":[{"id":"store","name":"Store","url":"http://www.bodybuilding.com/store/","children":null},{"id":"Training_Main","name":"Training","url":"http://www.bodybuilding.com/fun/training.html","children":null},{"id":"articles-training","name":"Articles & Videos","url":"http://www.bodybuilding.com/fun/features.html","children":null},{"id":"bodyspace","name":"Community","url":"http://bodyspace.bodybuilding.com/","children":null},{"id":"member-overview","name":"Member Profile","url":"http://bodyspace.bodybuilding.com/[slug]","children":null},{"id":"My Home","name":"Dashboard","url":"http://my.bodybuilding.com/community/my-bodyspace/","children":null}]}

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



Test Scenario 3 --> ID = 7daybeginner
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

http://localhost:9090/tree-pruning?ID=7daybeginner
{"id":"root","name":"Home","url":"http://www.bodybuilding.com","children":[{"id":"store","name":"Store","url":"http://www.bodybuilding.com/store/","children":null},{"id":"Training_Main","name":"Training","url":"http://www.bodybuilding.com/fun/training.html","children":[{"id":"find-a-plan","name":"Find A Plan","url":"http://www.bodybuilding.com/guides","children":[{"id":"7daybeginner","name":"7 Day Beginner Trainer","url":"http://www.bodybuilding.com/fun/new-year-start-here-7-day-beginner-trainer.html","children":[]}]}]},{"id":"articles-training","name":"Articles & Videos","url":"http://www.bodybuilding.com/fun/features.html","children":null},{"id":"bodyspace","name":"Community","url":"http://bodyspace.bodybuilding.com/","children":null},{"id":"member-overview","name":"Member Profile","url":"http://bodyspace.bodybuilding.com/[slug]","children":null},{"id":"My Home","name":"Dashboard","url":"http://my.bodybuilding.com/community/my-bodyspace/","children":null}]}

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


Test Scenario 4 --> ID=Swimming

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

http://localhost:9090/tree-pruning?ID=Swimming

{"id":"root","name":"Home","url":"http://www.bodybuilding.com","children":[{"id":"store","name":"Store","url":"http://www.bodybuilding.com/store/","children":null},{"id":"Training_Main","name":"Training","url":"http://www.bodybuilding.com/fun/training.html","children":null},{"id":"articles-training","name":"Articles & Videos","url":"http://www.bodybuilding.com/fun/features.html","children":[{"id":"Sports","name":"For Sports","url":"http://www.bodybuilding.com/fun/bbmainsports.htm","children":[{"id":"endurance-sports","name":"Endurance Sports","url":"http://www.bodybuilding.com/fun/bbmainsports.htm?sel=endurance-sports#endurance-sports","children":[{"id":"Swimming","name":"Swimming","url":"http://www.bodybuilding.com/fun/bbinfo.php?page=Swimming","children":[]}]}]}]},{"id":"bodyspace","name":"Community","url":"http://bodyspace.bodybuilding.com/","children":null},{"id":"member-overview","name":"Member Profile","url":"http://bodyspace.bodybuilding.com/[slug]","children":null},{"id":"My Home","name":"Dashboard","url":"http://my.bodybuilding.com/community/my-bodyspace/","children":null}]}


------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
