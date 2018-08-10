# NavigationSystem

# Requirements

Apache Tomcat 9
Redis 2.0

# Approach used

Map is represented as a graph characterized by a set of nodes, each node having a set of edges of custom types. I have used Dijiktra's shortest path algorithm modified to find shortest path between two nodes.

# How to deploy

1) Run : mvn clean install
2) Stop tomcat server
3) copy war file into webapps folder of tomcat
4) Start tomcat server on default port
5) Start Redis server on default port (or change the application.properties file to the desired port)

# How to test

# Controller unit test

 mvn '-Dtest=com.microsoft.navigation.controller.*Test' test

# Integration test

 mvn '-Dtest=com.microsoft.navigation.integration.*Test' test

 # Memory performance

 mvn '-Dtest=com.microsoft.navigation.performance.*Test' test






