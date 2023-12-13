FROM bellsoft/liberica-runtime-container:jre-21-slim-musl

COPY backend.jar app.jar

EXPOSE 80

ENTRYPOINT ["java","-jar","/app.jar"]