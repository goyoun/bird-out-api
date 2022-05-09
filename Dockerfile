FROM openjdk:11

ARG JAR_FILE=build/libs/*.jar

# public image 디렉토리 생성 명령어 필요(디렉토리 읽기 권한만 지정)

COPY ${JAR_FILE} /app/birdout/jar/birdout.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${PROFILE}", "/app/birdout/jar/birdout.jar"]