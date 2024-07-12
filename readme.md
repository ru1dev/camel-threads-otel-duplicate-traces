# Duplicate trace id with tread pool example

env:
```
Gradle 6.8.3
Springboot 2.2.11.RELEASE
Spring 5.3.16
Apache Camel 2.25.1
Kotlin version 1.4.0
```

VM options for opentelemetry javaagent
```
-javaagent:src/main/resources/opentelemetry-javaagent-2.5.0.jar
-Dotel.metrics.exporter=none
-Dotel.logs.exporter=none
-Dotel.traces.exporter=none
```

endpoint to test
```http request
http://localhost:8080/services/rest/test
```