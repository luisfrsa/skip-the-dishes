# Food Plus

## Introduction

> This is a project built for Skip the Dishes event, provided by Vanhack.
This project is developed with this stacks:
 - Java 8
 - Spring-boot 2 framework
 - Mysql
 - Hibernate
 - JPA
 - Docker
 - Docker compose



## Rest request

> This application consists in a simple User, Courier, Restaurant and Order CRUD (create, read, update, delete), using a RESTful API, that can be accessed in: http://localhost:8080, when it is running.

> It also includes a creation of Order, with parameters User.id, Restaurant.id, and the order value.
> Finally it lets a Courrier to ask for orders to deliver.

 - GET http://localhost:8000/api/courier/assignOrder/{id} -> assign Order to courier 
 - GET http://localhost:8000/api/order/{idUser}/{idRestaurant}/{value} -> create new order for user, with restaurant and value
 - 
 - GET http://localhost:8000/api/courier  
 - GET http://localhost:8000/api/courier/{id}  
 - POST http://localhost:8000/api/courier  
 - PUT http://localhost:8000/api/courier  
 - DELETE http://localhost:8000/api/courier  
 - 
 - GET http://localhost:8000/api/order
 - GET http://localhost:8000/api/order/{id}
 - POST http://localhost:8000/api/order
 - PUT http://localhost:8000/api/order
 - DELETE http://localhost:8000/api/order
 - 
 - GET http://localhost:8000/api/restaurant
 - GET http://localhost:8000/api/restaurant/{id}
 - POST http://localhost:8000/api/restaurant
 - PUT http://localhost:8000/api/restaurant
 - DELETE http://localhost:8000/api/restaurant
 - 
 - GET http://localhost:8000/api/user
 - GET http://localhost:8000/api/user/id
 - POST http://localhost:8000/api/user
 - PUT http://localhost:8000/api/user
 - DELETE http://localhost:8000/api/user

## Installation

> 
- Create a mysql database located in 127.0.0.1:3306, with user root, and password 123.
Create a database called userorder;
- Run the command mvn clean install inside ./order folder.
- Run the command mvn spring-boot:run inside ./order folder

> You can also run the script inside ./scripts/run.sh to run the services with docker, and the application will start automatically.
> Dont forget about access sonar at http://localhost:9000/


 

