# Hi Master platform:

##How to build:

1) Build locally the core-module by maven:

   https://github.com/shayhutdinovilnaz/himaster-core

   Execute cmd locally from the project repository:

   `mvn clean install`

2) Build locally the platform-app by maven:

   Launch locally from the project repository:

   `mvn clean install`

## How to launch:

1) Launch MYSSQL DB using docker:
   `docker run -e MYSQL_ROOT_PASSWORD=Qwerty-2 -d -p 3306:3306 mysql:8.0.27`

2) Launch application:

   from IDEA :
   ee.himaster.platform.web.application.PlatformServiceApplication

   or

   from using jar file:

   platform-web-application/target/platform-web-application-1.0-SNAPSHOT.jar


## Modules:
   **platform-api** - the module is used for the generation the API and DTO models via OpenApi Contract.
   
   **platform-service** - the module contains all business logic and managing model objects in DB
   
   **platform-facade** - the proxy module between DTO objects and business model objects
   
   **platform-web-application** - the starter of the web application.
   
   **platform-integration-test** - the module contains integration tests for platform-app.

## Example of the queries:


   1) List of cities in country:

   `curl -X GET --location "http://localhost:8081/platform/api/v1/siteconfig/city/all?countryIsoCode=EST" \
   -H "Locale-code: EST_EN"`
   
   2) List of languages which available in a country:

   `curl -X GET --location "http://localhost:8081/platform/api/v1/siteconfig/language/all?countryIsoCode=EST" \
   -H "Locale-code: EST_EN"`
   
   3) The locale for region and language. If a language are not exist then return default locale for region:
   
   `curl -X GET --location "http://localhost:8081/platform/api/v1/siteconfig/locale?regionCode=EST" \
   -H "Locale-code: EST_EN"`
   
   4) Return category by id:
   
   `curl -X GET --location "http://localhost:8081/platform/api/v1/category/1" \
   -H "Locale-code: EST_EN"`
   
   5) Return list of root categories:
   
   `curl -X GET --location "http://localhost:8081/platform/api/v1/category/roots" \
   -H "Locale-code: EST_EN"
`







   
   
