# MaintenanceTaskREST
A prerequisite task that implements an API for device maintenance tasks

Technical requirements:

MySQL server (Can also run other relational database solutions but have to figure out the Java connector dependency yourself)

Maven

Java SDK 17

1: Clone the repository and navigate to src\main\resources and open application.properties

2: Change spring.datasource.url to where ever your MySQL is running on

3: Change spring.datasource.username and -.password to your own. Make sure the user
has create and write permissions. Currently just generic admin/admin.

4: Navigate to project root and in terminal of your choice run:

```
mvn package

java -jar target/etteplan-prerequisite-1.0-SNAPSHOT.jar org.prerequisitetask.MaintenanceEtteplan 

```
5: Server should now be running on localhost port 8080. Make a post request to localhost:8080/devices/populate to read the mock data into the DB. 
The API is now ready to be used.

Refer to the Swagger docs below for usage information:

https://app.swaggerhub.com/apis-docs/Eckersleyful/Maintenance/1.0

