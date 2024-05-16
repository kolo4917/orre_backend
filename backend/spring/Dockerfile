# 베이스 이미지를 설정합니다. OpenJDK를 사용합니다.
FROM openjdk:17

# 작업 디렉토리를 생성합니다.
WORKDIR /app

# JAR 파일을 이미지 내부의 app.jar로 복사합니다.
COPY backend/spring/build/libs/orre.jar app.jar

# 필요한 디렉토리를 생성합니다.
RUN mkdir -p src/main/resources/firebase

# 비밀 파일을 복사합니다.
COPY backend/spring/src/main/resources/firebase/firebase_service_key.json /app/src/main/resources/firebase/firebase_service_key.json
COPY backend/spring/src/main/resources/application.properties /app/src/main/resources/application.properties

# 컨테이너가 시작될 때 실행할 명령을 설정합니다.
ENTRYPOINT ["java","-Duser.timezone=Asia/Seoul","-jar","/app/app.jar"]
