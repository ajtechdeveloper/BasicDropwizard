FROM openjdk:8-jdk

COPY src/main/resources/basicdropwizard.yml /data/BasicDropwizard/basicdropwizard.yml  
COPY /target/BasicDropwizard-1.0.0.jar /data/BasicDropwizard/BasicDropwizard-1.0.0.jar

WORKDIR /data/BasicDropwizard

RUN java -version

CMD ["java","-jar","BasicDropwizard-1.0.0.jar","basicdropwizard.yml"]

EXPOSE 4000-4001 