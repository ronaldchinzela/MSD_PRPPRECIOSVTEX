FROM adoptopenjdk/openjdk16:jre-16.0.1_9-ubuntu as FINAL-CONTAINER

RUN apt-get update -y && apt-get upgrade -y \
 && apt-get clean \
 && rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME=/usr/java/openjdk-16
ENV TZ=America/Mexico_City

COPY /security/* $JAVA_HOME/lib/security/

RUN keytool -importcert -noprompt -trustcacerts -alias gobapisdev -file $JAVA_HOME/lib/security/gobapis.cer -storepass changeit -keystore $JAVA_HOME/lib/security/cacerts \
&& keytool -importcert -noprompt -trustcacerts -alias gobapisprod -file $JAVA_HOME/lib/security/security_ca-chain.cert.pem -storepass changeit -keystore $JAVA_HOME/lib/security/cacerts \
&& keytool -importcert -noprompt -trustcacerts -alias rkedev -file $JAVA_HOME/lib/security/rkedev.cer -storepass changeit -keystore $JAVA_HOME/lib/security/cacerts \
&& keytool -importcert -noprompt -trustcacerts -alias rkeprod -file $JAVA_HOME/lib/security/rkeprod.cer -storepass changeit -keystore $JAVA_HOME/lib/security/cacerts \
&& keytool -importcert -noprompt -trustcacerts -alias ddpdev -file $JAVA_HOME/lib/security/serv.cer -storepass changeit -keystore $JAVA_HOME/lib/security/cacerts \
&& keytool -importcert -noprompt -trustcacerts -alias ddpprod -file $JAVA_HOME/lib/security/ddpprod.pem -storepass changeit -keystore $JAVA_HOME/lib/security/cacerts \
&& keytool -importcert -noprompt -trustcacerts -alias ouiprod -file $JAVA_HOME/lib/security/ouiprod.cer -storepass changeit -keystore $JAVA_HOME/lib/security/cacerts \
&& keytool -importcert -noprompt -trustcacerts -alias sap1 -file $JAVA_HOME/lib/security/sap1.cer -storepass changeit -keystore $JAVA_HOME/lib/security/cacerts \
&& keytool -importcert -noprompt -trustcacerts -alias sap2 -file $JAVA_HOME/lib/security/sap2.cer -storepass changeit -keystore $JAVA_HOME/lib/security/cacerts \
&& keytool -importcert -noprompt -trustcacerts -alias lokhubcomdev -file $JAVA_HOME/lib/security/lok-hub.com-dev.cer -storepass changeit -keystore $JAVA_HOME/lib/security/cacerts \
&& keytool -importcert -noprompt -trustcacerts -alias lokhubcomprod -file $JAVA_HOME/lib/security/lok-hub.com-prod.cer -storepass changeit -keystore $JAVA_HOME/lib/security/cacerts \
&& ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar"]