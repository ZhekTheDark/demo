### дойти до файла
cd <путь до проекта>

### вывод в консоль всех контейнеров
docker ps

### поднимает все штуки описанные в docker-compose.yml, опция -d (Detached mode: run command in the background) для запуска в бекграунде
docker-compose up -d

### выполняет команду bash в контейнере ubuntu-seminars, -i (Keep STDIN open even if not attached) -t (Allocate a pseudo-TTY), обе опции для комфортной работы  
docker exec -it ubuntu-seminars bash

### выходим и выключаем контейнер
exit
docker-compose stop

### чтобы полностью пересоздать контейнер
docker-compose down
docker-compose up -d
