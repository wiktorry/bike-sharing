FROM maven:3.9.9-amazoncorretto-17-alpine
COPY . /bike-sharing
WORKDIR /bike-sharing
RUN mvn package -DskipTests
ENTRYPOINT ["java","-jar","/bike-sharing/target/bike-sharing.jar"]