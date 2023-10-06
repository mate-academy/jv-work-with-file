### Common mistakes

#### Create variables with informative names.

Bad naming:
```java
BufferedReader br = new BufferedReader(new FileReader(fileName))
```  

Good naming:
```java
BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))
```  

#### Use constants where applicable.
If you have strange strings or numbers in the code it's better to declare them as constants.
The name of the constant should display this object's purpose.

Bad practice:
```java
    public boolean startWithLetter(String word) {
        return word.startsWith("d"); // why do we use 'd' here???
    }
```

Good practice:
```java
    private static final String SPECIFIED_CHARACTER = "d";
    
    public boolean startWithLetter(String word) {
        return word.startsWith(SPECIFIED_CHARACTER); 
    }
```
[Correct constant names](https://google.github.io/styleguide/javaguide.html#s5.2.4-constant-names)

#### Close all resources.
Connections, streams, files, and other classes that implement the `Closeable` or `AutoCloseable` interface, 
needs to be closed after use. Furthermore, that close should be done in a `finally` block.
Preferably, when class implements `AutoCloseable`, resource should be created using "try-with-resources" pattern 
and will be closed automatically.

#### Don't ignore exceptions.
Leaving empty catch block or `e.printStackTrace` here is a bad practice. 
Better re-throw `RuntimeException` with original exception in the parameters:
```java
catch (Exception e) {
    throw new RuntimeException(e);
}
```

#### Don't create redundant variables.
Let's make your code simple and easy to read. So better avoid using redundant variables.

#### Use System.lineSeparator() insted `\n`
[explanation](https://www.geeksforgeeks.org/system-lineseparator-method-in-java-with-examples/)
