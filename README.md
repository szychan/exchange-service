# Getting Started

This application allows user to lookup balance of account in foreign currency (PLN -> EUR)

to run the application docker will be required 

### Guides

build project with "gradle bootJar"

run "docker-compose up"
that will stand up postgres database and service. 

Get endpoint will be available to use
localhost:8080/exchange/{account uuid}

existing account id is following "6724da0a-3109-41ae-968c-da434615f709"
