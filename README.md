### Simple REST api example

### Why this project exists?
To show how to implement simple REST api using:
 - Spring boot (https://spring.io/projects/spring-boot)
 - Jpa (https://en.wikipedia.org/wiki/Java_Persistence_API)
 - Postgres (https://www.postgresql.org/)
 - Liquibase (https://www.liquibase.org/)
 - Rest assured (http://rest-assured.io/)
 - TestContainers (https://www.testcontainers.org/)
 - Mapstruct (http://mapstruct.org/)
### How to run?

1. Install postgres
- Create database using file from dev-support/database_setup.sql
- If you know what docker-compose is, go to dev-support folder, it contains docker-compose.yml which will run postgres database with default parameters (database name, username\password)

2. Run ./mvn spring-boot:run 
3. Go to http://localhost:8080/swagger-ui.html

### Windows users. Attention!!!
This project uses TestContainers (https://www.testcontainers.org/) in order to run integration tests with postgres. This can be a problem on windows. So you must specify database params:

```
mvnw clean verify -Dspring.datasource.url=jdbc:postgresql://localhost:5432/data_base_name -Dspring.datasource.username=postgres -Dspring.datasource.password=postgres
```

### Params
If you don't know how to specify params learn it here:
https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html

* spring.datasource.url - postgres jdbc url. Example: jdbc:postgresql://localhost:5432/cat_database
* spring.datasource.username - postgres user username
* spring.datasource.password - postgres user password
