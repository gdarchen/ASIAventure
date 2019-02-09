#!/bin/bash
java -classpath /usr/share/java/junit4.jar:/usr/share/java/hamcrest-all.jar:./classes:./classestest  org.junit.runner.JUnitCore fr.insarouen.asi.prog.asiaventure.AllTests
