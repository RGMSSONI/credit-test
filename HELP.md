# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

build the application with -> mvn clean install
run the application -> mvn spring-boot:run


I am not persisting any data to the database. So you have to create profile with POST profile before doing follow, unfollow and post to the application.

you can check user profile with GET call of profile. where you can check followers list and following list.


I have uploaded the postman collection for the same. kindly check inside resource folder and import it in POSTMAN.

Sequence order -->>>
1. POST http://localhost:8080/createProfile 
Request Body
{
"userId":"1",
"followers":[],
"following":[]
}

2. GET http://localhost:8080/getProfile/1

3. POST http://localhost:8080/1/follow?id=2

4. POST http://localhost:8080/2/createPost
Request Body
{
	"postId":"3",
	"content":"hello Akash!"
}

5. POST http://localhost:8080/1/unfollow?id=2