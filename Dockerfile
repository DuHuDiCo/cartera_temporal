FROM openjdk:11


ARG XMX=100m
ARG PROFILE=develop

ENV XMX=$XMX
ENV PROFILE=$PROFILE

RUN apk --no-cache add tzdata && \
    cp /usr/share/zoneinfo/America/Bogota  /etc/localtime && \
    echo "America/Bogota" > /etc/timezone && \
    apk del tzdata

VOLUME /tmp


EXPOSE 8021

ADD ./target/cartera-temporal-microservice-0.0.1-SNAPSHOT.jar cartera-temporal-microservice.jar

ENTRYPOINT java -Xmx$XMX -jar /cartera-temporal-microservice.jar --spring.profiles.active=$PROFILE 
