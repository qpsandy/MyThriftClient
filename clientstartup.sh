#!/bin/bash
echo $CS_PORT_18443_TCP_ADDR
echo $CS_PORT_18443_TCP_PORT
java -jar -Dserver.ip=$CS_PORT_18443_TCP_ADDR -Dserver.port=$CS_PORT_18443_TCP_PORT myThriftClient-1.0.jar
