FROM maven:3.6.1-jdk-8-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY ./src /workspace/src
RUN mvn -f pom.xml clean package -P remote

FROM registry.access.redhat.com/ubi8/openjdk-17:1.14

ENV LANGUAGE='en_US:en'


# We make four distinct layers so if there are application changes the library layers can be re-used
COPY --chown=185 --from=build /workspace/target/quarkus-app/lib/ /deployments/lib/
COPY --chown=185 --from=build /workspace/target/quarkus-app/*.jar /deployments/
COPY --chown=185 --from=build /workspace/target/quarkus-app/app/ /deployments/app/
COPY --chown=185 --from=build /workspace/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"
