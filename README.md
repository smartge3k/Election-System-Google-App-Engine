


-The Cost Vote button on admin panel is only for how the front end looks like, you cannot vote without a valid token (link).
- Email Quota given by the google appengine is only 10 emails per day, please upload the text file accordingly.
- Please Visit the following link for access: https://usman-264309.appspot.com/
- Please accept the invitation link sent to dsam.dsg@gmail.com to able to use the website as admin








HelloWorld for App Engine Standard (Java 8)
============================

This sample demonstrates how to deploy an application on Google App Engine.

See the [Google App Engine standard environment documentation][ae-docs] for more
detailed instructions.

[ae-docs]: https://cloud.google.com/appengine/docs/java/


* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven](https://maven.apache.org/download.cgi) (at least 3.5)
* [Gradle](https://gradle.org/gradle-download/) (optional)
* [Google Cloud SDK](https://cloud.google.com/sdk/) (aka gcloud)

## Setup

• Download and initialize the [Cloud SDK](https://cloud.google.com/sdk/)

    gcloud init

* Create an App Engine app within the current Google Cloud Project

    gcloud app create

## Maven
### Running locally

    mvn appengine:run

To use vist: http://localhost:8080/

### Deploying

    mvn appengine:deploy

To use vist:  https://YOUR-PROJECT-ID.appspot.com

## Gradle
### Running locally

    gradle appengineRun

If you do not have gradle installed, you can run using `./gradlew appengineRun`.

To use vist: http://localhost:8080/

### Deploying

    gradle appengineDeploy

If you do not have gradle installed, you can deploy using `./gradlew appengineDeploy`.

To use vist:  https://YOUR-PROJECT-ID.appspot.com

## Testing

    mvn verify

 or

    gradle test

As you add / modify the source code (`src/main/java/...`) it's very useful to add [unit testing](https://cloud.google.com/appengine/docs/java/tools/localunittesting)
to (`src/main/test/...`).  The following resources are quite useful:

* [Junit4](http://junit.org/junit4/)
* [Mockito](http://mockito.org/)
* [Truth](http://google.github.io/truth/)
