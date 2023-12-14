FROM openjdk:17-jdk-slim

RUN curl -s https://code.quarkus.io/d?g=io.quarkus&a=quarkus-bom&v=LATEST -o quarkus.zip
RUN unzip quarkus.zip -d /usr/src/ && \
    rm quarkus.zip && \
    mv /usr/src/quarkus-* /usr/src/quarkus
EXPOSE 80

WORKDIR /usr/src/app
COPY target/nurse-world-blob-1.0-SNAPSHOT.jar ./application.jar
CMD ["java", "-jar", "application.jar"]