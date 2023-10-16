FROM ubuntu:latest AS build

RUN apt update
RUN apt install openjdk-17-jdk -y

COPY . .

RUN apt install maven -y
RUN mvn clean install

EXPOSE 8080

COPY --from=build /target/java-to-do-list-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]