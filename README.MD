# java-fib

Fibonacci CLI that handles negative integers as well as high n-values.

### Building

```
mvn package
```

will create the `/target` directory and create a jar inside named something like `fib-1.0-SNAPSHOT.jar` which then can be run using the "usage" section below".

### Usage

```
java -cp target\fib-1.0-SNAPSHOT.jar com.arcia.App [options] <n>
```

#### Options

```
--profile|-p[=count] Tests timing of Fib.fibonnacci and Fib.bigFibonacci [count] times (default 1)
```
