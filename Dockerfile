FROM eclipse-temurin:25.0.2_10-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

COPY src/main/resources/notes.json /data/notes.json
COPY src/main/resources/users.json /data/users.json

ENV USER_NAME=Docker_Malak_Yassin
ENV ID=Docker_55_3546

EXPOSE 8080

ENTRYPOINT ["java","-jar","target/*.jar"]