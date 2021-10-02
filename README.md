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



