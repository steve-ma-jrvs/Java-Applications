# Overview

This project includes three project: Twitter CLI App, Java Grep App and Java JDBC App.

1. [Twitter CLI App](Twitter CLI App)
2. [Java Grep App](Java Grep App)
3. [Java JDBC App](Java JDBC App)

# Twitter CLI App

## Introduction

* A Twitter CLI App is a twitter command line API to create, read and delete your tweet. It covers Java JDBC, DAO, DTO, service implement, business logic, MAVEN, Spring Framework, Spring Boot, etc..

## Usage

#### Simplified Tweet Object

In this app, we will only focus on some particular fields in the tweet object.  

Tweet object docks [link](https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/tweet-object)

```
//Simplified Tweet Object
{
   "created_at":"Mon Feb 18 21:24:39 +0000 2019",
   "id":1097607853932564480,
   "id_str":"1097607853932564480",
   "text":"test with loc223",
   "entities":{"hashtags",
               "user_mentions"},
   "coordinates",
   "retweet_count",
   "favorite_count",
   "favorited",
   "retweeted"
}
```

#### Create a tweet on your timeline

```
USAGE: TwitterCLI post "tweet_text" "latitude:longitude"

Description: create a tweet with a geotag and output the created tweet object (simplified version) in JSON format.
```

* `Tweet_text` - tweet_text cannot exceed 140 UTF-8 encoded characters.
* `Latitude:longitude` - Geo location

#### Read/Show a tweet by ID

```
USAGE: TwitterCLI show tweet_id [field1, field2]

Description: Lookup a tweet by ID and print the tweet object in JSON format. Show all fields in JSON document if [field1,fields2] is empty. Otherwise, only show user specified [fields] in the JSON document.
```

* `Tweet_id` - same as id_str in the tweet object
* `[field1, field2]` - (Optional) A common-separated list of top-level fields from the tweet object (similar to SELECT clause in SQL)

#### Delete a tweet by tweet ID

```
USAGE: TwitterCLI delete tweet_ids

Description: Delete a list of tweets by id and print deleted tweet object
```

* `Tweet_ids` - A comma-separated list of tweets.

## Design and Implementaion

| Component  | Description                                                  |
| :--------- | :----------------------------------------------------------- |
| HttpHelper | Making HTTP requests (GET/PUT/DELETE) and handle auth        |
| Dao        | Data Access Object which handles tweet object (Dao depends on HttpHelper) |
| Service    | Business logic. In other words, it depends on Dao, and manipulate twitter object according to application requirements (e.g. select certain fields when showing tweet object) |
| Runner     | Parse user CLI inputs and then calls the corresponding service methods |
| Main       | Create above components and start applications               |

