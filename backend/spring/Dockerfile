# Dockerfile

# 베이스 이미지를 설정합니다. OpenJDK를 사용합니다.
FROM openjdk:17

# 작업 디렉토리를 설정합니다.
ARG JAR_FILE=build/libs/orre.jar

# 호스트의 JAR 파일을 컨테이너의 /app 디렉토리로 복사합니다.
COPY ${JAR_FILE} app.jar

# 컨테이너가 시작될 때 실행할 명령을 설정합니다.
ENTRYPOINT ["java","-jar","/app.jar"]