#!/bin/bash
javadoc -encoding utf8 -docencoding utf8 -charset utf8 -d ./doc -sourcepath ./src $(find ./src -name *.java)
