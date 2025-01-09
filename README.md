# Alpha-hack
Это наш совместный проект, который был сделан для хакатона Hack&Change, в треке от Альфа-банка.  
Ссылка на презентацию проекта : https://disk.yandex.ru/i/9V3dXiJeP55orw



# Как запустить?
# Разворачивание приложения

Следуйте этим шагам, чтобы развернуть приложение с использованием Docker и Docker Compose.

## Шаг 1: Клонирование репозиториев

Склонируйте все необходимые репозитории в **одну папку** на вашем локальном компьютере. Используйте команду `git clone`.

```bash
git clone https://github.com/makosiale/alpha-hack.git
```

## Шаг 2: Создание файла docker-compose

Перейдите в корневую директорию, которая выглядит следующим образом
```
| root
|-----backend/
|-----frontend/
|-----ml-model/
```

Пропишите команду 
```
touch docker-compose.yml
```

Откройте файл с помощью текстового редакора и вставьте следующие настройки

```
version: '3.8'
services:
  postgres-db:
    image: postgres:15-alpine
    container_name: postgres-db
    ports:
      - "9000:5432"
    environment:
      POSTGRES_DB: postgres
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 1
    restart: unless-stopped
  alpha-backend:
    build: ./backend
    image: alpha-backend
    environment:
      - SPRING_PROFILES_ACTIVE=prod
    container_name: alpha-backend
    ports:
      - "8001:8001"
    depends_on:
      - postgres-db
      - ml-model
  ml-model:
    build: ./ml-model
    image: ml-model
    container_name: ml-model
    ports:
      - "8000:8000"
  frontend:
    build: ./frontend
    image: frontend
    container_name: frontend
    ports:
      - "5173:5173"
    depends_on:
      - alpha-backend
```

## Шаг 3: Запуск приложения

После создания файла `docker-compose.yml` корневая директория выглядит так
```
| root
|-----backend/
|-----frontend/
|-----ml-model/
|-----docker-compose.yml
```

Для начала работы приложения необходимо запустить `docker-compose.yml` командой
```
docker-compose -f docker-compose.yml up
```

## Шаг 4: Тестирование

После успешного запуска контейнеров в Вашем браузере перейдите по адресу
```
http://localhost:5173
```
