FROM openjdk:8
COPY build/libs/laminuta.jar /app/
ENTRYPOINT ["java", "-jar", "/app/laminuta.jar"]
