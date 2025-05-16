# Usa una imagen de Maven para compilar el proyecto
FROM maven:3.8.4-openjdk-17 AS build

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo POM y descarga las dependencias
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copia el resto del código del proyecto
COPY src ./src

# Compila el proyecto y empaca en un archivo JAR
RUN mvn package -DskipTests

# Usa una imagen de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR desde la imagen de compilación
COPY --from=build /app/target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 5000

# Define el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]