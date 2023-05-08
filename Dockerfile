FROM openjdk:17.0.2-jdk-slim-buster
COPY target/*jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx512m", "-Duser.timezone=Asia/Bangkok", "-XshowSettings:vm -XX:+PrintCommandLineFlags -XX:+UnlockExperimentalVMOptions -XX:+UseZGC -Xlog:gc", "-jar", "app.jar"]
