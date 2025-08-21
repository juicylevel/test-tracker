## как собирать и запускать

репа проекта https://github.com/juicylevel/test-tracker/tree/main

на компе должна быть установлена java и maven

## Сборка

в терминале переходим в папку проекта и выполняем команду:

```shell
mvn clean package
```

в папке target проекта создастся файл test-tracker-1.0-SNAPSHOT-jar-with-dependencies.jar

## Запуск

в терминале переходим в папку проекта и выполняем команду:

```shell
java -jar target/test-tracker-1.0-SNAPSHOT-jar-with-dependencies.jar
```
