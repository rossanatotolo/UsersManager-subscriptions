# UsersManager-subscriptions - микросервисное приложение для управления пользователями и их подписками на сервисы.

#### Используемые инструменты: 
### Java 17, Spring Boot 3, Spring Data JPA, PostgreSQL, REST API, Lombok, Docker. 

Запуск настроен через Docker. Приложение и база данных PostgreSQL запускаются в отдельном Docker-контейнере каждый и их общение происходит через REST.

### Эндпоинты:
##### 1. USERS:
- POST /users - добавление пользователя;
- PUT /users/{id} - обновление данных пользователя;
- GET /users/{id} - получение данных пользователя;
- DELETE /users/{id} - удаление пользователя.
- POST /users/{id}/subscriptions/{sub_id} - добавление пользователем подписки;
- GET /users/{id}/subscriptions - получение списка подписок пользователя;
- DELETE /users/{id}/subscriptions/{sub_id} - удаление пользователм подписки.

##### 2. SUBSCRIPTIONS:
- POST /subscriptions - добавление подписки;
- GET /subscriptions/top - получение ТОП-3 популярных подписок;
- DELETE /subscriptions/{id} - удаление подписки;
