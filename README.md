**SERVICE DISCOVERY W/ SPRING CLOUD NETFLIX (EUREKA)**

**Adding service registry**

1. Create a new project with Eureka server, Config client and Actuator dependencies
2. EurekaserverApplication.java -> add @EnableEurekaServer (app will act as service discovery agent)
3. application.properties -> application.yml
4. application.yml -> add Eureka server basic properties
5. config-server -> add eurekaserver.yml with another set of properties
6. MS pom.xml -> add Eureka client dependency
7. MS application.yml -> add Eureka connection properties + info

Eureka endpoints:
    http://localhost:8070/ (dashboard)
    http://localhost:8070/eureka/apps (services details XML)
    http://localhost:8080/actuator/shutdown (MS shutdown - use POST method)

**Allowing MS to communicate using Open Feign Client**

1. MS pom.xml -> add spring-cloud-starter-openfeign dependency
2. MS main class -> add @EnableFeignClients
3. client MS -> create @FeignClient interface + 
4. client MS -> create backing service DTO, CustomerController, CustomerDetailsDto, ICustomerService, 
   CustomerServiceImpl (where we use the feign clients to load information from other MS)