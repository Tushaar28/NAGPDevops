FROM tomcat
WORKDIR devops
COPY target/devops.war .
RUN rm -rf ROOT && mv devops.war ROOT.war
ENTRYPOINT ["sh", "/usr/local/tomcat/bin/startup.sh"]
