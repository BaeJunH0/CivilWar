FROM openjdk:17-alpine

ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar
ARG KEY
ARG TOKEN_EXPIRE_LENGTH

# ENV로 설정하여 이후에 사용 가능
ENV KEY=$KEY
ENV TOKEN_EXPIRE_LENGTH=$TOKEN_EXPIRE_LENGTH
ENV API_KEY=$API_KEY
ENV SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL
ENV SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME
ENV SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD

COPY ${JAR_FILE} my-project.jar

ENTRYPOINT ["java","-jar","my-project.jar"]

RUN ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime