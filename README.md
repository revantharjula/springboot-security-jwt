# springboot-security-jwt
This is a seed project with boilerplate code

clone this code and run mvn clean install after a successful build.

java -jar target/springboot-security-jwt.jar

app runs on port 8085

localhost:8085/token generates you a token but need to send a post request with the json 

{ 
  "username": "name",
  "id": 123, 
  "role": "admin"
}

save the token (generatedtoken)

localhost:8085/hello/notsecured greets hello - no security applied for this 

localhost:8085/secured need a get request with a header "authorization" and value as "Token (generatedtoken)"


