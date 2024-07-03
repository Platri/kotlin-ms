# Schritt 1: Wählen Sie ein geeignetes Basis-Image mit Java
FROM openjdk:8-jdk-slim AS build

# Schritt 2: Installieren Sie Gradle
ENV GRADLE_VERSION=8.4
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip && \
    unzip gradle-${GRADLE_VERSION}-bin.zip -d /opt && \
    ln -s /opt/gradle-${GRADLE_VERSION} /opt/gradle && \
    echo "export PATH=$PATH:/opt/gradle/bin" >> ~/.bashrc

# Schritt 3: Erstellen Sie das Arbeitsverzeichnis
WORKDIR /app

# Schritt 4: Kopieren Sie die Projektdateien
COPY ./ .

# Schritt 5: Führen Sie Gradle-Befehle aus, um das Projekt zu bauen
ENV PATH=/opt/gradle/bin:$PATH
RUN gradle clean build --no-daemon

# Schritt 6: Erstellen Sie das endgültige Image
FROM openjdk:8-jdk-slim

# Schritt 7: Erstellen Sie das Arbeitsverzeichnis
WORKDIR /app

# Schritt 8: Kopieren Sie die JAR-Datei aus dem Build-Image
COPY --from=build /app/build/libs/kotlin-ms.jar /app/kotlin-ms.jar

# Schritt 9: Setzen der Umgebungsvariablen und Starten der Anwendung
ENV RABBITMQ_HOST=your-rabbitmq-host
ENV RABBITMQ_PORT=5672
ENV RABBITMQ_USERNAME=guest
ENV RABBITMQ_PASSWORD=guest
ENV RABBITMQ_QUEUE_INPUT=numbers
ENV RABBITMQ_QUEUE_OUTPUT=results

CMD ["java", "-jar", "kotlin-ms.jar"]

# Hinweis: Ersetzen Sie "your-kotlin-app-name" durch den tatsächlichen Namen Ihres JAR-Pakets.
