FROM openjdk:11

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} /app/birdout/jar/birdout.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${PROFILE}", "/app/birdout/jar/birdout.jar"]