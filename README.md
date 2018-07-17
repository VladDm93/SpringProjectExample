```
http://localhost:8080/swagger-ui.html - swagger address

Т.к. docker на windows не работает нормально, для запуска тестов при сборке нужно указать параметры:
mvnw clean package -Dspring.datasource.url=jdbc:postgresql://localhost:5432/data_base_name -Dspring.datasource.username=postgres -Dspring.datasource.password=postgres
```