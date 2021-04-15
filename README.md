# Searcher
A simple Java Bootstrap application that searches for a string using the [levenshtein algorithm](https://en.wikipedia.org/wiki/Levenshtein_distance) and returns the count and similar words.
Go [here](https://searcher-maven.herokuapp.com/?text=Word&word=word) to try out the server

## Requirements

* Java (version >= 11) has to be available on the system
* On Unix based Operating Systems, maven is required to build the project
  * install maven with `sudo apt install maven`
## Running the Server
Steps to run the server:
* Clone the repository
* On a Unix based OS,
    * At the project folder run`mvn clean install`
* On a Windows System, 
    * At the project folder run`./mvnw clean install`
* `java -jar target/searcher-0.0.1-SNAPSHOT.jar` to start the server. Starts at http://localhost:8080/ by default.

## Sample requests
1. With parameters text and word
```
http://localhost:8080?text=Word Words Wor word&word=Word

Parameters:
text=Word Words Wor word
word=Word
```
Response:
```
{
    "frequency": 1,
    "similarWords": [
        "Words",
        "Wor",
        "word"
    ]
}
```
2. With parameter maxLevenshteinDistance. Added a feature to change the maxLevenshtein Distance which is set to 1 by default.
```
http://localhost:8080?text=Word Words Wor word&word=word&maxLevenshteinDistance=2

Parameters:
text=Word Words Wor word
word=word
maxLevenshteinDistance=2
```
Response:
```
{
    "frequency": 1,
    "similarWords": [
        "Word",
        "Words",
        "Wor"
    ]
}
```

## External Libraries used
* [Lombok](https://projectlombok.org/) - Used to simplify the code at the model package.
  The constructor, Getters and Setters are set from Lombok.

## Improvements to be made
* The structure of the project is made for a single api application.
  If we are to expand on this, we'll have to add packages for each module.
  Moving the APIs to a controller module would be a start.
* The for loops can be further optimised. There are two iterations which can be avoided in the loops. It's only present on certain conditions.