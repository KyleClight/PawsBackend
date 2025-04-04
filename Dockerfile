# Используем официальный образ Java
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем собранный .jar файл в контейнер
COPY build/libs/paws.jar app.jar

# Открываем порт, на котором будет работать приложение
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "pawsapplication.jar"]