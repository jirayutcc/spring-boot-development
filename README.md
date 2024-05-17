## spring boot development

#### Application use `Multiple Data Sources`. Install Data Source via `Docker`

Mariadb

```
docker run --name mariadb -e MYSQL_ROOT_PASSWORD=P@ssw0rd -p 3306:3306 -d mariadb:latest
```

PostgresSql

```
docker run --name postgressql -e POSTGRES_PASSWORD=P@ssw0rd -p 5432:5432 -d postgres:16
```

#### Application running default port`http://localhost:9000`

Check application is running

Method: GET

```
http://localhost:9000/application/status
```

Check application version

Method: GET

```
http://localhost:9000/application/version
```

Redis
```
docker run --name redis -d redis:latest
```
