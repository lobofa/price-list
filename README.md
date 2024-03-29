# Price List Application

Maven project containing the elements for building the Price List Spring Boot application.

___

## ARTIFACT MANAGEMENT

The artifact management is done using a MAKE file located in the root folder with the following tasks:

- boot - Start the application using the Spring Boot Maven plugin
- build - Build the artifact and install it in the Maven repository

1. Make sure you have GNU Make installed (On Windows, install it as described [here](https://earthly.dev/blog/makefiles-on-windows/))
2. Open a terminal and run the 'make' command to see the declared targets

___

## API DEFINITION

With the application running, the API definition can be accesed in the following url: 

http://localhost:11010/api-docs.html

___

## DATABASE VISUALIZATION

With the application running, the H2 database can be accesed in the following url: 

http://localhost:11010/db-console

With the following parameters:

- JDBC URL: jdbc:h2:mem:prices
- Username: prices
- Password: cHJpY2Vz

___

## REPORTS

After the artifact has been built, the report for the code coverage can be found on the following location

/target/site/jacoco/index.html