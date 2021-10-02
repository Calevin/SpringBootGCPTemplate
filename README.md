# SpringBootGCPTemplate
Template repository Spring Boot and Google Compute Platform (GCP)

## Setup
### 1. Set GCP project
```
gcloud config list project

gcloud config set project <PROJECT_ID>
```

### 2. Create a new Spring Boot web app
* Project: Maven Project
* Languaje: Java
* Spring Boot: 2.5.5
* Packaging: Ja
* Java: 11
* Dependencies: Spring Web, Lombok, Spring Data JPA, H2

[Spring initializr configuration](https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.5.5&packaging=jar&jvmVersion=11&groupId=com.calevin&artifactId=springboot-gcp-template&name=springboot-gcp-template&description=Template%20Spring%20Boot%20and%20Google%20Compute%20Platform&packageName=com.calevin.springboot-gcp-template&dependencies=web,lombok,data-jpa,h2)

### 3. Add Maven App Engine Plugin
pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" ...>
  ...
  <build>
    <plugins>
      ...
      <plugin>
        <groupId>com.google.cloud.tools</groupId>
        <artifactId>appengine-maven-plugin</artifactId>
        <version>2.2.0</version>
        <configuration>
          <version>1</version>
          <projectId>GCLOUD_CONFIG</projectId>
        </configuration>
      </plugin>
      ...
    </plugins>
  </build>
</project>
```

### 4. Add App Engine descriptor
```
mkdir -p src/main/appengine/
touch src/main/appengine/app.yam
```

### 5. Add HelloWorldController 
```java
// Add the controller.
@RestController
class HelloWorldController {
  @GetMapping("/")
  public String hello() {
    return "hello world!";
  }
```

### 6. Run local
http://localhost:8080/
```
.\mvnw.cmd -DskipTests spring-boot:run
```