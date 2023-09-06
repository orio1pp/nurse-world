FROM quay.io/quarkus/quarkus-micro-image:2.0
WORKDIR /work/
COPY target/nurse-world-jwt-1.0-SNAPSHOT /app
EXPOSE 80
CMD ["/app", "-Dquarkus.http.host=0.0.0.0", "-Dquarkus.http.port=80"]