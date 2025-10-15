# Etapa 1: Build con Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiamos pom.xml y resolvemos dependencias primero (para aprovechar cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el resto del código y compilamos
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final minimalista
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiamos el jar desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Puerto
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
