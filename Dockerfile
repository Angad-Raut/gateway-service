FROM openjdk:17
EXPOSE 1995
ADD target/api-gateway.jar api-gateway.jar
ENTRYPOINT ["java","-jar","api-gateway.jar"]