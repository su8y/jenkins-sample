FROM openjdk:17-jdk

CMD ["./gradlew", "clean", "build"]

VOLUME /tmp
ARG JAR_FILE=./build/libs/be-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080
# 시스템 진입점 정의
ENTRYPOINT ["java","-jar","/app.jar"]