FROM openjdk:11.0.6-slim

COPY /detectionsystem/detectionsystem /detectionsystem/detectionsystem

RUN javac /detectionsystem/detectionsystem/*.java

COPY /detectionsystem /detectionsystem

EXPOSE 8080

WORKDIR /detectionsystem

ENTRYPOINT java detectionsystem.Main   
