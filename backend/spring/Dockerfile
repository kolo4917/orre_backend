# 베이스 이미지를 설정합니다. OpenJDK를 사용합니다.
FROM openjdk:17

# JAR_FILE 인자를 사용하여 JAR 파일의 경로를 받습니다.
ARG JAR_FILE=backend/spring/build/libs/*.jar

# 환경 변수로 중요한 파일의 내용을 받습니다.
ARG FIREBASE_SERVICE_KEY
ARG APPLICATION_PROPERTIES

# 작업 디렉토리를 생성합니다.
WORKDIR /app

# JAR_FILE 인자로 받은 경로에 있는 JAR 파일을 이미지 내부의 app.jar로 복사합니다.
COPY ${JAR_FILE} app.jar

# 필요한 디렉토리를 생성합니다.
RUN mkdir -p src/main/resources/firebase

# 환경 변수로 받은 내용을 파일로 저장합니다.
RUN echo "$FIREBASE_SERVICE_KEY" > src/main/resources/firebase/firebase_service_key.json
RUN echo "$APPLICATION_PROPERTIES" > src/main/resources/application.properties

# 컨테이너가 시작될 때 실행할 명령을 설정합니다.
ENTRYPOINT ["java","-Duser.timezone=Asia/Seoul","-jar","/app.jar"]
