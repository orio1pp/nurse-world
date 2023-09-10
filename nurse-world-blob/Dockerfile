FROM quay.io/quarkus/quarkus-micro-image:2.0
VOLUME /tmp
EXPOSE 80
COPY target/nurse-world-blob-1.0-SNAPSHOT.jar /app
CMD ["/app", "-Dquarkus.http.host=0.0.0.0", "-Dquarkus.http.port=80"]