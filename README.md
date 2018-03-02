# springboot-starter-demo
A project which demonstrates how a custom Spring Boot starter can be made

## Spring Boot Auto Configuration

Spring Boot has a concept of auto configuration, which is its "killer" feature. This auto configuration is provided by Spring Boot starters, which contain everything you need to use a specific dependency. Using a dependency should always be this simple!

## What you need

### 1. The dependency you want to auto configure

You need something you can configure. In this project, that's the `autoconfigure-stringgen` module.

### 2. The auto configuration module

This module (`autoconfigure-autoconfiguration` in this example) provides Properties and Configuration for our dependency.

This module uses the scope `provided` when [referring](https://github.com/jmesserli/springboot-starter-demo/blob/16cd541d3a905a0c4f03c038bc3730d7575e5149/autoconfigure-autoconfiguration/pom.xml#L34) to the dependency we're configuring, allowing the auto configuration module to be present without pulling the dependencies in directly.
This is useful if you want to have a project which accumulates all auto configuration modules.

#### Properties

The properties are [defined](https://github.com/jmesserli/springboot-starter-demo/blob/16cd541d3a905a0c4f03c038bc3730d7575e5149/autoconfigure-autoconfiguration/src/main/java/nu/peg/springboot/autocfg/StringGeneratorProperties.java#L6) in a class annotated with `@ConfigurationProperties`.
Those properties need to be [enabled and referenced](https://github.com/jmesserli/springboot-starter-demo/blob/16cd541d3a905a0c4f03c038bc3730d7575e5149/autoconfigure-autoconfiguration/src/main/java/nu/peg/springboot/autocfg/StringGeneratorAutoConfiguration.java#L14) in a `@Configuration` class using `@EnableConfigurationProperties` so Spring can pick them up.

#### Auto configuration

Your [auto configuration class](https://github.com/jmesserli/springboot-starter-demo/blob/16cd541d3a905a0c4f03c038bc3730d7575e5149/autoconfigure-autoconfiguration/src/main/java/nu/peg/springboot/autocfg/StringGeneratorAutoConfiguration.java#L15) is a standard `@Configuration` class, which has to be [referenced](https://github.com/jmesserli/springboot-starter-demo/blob/16cd541d3a905a0c4f03c038bc3730d7575e5149/autoconfigure-autoconfiguration/src/main/resources/META-INF/spring.factories#L2) in the `META-INF/spring.factories` file.

Unlike normal `@Configuration` classes, there are special `@Conditional[...]` annotations, which assure that the auto configuration only runs when the dependency is on the class path.

### 3. The starter module

It combines all needed dependencies and the auto configuration module.
Only contains a [pom.xml file](https://github.com/jmesserli/springboot-starter-demo/blob/16cd541d3a905a0c4f03c038bc3730d7575e5149/autoconfigure-starter/pom.xml#L31).

### 4. Use the starter

When you [include](https://github.com/jmesserli/springboot-starter-demo/blob/16cd541d3a905a0c4f03c038bc3730d7575e5149/autoconfigure-app/pom.xml#L42) your starter, all needed dependencies and the auto configuration are pulled in and applied automatically. You can immediately [start injecting](https://github.com/jmesserli/springboot-starter-demo/blob/16cd541d3a905a0c4f03c038bc3730d7575e5149/autoconfigure-app/src/main/java/nu/peg/springboot/autocfgapp/GeneratedStringPrinter.java#L18) beans provided by the auto configuration or [override some properties](https://github.com/jmesserli/springboot-starter-demo/blob/master/autoconfigure-app/src/main/resources/application.properties) first.