
FROM openjdk:7

RUN mkdir -p /home/log_percentiles 
COPY build/distributions/log_percentiles.tar /home/log_percentiles
WORKDIR /home/log_percentiles
RUN tar -xvf log_percentiles.tar
WORKDIR /home/log_percentiles
CMD ["log_percentiles/bin/log_percentiles"] 