# Microservices simple

> Simple example of splitting an application into microservices using KumuluzEE.

This example demonstrates how to split an application into microservices using KumuluzEE and create a self-sustaining
package that runs independently for each one.

Let's create an online book catalogue that people can browse and place orders for the books they like.
While there would normally be many more functionalities, for brevity we are only going to look at the two of them;
browsing available books and placing orders.

We start by separating our concerns and split the catalogue and orders functionalities into two separately configured and deployed microservices.
That way we have created microservices that are only concerned with their respected functionalities.
We’ve also reduced the interference with one another and overall form a better modular and bug free application.
Each one of them will communicate with each other through pre-defined REST interfaces.

The example demonstrates the above premise.

## Requirements

In order to run this example you will need the following:

1. Java 8 (or newer), you can use any implementation:
    * If you have installed Java, you can check the version by typing the following in a command line:
        
        ```
        java -version
        ```

2. Maven 3.2.1 (or newer):
    * If you have installed Maven, you can check the version by typing the following in a command line:
        
        ```
        mvn -version
        ```
        
3. (Optional, to get a database running) Docker 1.5.0 (or newer):
    * If you have installed Maven, you can check the version by typing the following in a command line:
    
        ```
        docker -v
        ```
    
## Prerequisites

Make sure you have PostgreSQL database running in order to start the example. If you don't have one, you can simply start
an instance using docker:

    ```bash
    docker run -e POSTGRES_PASSWORD=postgres --restart=always -p 5432:5432 -d postgres:9.4
    ```

## Usage

The example uses docker to build and run the microservices.

1. Build the microservices using maven:

    ```bash
    $ mvn clean package
    ```

2. Run each individual microservice separately (separate terminal) with a single command with the appropriate environment variables that serve as the applications config:
    * `PORT` should containt the port on which the microservice will accept connections
    * `DATABASE_UNIT` should contain the microservice persistence unit (defaults are obtained from the `persistence.xml` file)
    * `DATABASE_URL` should contain the jdbc URL for the persistence unit specified above (defaults are obtained from the `persistence.xml` file)
    * `DATABASE_USER` should contain the database username (defaults are obtained from the `persistence.xml` file)
    * `DATABASE_PASS` should contain the database password (defaults are obtained from the `persistence.xml` file)
    
    ```bash
    $ PORT=3000 java -cp catalogue/target/classes:catalogue/target/dependency/* com.kumuluz.ee.EeApplication
    
    $ PORT=3001 java -cp orders/target/classes:orders/target/dependency/* com.kumuluz.ee.EeApplication
    ```
    
The application/service can be accessed on the following URLs:
    * Catalogue service - http://localhost:3000/books
    * Orders service - http://localhost:3001/orders

To shut down the example simply stop the processes in the foreground.