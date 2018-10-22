# Hera Logging Framework

Hera Logging Framework is a reliable and flexible enterprise logging API for Java, that wraps the
    functionality provided for any logging framework.
    The default underlying logging mechanism is based on  the standard output
    This implementation use the decorator or wrapper design pattern in order to provide a common alternative to subclassing
    to attach new responsibilities to objects. Wrappers can save the day when extension by sub-classing
    is impractical, which happens to be the case for the Log4J Logger class.


## Log levels
 <P>The levels  to prioritize the log messages, are defined in the  <code>ve.com.cantv.common.hera.logging.LogLevel</code> class:</P>

- FATAL: designates very severe error events that will presumably lead the application to abort.

- ERROR: designates error events that  might still allow the application to continue running.

- WARN: designates potentially harmful situations

- INFO: designates informational messages that highlight the progress of the application at coarse-grained grade.

- DEBUG: designates fine-grained  informational events that are most useful to debug an application.

- TRACE: fine-grained debug message, typically capturing the flow through the application.


## Getting Started

The first and foremost advantage of any logging API over plainSystem.
out.println statements resides in its ability to disable certain log statements
while allowing others to print unhindered without the need to re-compile, its only a matter of configuration.
**Important:** Never use <code>System.out.println</code> in your Java code.

### Using Hera Logging Framework


#### Setup
   You need to include the hera-logging.jar in the classpath.

#### Usage

In order to retrieve a HeraLogger object, you only must declare one of the following forms in your Java code:

```java
/**
 * This is the conventional way to retrieve a logger for this class
 */
 public static final HeraLogger LOGGER = HeraLogger.getLogger(Test.class);


/**
 * This way use the class' name to retrieve a logger for this class
 */
public static final HeraLogger LOGGER = HeraLogger.getLogger(Test.class.getName());

A simple hello world program:

public class HelloWorld {
public static final HeraLogger LOGGER = HeraLogger.getLogger(HelloWorld.class);
    public static void main(String[] args) {
        LOGGER.debug("Hello world.");
    }
}
```

### Extending the Hera logging framework

   Remember that Hera logging framework has a flexible and extensible architecture. If you wish use another underlying logger, you
   simply must create a new class with a public constructor that takes one String param representating the logger's name, and you must
   implement the `ve.com.cantv.common.hera.logging.UniversalLogger` interface.
   Furthermore, add in the `hera-logging.properties` file the following key: `loggingClass` with the full qualified class name of the new class.

  Example code:

```java
   public class Jdk14Logger implements UniversalLogger {

        /**
         * Wrapper of Java logging API Logger object
         */
        private java.util.logging.Logger logger;


        /**
         * Creates a new Jdk14Logger object
         * @param name the logger's name
         */
        public Jdk14Logger(String name) {
            this.logger = java.util.logging.Logger.getLogger(name);
        }

        //The rest of code is omitted by clarity

    }
```

Example of *hera-logging.properties* file configuration:

   `loggingClass=ve.com.cantv.common.hera.logging.ext.Jdk14Logger`


   *Important:* If you wish use STDOUT like default underlying logging mechanism, you must remove
   `loggingClass` key from `hera-logging.properties` file configuration. In order to use the functionality
   provided by the `hera-logging.properties` file configuration simply add this file to your CLASSPATH.

   </pre>


## Built With

*
* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

Although I have tried my best, this summary undoubtedly contains omissions, inaccuracies
and mistakes. You can help me improve it by sending your suggestions or questions to:

* **Liodegar Bracamonte** - *Initial work* - [liodegar@gmail.com)


## License

No license required.

## Acknowledgments

* To the all open source software contributors.


