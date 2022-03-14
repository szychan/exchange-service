#Uses openjdk 11 image
FROM adoptopenjdk/openjdk11:alpine

#Copies previously built Jar
COPY ./build/libs/exchange-service-1.0.0.jar /home/exchange-service/build/libs/exchange-service.jar

#Executes newly built jar
CMD [ "java", "-jar","/home/exchange-service/build/libs/exchange-service.jar" ]