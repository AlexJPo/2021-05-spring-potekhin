## Переписать приложение для хранения книг на ORM

### Цель:
полноценно работать с JPA + Hibernate для подключения к реляционным БД посредством ORM-фреймворка Результат: Высокоуровневое приложение с JPA-маппингом сущностей 

### Описание задание:
Домашнее задание выполняется переписыванием предыдущего на JPA.

### Требования: 
- Использовать JPA, Hibernate только в качестве JPA-провайдера.
- Для решения проблемы N+1 можно использовать специфические для Hibernate аннотации @Fetch и @BatchSize.
- Добавить сущность "комментария к книге", реализовать CRUD для новой сущности.
- Покрыть репозитории тестами, используя H2 базу данных и соответствующий H2 Hibernate-диалект для тестов.
- Не забудьте отключить DDL через Hibernate
- @Transactional рекомендуется ставить только на методы сервиса.

Это домашнее задание будет использоваться в качестве основы для других ДЗ Данная работа не засчитывает предыдущую!
