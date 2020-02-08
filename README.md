
# Get rid of System.out

## Or how to replace annoying System.out.println with concise println

Some features of Java are annoying for me, especially after several years of Scala experience. First of all: why should I use System.out.println instead of println!

It appears , that this problem can be solved easily

## Quick Start

#### Maven:
```
    <dependency>
       <groupId>ru.exxo.jutil</groupId>
      <artifactId>printer</artifactId>
       <version>1.2</version>
    </dependency>
```
#### Gradle
```
    implementation 'ru.exxo.jutil:printer:1.2'
```

And then:

```
    import static ru.exxo.jutil.Printer.*;
    
    println("I can print!");
    println(10);
    println(true);

    printf("It's %TA, %<tH:%<tM. It's %s", new Date(), "good");
    printf(Locale.forLanguageTag("RU"), "%n%,.2f", 10000.5);
        
```

