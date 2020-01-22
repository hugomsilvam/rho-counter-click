# Click counter spring boot application

Create a spring boot application for counting purposes

This is a spring boot couting exercise, it's currently deployed in heroku and can be accessed through this URL: https://intense-basin-14122.herokuapp.com/

The heroku app has Free Dyno Hours, this means if the app doesn't have any web traffic in a 30-minute period, it will sleep, so probably the first access to the service will be slower because heroku needs first to wake up the service :wink:

The counter value is being persisted in a postgresQL DB also hosted by heroku (I have used [Heroku Postgres Addon](https://elements.heroku.com/addons/heroku-postgresql) as a database as a service).

This spring boot application was built with a reactive stack, It's using [Spring WebFlux](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html), and [R2DBC](https://r2dbc.io/)

I have also used [detekt](https://github.com/arturbosch/detekt) to analyse the project code (in kotlin)

I didn't implement tests, I know :trollface: but I don't know how to implement it sorry (I don't have any kind of experience with testing) :sweat_smile:

## Run Local

To execute this service in your machine run `./gradlew build` and then `./gradlew bootRun`
