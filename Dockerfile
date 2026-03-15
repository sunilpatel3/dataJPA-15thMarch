# ---------- Build Stage ----------
FROM eclipse-temurin:25-jdk AS build

# Install Maven manually in case CI/CD base image doesn’t include it
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy pom.xml first for dependency caching
COPY pom.xml .

# Download dependencies
RUN mvn -B dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application (skip tests)
RUN mvn -B clean package -DskipTests
