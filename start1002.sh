#!/usr/bin/env bash

mvn exec:java -f pom.xml  -Dexec.mainClass="peerProcess" -Dexec.args="1002"