![p3](https://github.com/steve-ma-jrvs/Java-Applications/blob/master/images/Twitter.png)

#### Implement `HttpHelper`

```java
package ca.jrvs.apps.twitter.dao.helper;
public interface HttpHelper {
  HttpResponse httpPost(URI uri);
  HttpResponse httpPost(URI uri, StringEntity stringEntity);
  HttpResponse httpGet(URI uri);
}
```

* Create a `HttpHelper` interface in order to implement it with different HttpClient vendors and auth mechanisms. And in this app, we use `ApacheHttphelper` class.

#### Implement `TwitterRestDao`

```java
package ca.jrvs.apps.twitter.dao;
public interface CrdDao<T, ID> {
  T create(T entity);
  T findById(ID id);
  T deleteById(ID id);
}
```

* Create a `CrdDao` interface 
* Implement it with `TwitterRestDao` to approach each method with following logic:
  * Construct URI
  * Execute HTTP request
  * Validate response and deser response to `Tweet` object

* Testing
  * Unit Testing `TwitterRestDaoUnitTest`
  * Integration Test `TwitterRestDaoIntTest`

#### Implement `TwitterService`

```java
package ca.jrvs.apps.twitter.service;
import ca.jrvs.apps.twitter.dto.Tweet;
import java.util.List;
public interface TwitterService {
  Tweet postTweet(String text, Double latitude, Double longitude);
  Tweet showTweet(String id, String[] fields);
  List<Tweet> deleteTweets(String[] ids);
}
```

* Create a `TwitterService` interface
* Implement it with `TwitterServiceImp` to approach each method
* It depands on dao and validate id or tweet before using

#### Implement `TwitterCLI`

* Implement `TwitterCLIRunner` to validate the input arguments
* It depends on `service`
* Run `Main` in `TwitterCLI` and call `TwitterCLIRunner`

## Spring Framework

#### Traditional Dependency Management

* Diagram for `TwitterCLI` app

* Implement `ca.jrvs.apps.twitter.TwitterCLIApp#main`

#### The IoC Container - Spring Framework

* Setup Spring Framework with Maven
* **Spring Bean Approach**
  * @Configuration
  * @Bean
    * Manually create @Bean for each components

* **Spring Annotation Approach**

  * Use in-line @Component annotations to add classes to IoC
  * Annotate the class contructor with `@Autowired`
  * In this app,

  | Annotation  | Class                                  |
  | ----------- | -------------------------------------- |
  | @Component  | `TwitterCLIRunner`, `ApacheHttpHelper` |
  | @Repository | `TwitterRestDao`                       |
  | @Service    | `TwitterServiceIMP`                    |

* **SpringBoot Approach**

![p4](https://github.com/steve-ma-jrvs/Java-Applications/blob/master/images/Screen%20Shot%202019-07-10%20at%204.05.44%20PM.png)

  * Create a properties file `java_apps/src/main/resources/application.properties` and write

    ```
    logging.level.root=INFO
    logging.level.org.springframework.beans.factory=DEBUG
    ```

  * `@SpringBootApplication` is a convenience annotation that adds all of the following:

    * `@Configuration`
    * `@EnableAutoConfiguration`
    * `@ComponentScan`

  * In this app, we annotate the `TwitterCLISpringBoot` class with `@SpringBootApplication(scanBasePackages = "ca.jrvs.apps.twitter")`

## Enhancements and Issues

* Could only search tweet by ID and no advanced searching implements such as key word searching, username searching, etc..

# Java Grep App
## Introduction
- Java Grep App searches for a text pattern recursively in a given directory, and output matched lines to a file. It is focused on Regex Pattern, Lambda & Steam API and Maven.
## Usage
- The app takes three arguments:
```
USAGE: regex rootPath outFile  

Similar to  
egrep -r {regex} {rootPath} > {outFile}
```

- `regex` - a special text string for describing a search pattern
- `rootPath` - root directory path
- `outFile` - output file name
- Demo:
```python
#note: regex must match entire line.  
.*data.* ~/dev/test /tmp/grep.out
```

* It searches all files in `~/dev/test` directory, and output lines contain `data` keyword to the output file `/tmp/grep.out`

## Design and Implementation
- Pseudo code and workflow

```pseudocode
matchedLines = []
for file in listFiles(rootDir)
  for line in readlines(file)
    if containsPattern(line)
      matchedLines.add(line)
writeToFile(matchedLines)
```

- Libraries
  - Use Lambda and Stream API
  - Use Maven to manage the project and its dependencies
## Enhancements and Issues

* The app would return all lines matched the search pattern without exception, so may improve this app with centain filter.
* The app only takes one keywork as a search pattern, so may add more arguments to do a further search. E.g. `lines with pattern A & pattern B` , `lines with pattern A but not pattern B`, etc..

# Java JDBC App

## Introduction

*  Java JDBC App is a Java Database Connectivity application. The app connects the containerized Postgresql database and applies CRUD(Create, Read, Update and Delete) data access pattern with created DAO(Data Access Object) and DTO(Data Transfer Object). It is based on the Lynda Java JDBC course([Link](https://www.lynda.com/Java-tutorials/Learning-JDBC/779748-2.html)) and to get Java JDBC implementation.  

## Usage

* The app takes arguments

```
USAGE: tablename query_keyword query_arguments
```

* `Tablename` - a specific table in the database { customer | order | salesperson | product }
* `query_keyword` - { select | insert | update | delete }
* `query_arguments` - row id and record arguments in order
* Demo:

```sql
customer select id

similar to:
select * from customer where customer.id = id;
```

* it selects the record from the customer table with the certain id.

## Design and Implementation

* PostgreSQL database scheme and table_info
![p1](https://github.com/steve-ma-jrvs/Java-Applications/blob/master/images/Entity%20Relationship%20Diagram.png)
* Workflow
![p2](https://github.com/steve-ma-jrvs/Java-Applications/blob/master/images/Screen%20Shot%202019-07-11%20at%204.58.48%20PM.png)

  * Prepare Database connection manager

  * Create DTO for each table to pass the data

  * Create DAO for each table to apply CRUD

    * Create preparestatement, e.g. 

    * Demo:

    * ```SQL
      private static final String GET_ONE = "SELECT customer_id, first_name, last_name, email, phone, address, city, state, zipcode FROM customer WHERE customer_id=?";
      ```

  * Pass statement with connection manager to postgreSQL database and return the result

## Enhancements and Issues

* Need to do preparestatement in each DAO
* Usage is limited 
