# Dockerfile

# 베이스 이미지를 설정합니다. OpenJDK를 사용합니다.
FROM openjdk:17

# JAR_FILE 인자를 사용하여 JAR 파일의 경로를 받습니다.
ARG JAR_FILE=backend/spring/build/libs/*.jar

# JAR_FILE 인자로 받은 경로에 있는 JAR 파일을 이미지 내부의 app.jar로 복사합니다.
COPY ${JAR_FILE} app.jar

# 컨테이너가 시작될 때 실행할 명령을 설정합니다.
ENTRYPOINT ["java","-jar","/app.jar"]
