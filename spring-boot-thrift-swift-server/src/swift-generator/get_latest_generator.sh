#!/usr/bin/env bash

mvn org.apache.maven.plugins:maven-dependency-plugin:2.8:get -DremoteRepositories=central::default::http://repo1.maven.apache.org/maven2 -Dartifact=com.facebook.swift:swift-generator-cli:RELEASE:jar:standalone -Ddest=.

mvn org.apache.maven.plugins:maven-dependency-plugin:2.8:get -DremoteRepositories=central::default::http://repo1.maven.apache.org/maven2 -Dartifact=com.facebook.swift:swift2thrift-generator-cli:RELEASE:jar:standalone -Ddest=.
