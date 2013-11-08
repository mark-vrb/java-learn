Welcome to java-learn
=====================
My Java language university learning. Builded with [IntelliJ IDEA Community Edition](http://www.jetbrains.com/idea/free_java_ide.html).

1. Hello world
--------------
Simple hello world application with two classes in two different packages. Folder `just-hello`.

2. Containers
-------------
Class for performing math operations with matrices, such as multiplying. There are two realizations of Matrix class. One of them based on ArrayList, another - on LinkedList. Simple console app tests performance of this two realizations. Also included unit tests for all public methods in Matrix and Vector classes. JUnit library was used for this purpose. Folder `matrix-math`.

3. Strings IO
-------------
- Class `SymbolsUsageStatistics` can read text file and calculate statistics of usage for all symbols from this file.
- Read/write from/to file for `Matrix` class (arraylistbased) with `Reader` and `Writer`.
- Serialization for `Matrix` class (arraylistbased) with `ObjectInputStream` and `ObjectOutputStream`.

4. Threading
------------
> **NOTE:** To be implemented 

5. Jdbc
-------
DAO layer for agile-tool aplication with simple CRUD logic. Using jdbc connector for MySQL. Entities: `User`, `Board`, `BoardColumn`, `WorkItem`, `BoardUserRole`. Corresponding scheme should be deployed to local instance of MySQL.

6. Unit testing, logging
------------------------
Unit tests with [JUnit4](http://junit.org/) for DAO layer of agile-tool app, errors logging with [Log4j2](http://logging.apache.org/log4j/2.x/). Log4j2 package added via Maven.
