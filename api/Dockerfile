FROM gradle:7.3-jdk17

COPY --chown=gradle:gradle . /home/api
WORKDIR /home/api

EXPOSE 8080

CMD gradle bootRun