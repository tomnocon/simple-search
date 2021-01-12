# Simple search

A command line tool for scoring files which contains provided words in given directory.

## Prerequisites

To run this application, you need to install at least java 8 and configure properly the *JAVA_HOME* environment variable.

## Building 

Without installed maven:

```shell script
mvnw package
```

With installed maven:

```shell script
mvn package
```

## Running

To run java application provide path to the jar file and directory containing text files.

``` shell script
java -jar <jarPath> <directoryContainingTextFiles>
```

Example:

``` shell script
java -jar target/simple-search-0.0.1-jar-with-dependencies.jar /foo/bar
```

# Architecture decisions

This section contains important architectural decision made along with its context and consequences.

## Command line parser

### Context

Most of the command line tools require parsing arguments that are passed in the console. 
This decision is to create a command line parser.

### Decision

Possible choices:
* use one of the popular open source library for parsing arguments
* implement custom solution with well-defined abstraction

### Consequences

* Easy and fast library for parsing arguments
* User friendly interface
* Reusability in other command line projects

## Search algorithm

### Context

Searches should be relatively efficient.

### Decision

For maximum performance we need to build a word occurrence representation with `O(1)` lookup time.
Key-value store is perfectly suited to this task. Where the key represents a word, and the value represents a word's statistics.
There are several stores that can be used eg. `InMemory: HashMap, Memcached` or `Persistent: Redis, RocksDB`.

### Consequences

* Quick access to word's occurrences
* High memory consumption

## Ranking

### Context

* The rank score must be 100% if a file contains all the words
* It must be 0% if it contains none of the words
* It should be between 0 and 100 if it contains only some of the words but the exact ranking formula is up to you to choose and implement

### Decision

The first and second point are quite obvious. The third point needs formula that promote files with higher word occurrences.

Proposed logarithmic formula:
```
rank=1-1/sum(f1,..,fn)
where:
f - word's occurrences in file
```

### Consequences

* Promoting files with higher occurrences
* Lack of scoring between `(0 - 0.5)`


