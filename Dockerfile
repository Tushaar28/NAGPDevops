FROM openjdk:8-jre-alpine
WORKDIR devops
COPY target/devops.war .
RUN rm -rf ROOT && mv devops.war ROOT.war
ENTRYPOINT ["/usr/bin/java", "-jar", "-Dspring.profiles.active=test", "/devops.war"]
