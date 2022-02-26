# Setup && Run 

First makes sure you have maven and docker installed on your machine.

### Clone repository
```
git clone https://github.com/enemymerch/ReadingIsGood.git
```
### Change existing directory 
```
cd ReadingIsGood
```
### Creating the jar
```
mvn clean package
```
### Building Docker Image
```
docker build -t mcan/rig-app .
```
### Run Docker Image
```
docker run -p 9292:9292 mcan/rig-app:latest
```

# API's
Have 4 different API
to check out more about API's,
you can find openAPI page on this url :
'baseUrl/rig/swagger-ui.html'. 
Also there's a postman collection on root directory of the project named: 'rig_postman_collection.json' 
which you can also use to send requests. But do not forget to set environment variables for postman: baseUrl, bookAPI, customerAPI, orderAPI, statisticAPI, loginAPI
.And also do not forget to update Authorization header before sending secured requests
### Authentication API 
to get access token to use other APIS
```
baseUrl/rig/api/v1/login/auth
```
IMPORTANT
Initially , i created two users with
- username: 'mcan' , password: 'mcan'
- username: 'jhonny' password: 'jhonny'

To request other API's you need to provide access token in request headers
```
header: 'Authentication' value: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqaG9ubnkiLCJyb2xlcyI6WyJjdXN0b21lciJdLCJleHAiOjE2NDU5MDMzNzMsImlhdCI6MTY0NTg4NTM3M30.Pkn6znkCw-n6fUaR0fVm0Smse0WwVvCfun9XZmMAh8DKJAffVudpKKomVk2H45AWsMe2TtOhNnJ1-bPbdPGdJw'
```
### Customer API 

- persist new customers
- query all orders of the customer

### Book API
- persist new book
- update book’s stock

### Order API
- persist new order
- query order by Id
- List orders by date interval

### Statistic API
- list monthly order statistics


# Custom Dependencies
1- To generate secret signed jwt access token for customer
```
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>3.4.1</version>
</dependency>
```
2- lombok for easy usega of getter, setters and constructors
```
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.16</version>
    <scope>provided</scope>
</dependency>
```
3- mapstruct to map between request, response and entity dtos
```
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>1.4.1.Final</version>
</dependency>
```
4- for auto-openAPI documentation
```
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.5.12</version>
</dependency>
```


