# travel-planner
## _Backend service of travel planner application_
### _Entity diagram_
![entity diagram](./src/doc/ER.jpg)

### _How to run_
- clone the source code or download
- go to the downloaded folder
- run ./mvnw spring-boot:run


### _Cache configuration_
- Get weather API calls are cashed using ehcache and spring cache (src/main/resources/ehcache.xml).
- Expiration is configured to 1 hour
  ![ehcache config](./src/doc/eh_cache.png)
  ![spring_cache config](./src/doc/spring_cache.png)



### _Database configuration_

- H2 is used with Hikari connection pool

### _Meta data_

- To implement itinerary save screen, to populate the city dropdown, 
  there is a xml file containing cities all cities.
  (src/main/resources/city_list.json)

  ![clity_list](./src/doc/city.png)

### _Verify the installation_
- [Swagger UI link](http://localhost:8080/swagger-ui/#) 