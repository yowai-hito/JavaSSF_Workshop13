## Workshop 13

- Run the spring boot app using the below command line

```
./mvnw spring-boot:run -Dspring-boot.run.arguments=--dataDir=/opt/tmp/data
```

- Make sure the following entry is in the pom.xml

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>
```

- Run ./mvnw package to run test cases.
