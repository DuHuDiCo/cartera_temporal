FROM openjdk:11


ARG XMX=1024m
ARG PROFILE=develop

ENV XMX=$XMX
ENV PROFILE=$PROFILE


VOLUME /tmp


EXPOSE 8021

ADD ./target/cartera_temp-0.0.1-SNAPSHOT.jar cartera-temporal-microservice.jar

ENTRYPOINT java -Xmx$XMX -jar /cartera-temporal-microservice.jar --spring.profiles.active=$PROFILE 
