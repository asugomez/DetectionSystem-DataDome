FROM openjdk:8-jdk-alpine
#VOLUME detectionsystem.jar
#ARG JAVA_OPTS
#ENV JAVA_OPTS=$JAVA_OPTS
COPY /detectionsystem /
WORKDIR /

RUN javac detectionsystem/AccessLog.java
RUN javac detectionsystem/DNSLookUp.java
RUN javac detectionsystem/Main.java

#RUN jar -cvf datadome.jar /*.class

EXPOSE 8080
#ENTRYPOINT ["java","-cp", "Main"]
ENTRYPOINT [ "java","Main"]
