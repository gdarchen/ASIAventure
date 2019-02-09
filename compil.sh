#!/bin/bash
javac -Xlint:unchecked -classpath ./classes -sourcepath ./src -d classes $(find src -name *.java)
