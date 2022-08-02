# Hotel-Service
Design one microservice CRUD Application Using SpringBoot, RestAPI, CustomException, Spring Basic Security,Swagger , log4j and JUnit for each API


Creating uber jar, which creates execution jar with all the dependencies.

Local Build (mvn clean package / mvn install):
> mvn clean package

TEST Result: 
<img width="939" alt="image" src="https://user-images.githubusercontent.com/37171860/182346997-ddf2a971-24f8-4ed1-8764-55c9d999d9da.png">

Execution :  

>java -jar target\Hotel-Service-0.0.1-SNAPSHOT.jar

<img width="944" alt="image" src="https://user-images.githubusercontent.com/37171860/182349909-a72f0662-5b99-4b21-a0d9-58936fcfb3fe.png">

API Request:



http://localhost:8081/menu            -> post all the request 

Example: 

{
    "menuName" : "Chicken",
   "serviceType" : "Good",
   "customerName" : "Gajendra",
   "menuPrice" :  100
}

http://localhost:8081/menu            -> get all the request

http://localhost:8081/menu/{id}       -> get request by using ID 

http://localhost:8081/menu/updateMenu -> update request by using ID

http://localhost:8081/menu/{id}       -> delete request by using ID



