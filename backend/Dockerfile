# 1. 베이스 이미지 선택
FROM openjdk:17-jdk-slim

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 어플리케이션 복사
ARG JAR_PATH=./build/libs
COPY ${JAR_PATH}/*.jar /app/

# 4. 실행 명령 설정
ENTRYPOINT ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]