## About The Films Project

![Main Page Screen Shot][main-page-screenshot]

Project contains data about directors and theirs films.
Information search is carried out by director's last name, film's release date.

### Built With

* [![Kotlin][Kotlin]][kotlin-url]
* [![Spring Boot][Spring_Boot]][spring-url]
* [![Flyway][Flyway]][flyway-url]
* [![PostgreSQL][postgres]][postgres-url]
* [![JavaScript][JS]][js-url]
* [![React][React.js]][React-url]
* [![Boostrap][Boostrap]][boostrap-url]
* [![Docker][Docker]][docker-url]
* [![Gradle][Gradle]][gradle-url]

## Getting Started

For starting application prefer docker-compose, but you also can use gradle.
</br>
Backend side coverage by unit and integration tests.
</br>

### Gradle Instruction

For starts project use commands

````
gradle clean
````

````
gradle bootRun
````

### Docker Instruction

For generated gradle build folder

````
gradle clean build jar
````

Build and Run Docker Images

````
docker-compose up
````

Rebuild Docker Images

````
docker-compose build
````

### Testing

For looking tests coverage you can use gradle command(Report is in /build/reports/jacoco/test/html/index.html)

````
gradle jacocoTestReport
````

For looking frontend tests coverage in frontend folder start command

````
npm test -- --coverage --watchAll=false
````        

Local:

+ http://localhost:8080/
+ http://localhost:8080/films
+ http://localhost:8080/swagger-ui/index.html

## Contact

[![LinkedIn][linkedin-shield]][linkedin-url]
[![Gmail][gmail-shield]][gmail-url]

<!-- MARKDOWN LINKS & IMAGES -->

[main-page-screenshot]: readme_image/main_page.PNG

[Kotlin]: https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white

[kotlin-url]: https://kotlinlang.org/docs/home.html

[Spring_Boot]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white

[spring-url]: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/

[Flyway]: https://img.shields.io/badge/-Flyway-red?style=for-the-badge

[flyway-url]: https://flywaydb.org/documentation/

[postgres]: https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white

[postgres-url]: https://www.postgresql.org/

[JS]: https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black

[js-url]: https://developer.mozilla.org/en-US/docs/Web/JavaScript

[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB

[React-url]: https://reactjs.org/

[Docker]: https://img.shields.io/badge/-Docker-fff?style=for-the-badge&logo=Docker

[docker-url]: https://docs.docker.com/

[Boostrap]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white

[boostrap-url]: https://react-bootstrap.github.io/getting-started/introduction

[Gradle]: https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white

[gradle-url]: https://docs.gradle.org/current/userguide/userguide.html

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

[linkedin-url]: https://www.linkedin.com/in/kkarpekina

[gmail-shield]: https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white

[gmail-url]: mailto:feliks.cat37@gmail.com