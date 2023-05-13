FROM openjdk:17
ENV TZ="Asia/Tbilisi"
COPY target/*.jar englishTest.jar
ENTRYPOINT ["java","-jar","/englishTest.jar"]
