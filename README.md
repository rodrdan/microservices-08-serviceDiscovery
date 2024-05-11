Adding service discovery

1. Create a new project with Eureka server, Config client and Actuator dependencies
2. EurekaserverApplication.java -> add @EnableEurekaServer (app will act as service discovery agent)
3. application.properties -> application.yml
4. application.yml -> add Eureka server basic properties
5. config-server -> add eurekaserver.yml with another set of properties
6. Start config-server + eureka-server, see eureka dashboard on http://localhost:8070/