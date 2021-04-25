# Introduction
Tasks required for LPG Technology Assessment - Software Engineer in Test

## API testing:
Sample swagger available here: https://leisurepassgroup.github.io/SDET-interview/  
Please write tests for the following:
1. Get all cities
1. Get all attractions for a city (New York)
1. Get all attractions for a city (New York) where the attraction type is ‘MUSEUM’ and ordered by rating descending

### Running tests
In the terminal run the tests using ```gradle cucumber```

### Choices
*Cucumber BDD* was used as it is a common form of writing tests that is easily readable. If a pipeline is used 
then there are plugins available to read cucumber reports and display them visually. If the pipeline tests also include 
browser tests, then it is likely that they are already using Cucumber and so the same pipeline config can be used for 
both api and browser testing.

*Java* was used as it is a fairly common testing language but Javascript or Python are also viable options depending on 
the tech team knowledge. Rest-Assured is the most common HTTP request library that simplifies the API request and 
response creation and manipulation that does not have a lot of boilerplate to worry about.

*Gradle* was used as it is a fairly common build tool that is a little simpler to use than Maven which is also commonly 
used.  Jenkins is able to use the gradle build specification if the tests are added to the Jenkins pipeline.


### Future changes
* Extract the assertions into separate classes so that they are single line code making the step definitions easier 
to read
* Add an environments variable file to store the rootUrl for the microservices and any other useful constants
* Add schema verification after the response status code step to ensure the shape of the response is correct
* Discuss adding journeys to the tests - API tests that mimic the calls an E2E test would make that cross multiple API
* Create page objects for each micro-service so that the end points and behaviour can be maintained in a central class
* Use endpoint models to convert the response into Java objects to simplify manipulation for assertions