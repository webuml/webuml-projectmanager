WebUml - ProjectManager
=======================

The WebUml Project Manager Backend.

Responsibilities:

- CRUD for meta-model and presentation-model elements

Requirements:
-------------

- GIT 1.9 (http://git-scm.com/downloads)
- Java 8 (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- Maven 3.0.5 (http://maven.apache.org/download.cgi)
- heroku-toolbelt 3.6.0 (https://toolbelt.heroku.com)
- MongoDB

Recommended Tools:
---------------

- jq (http://stedolan.github.io/jq/)

Build and Run Service local
===========================

Run with Maven on Command Line
------------------------------

```
mvn package
java -jar target/webuml-projectmanager.jar --spring.profiles.active=local
curl -s localhost:8082 | jq  .
```

Run in IntelliJ IDEA
-------------

1. Menu | Run | Edit Configurations...
2. \+ | "Application"
3. Name: WebUml
4. Main-Class: com.webuml.projectmanager.Application
5. Program-Arguments: --spring.profiles.active=local
6. OK

Press "Play" button in toolbar.

Deploy & Run on Production (Heroku)
==========================

Deployment
----------------

Initial login into heroku via command line tool

``heroku login``
(See https://devcenter.heroku.com/articles/heroku-command#logging-in)

``git push heroku master``
(See also https://devcenter.heroku.com/articles/git#deploying-code)

Configuration
--------------------

| Name | File
|------|-------------------------------------------|
| App                     | https://webuml-project-manager.example.org |
| Config                  | /Procfile |
| Application Properties  | src/main/resources/application-heroku.properties |

