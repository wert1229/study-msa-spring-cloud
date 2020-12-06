# 유데미 강의 실습
## Master Microservices with Spring Boot and Spring Cloud
### Spring Boot
* 인프런 [Spring Boot를 이용한 RESTful Web Service 개발 by Dowon Lee](https://github.com/wert1229/study-springboot-rest-service)
* 상기한 강의에서 강의 구조와 코드 샘플을 가져다 썼는지 내용이 90% 유사하므로 생략

### Spring Cloud
* Microservices
    * REST
    * Small Well Chosen Deployable Units
    * Cloud Enabled  
* Challenge
    * Bounded Context
    * Configuration manage
    * Dynamic Scale up and down
    * Visibility
    * Pack of Cards
 * Ports
 
|     Application       |     Port          |
| ------------- | ------------- |
| Limits Service | 8080, 8081, ... |
| Spring Cloud Config Server | 8888 |
|  |  |
| Currency Exchange Service | 8000, 8001, 8002, ..  |
| Currency Conversion Service | 8100, 8101, 8102, ... |
| Netflix Eureka Naming Server | 8761 |
| Netflix Zuul API Gateway Server | 8765 |
| Zipkin Distributed Tracing Server | 9411 |


* 설정 관리
    * Services -> Config Server -> Git


