FROM cantara/alpine-openjdk-jdk8
COPY cweiy-webmagic.jar /cweiy-webmagic.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/cweiy-webmagic.jar"]