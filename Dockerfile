FROM docker.io/java:8
ADD build/libs/myThriftClient-1.0.jar /opt/mollydocker/
ADD clientstartup.sh /opt/mollydocker/
RUN chmod +x /opt/mollydocker/clientstartup.sh

EXPOSE 18080
WORKDIR /opt/mollydocker/

CMD ["sh", "-c", "/opt/mollydocker/clientstartup.sh"]
