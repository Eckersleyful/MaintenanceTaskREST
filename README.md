# MaintenanceTaskREST
A prerequisite task that implements an REST API for device maintenance tasks


Technical requirements:

MySQL server (Can also run other relational database solutions but have to figure out the Java connector dependency yourself)

Maven

Java SDK 17


1: Clone the repository and navigate to src\main\resources and open application.properties

2: Change spring.datasource.url to where ever your MySQL is running on

Note that xyz:port/SCHEMA the SCHEMA has to point whatever schema already exists in the DB. The API does not create
one dynamically.

3: Change spring.datasource.username and -.password to your own. Make sure the user
has create and write permissions. Currently just generic admin/admin.

4: Navigate to the project root where the Maven pom.xml resides and in a terminal of your choice run:

```
mvn package

java -jar target/etteplan-prerequisite-1.0-SNAPSHOT.jar org.prerequisitetask.MaintenanceEtteplan 

```
5: Server should now be running on localhost port 8080. If the port 8080 is taken, add server.port = 8081 or whichever to the application.properties file.

Make a post request to localhost:8080/devices/populate to read the mock data into the DB. 

The API is now ready to be used.


Refer to the Swagger docs below for usage information:

https://app.swaggerhub.com/apis-docs/Eckersleyful/Maintenance/1.0

