### Common mistakes

#### Divide program logic
Large methods are very difficult to read and understand. So methods should be short, between 5-15 lines.
And it's important for you to remember that one method should be responsible for one task.

For example: "write to file" or "create report" - are separate operations. So we should create separate methods for them.

#### Stick to the method naming convention
Correct [method names](https://mate-academy.github.io/style-guides/java/java.html#s5.2.3-method-names) is the first step to pure code.

#### Remember about informative names of the variables

#### Use StringBuilder instead of String to concat data in loops
Keep in mind that String concatenation creates many new objects that take up a lot of memory if you use it inside 
of a loop. Though it's safe to use it outside of a loop because the compiler will replace it with StringBuilder anyway  [java doc](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html)

#### Use split() instead of substring()
`substring()` messes the code in this task.

#### Use constants
If you have strange strings or numbers in the code, it's better to declare them as constants.
The name of the constant should display this object's purpose.

#### Local variables
If you are counting something in your method for example using some variable for the result, don't make this variable a class field.  
The class fields should represent the state of the class objects otherwise they should be local variables.

#### try-with-resources
Remember, if you are using classes that implement an AutoCloseable interface,
we should use it with try-with-resources.

#### Don't open nested connections to files
Avoid opening the connection to the file in a try block inside of another try block. 
We should keep open connections to resources as short as possible. In code, if you need to 
read and write to different files - do this in separate try blocks that are not located inside each other.

#### Pay attention to access modifiers, they should not always be `public`
If the method has only a utility purpose and is used only inside the same class, it should not be 
`public`. Keep your code as close as possible to follow the encapsulation principle.

#### Don't ignore exceptions
Leaving an empty catch block or `e.printStackTrace` here is a bad practice. 
Better re-throw `RuntimeException` with the original exception and some message in the parameters:
```
catch (Exception e) {
    throw new RuntimeException("Can't read data from the file " + fileName, e);
}
```
#### Disable wildcard imports to always import single classes
[How to do it.](https://www.jetbrains.com/help/idea/creating-and-optimizing-imports.html#disable-wildcards-for-class)
