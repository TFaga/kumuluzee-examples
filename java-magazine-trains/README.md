# Java Magazine Trains

> Example of using microservices and KumuluzEE to build a basis for a simple train booking service.

This example demonstrates how to use microservices together with KumuluzEE to construct a bigger application. For service
discovery we use Zookeeper.

Let's create an online train booking service where people can plan their route and book a ticket to go on that route.
For brevity we are only going to look at the three of them; viewing routes, book a route and a user interface to tie them all together.

We start by separating our concerns and split the routes, booking and UI functionalities into three separately configured and deployed microservices.
That way we have created microservices that are only concerned with their respected functionalities. We’ve also reduced the interference
with one another and overall form a better modular and bug free application. Each one of them will communicate with each
other through pre-defined REST interfaces.

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
        
3. Docker 1.5.0 (or newer):
    * If you have installed Maven, you can check the version by typing the following in a command line:
    
        ```
        docker -v
        ```
    
## Usage

The example uses docker to build and run the microservices.

1. Start the Zookeeper service using Docker. This will be used for service discovery:

    ```bash
    $ docker run -p 2181:2181 -d fabric8/zookeeper
    ```
    
2. Start the PostgreSQL database using Docker:

    ```bash
    docker run -e POSTGRES_PASSWORD=postgres --restart=always -p 5432:5432 -d postgres:9.4
    ```

3. Build a Docker image for every microservice we have:
    
    ```bash
    $ docker build -t trains/ui –f ui/Dockerfile .
    $ docker build -t trains/routes –f routes/Dockerfile .
    $ docker build -t trains/bookings –f bookings/Dockerfile .
    ```
4. Start the created containers with the appropriate environment variables that serve as the containers config:
    * `BASE_URI` should contain the microservice actual url (in our case we will use the Docker hosts IP)
    * `ZOOKEEPER_URI` should contain the Zookeeper service URI and port (in our case we will use the Docker hosts IP)
    * `DATABASE_UNIT` should contain the microservice persistence unit (in our case we will use `trains`)
    * `DATABASE_URL` should contain the jdbc URL for the persistence unit specified above (in our case we will use the Docker hosts IP)
    * `DATABASE_USER` should contain the database username
    * `DATABASE_PASS` should contain the database password
    
    ```bash
    docker run -p 3001:8080 -d -e BASE_URI='http://172.17.42.1:3001' -e ZOOKEEPER_URI=172.17.42.1:2181 -e DATABASE_UNIT=trains -e DATABASE_USER=postgres -e DATABASE_PASS=postgres -e DATABASE_URL='jdbc:postgresql://172.17.42.1:5432/postgres' trains/routes
    docker run -p 3002:8080 -d -e BASE_URI='http://172.17.42.1:3002' -e ZOOKEEPER_URI=172.17.42.1:2181 -e DATABASE_UNIT=trains -e DATABASE_USER=postgres -e DATABASE_PASS=postgres -e DATABASE_URL='jdbc:postgresql://172.17.42.1:5432/postgres' trains/bookings
    docker run -p 3000:8080 -d -e BASE_URI='http://172.17.42.1:3000' -e ZOOKEEPER_URI=172.17.42.1:2181 trains/ui
    ```
    
The application/service can be accessed on the following URLs:
    * UI - http://localhost:3000/faces/index.xhtml
    * Routes service - http://localhost:3001/routes
    * Bookings service - http://localhost:3002/bookings

To shut down the example simply stop and destroy the created containers.