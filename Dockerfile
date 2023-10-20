# Defina a imagem base para construção
FROM ubuntu:latest AS build

# Atualize os pacotes e instale o JDK
RUN apt-get update
RUN apt-get install -y openjdk-17-jdk

# Copie o código-fonte do aplicativo para o contêiner
COPY . .

# Instale o Maven e compile o projeto
RUN apt-get install -y maven 
RUN mvn clean install

# Defina a imagem final para execução
FROM openjdk:17-jdk-slim

# Exponha a porta 8080
EXPOSE 8080

# Copie o arquivo JAR do estágio de compilação
COPY --from=build /target/todolist-0.0.1.jar app.jar

# Defina o ponto de entrada para executar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]
