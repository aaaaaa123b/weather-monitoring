# Сервер мониторинга погоды

Для полноценной работы серверва необходимо:

1. Запустить докер-контейнер для СУБД PostgreSQL

```
docker-compose up -d из корневой директории проекта (weather-monitoring)
```

2. Запустить приложение monitoring-server

```
mvn spring-boot:run
```

3. Перейти на [страницу с документацией системы](http://localhost:8080/swagger-ui/index.html#/)


# Сенсор мониторинга погоды

Для полноценной работы сенсора необходимо:

1. Запустить приложение monitoring-server
2. Запустить приложение weather-sensor

```
mvn spring-boot:run
```

### Этапы разработки
1. Проектирование: 1ч
2. Реализация: 10ч
3. Тестирование: 0.5ч
4. Документирование 0.5ч