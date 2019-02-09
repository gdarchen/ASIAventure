#!/bin/bash
javac -classpath /usr/share/java/junit4.jar:/usr/share/java/hamcrest-all.jar:./classes:./classestest -sourcepath ./srctest -d classestest $(find srctest -name *.java)
