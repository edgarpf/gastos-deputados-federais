FROM java:8-jdk-alpine

COPY ./target/deputado-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch deputado-0.0.1-SNAPSHOT.jar'

EXPOSE 8098

ENTRYPOINT ["java","-jar","deputado-0.0.1-SNAPSHOT.jar"]  