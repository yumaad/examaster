# Examaster

## 📌 О проекте
Spring Boot приложение для генерации экзаменационных вопросов.

Приложение доступно по адресу: http://localhost:8080

## 📌 Основные методы
| Метод   | Endpoint                | Описание                     | Пример запроса                                                                 |
|---------|-------------------------|-----------------------------|-------------------------------------------------------------------------------|
| `POST`  | `/exam/java/add`        | Добавить новый вопрос       | `curl -X POST "http://localhost:8080/exam/java/add" -d "question=Текст" -d "answer=Ответ"` |
| `DELETE`| `/exam/java/remove`     | Удалить существующий вопрос | `curl -X DELETE "http://localhost:8080/exam/java/remove" -d "question=Текст" -d "answer=Ответ"` |
| `GET`   | `/exam/java`            | Получить все вопросы        | `curl "http://localhost:8080/exam/java"`                                      |
| `GET`   | `/exam/get/{amount}`    | Получить N случайных вопросов | `curl "http://localhost:8080/exam/get/3"`                                    |
