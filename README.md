# Reload tinylog configuration at runtime

Officially, the configuration of tinylog 2 is immutable. The main reasons for having an immutable configuration are maintainability and performance (see [benchmark](https://tinylog.org/v2/benchmark/#conclusion)).

However, if you know what you do, the configuration of tinylog 2 can be reloaded at runtime with a few hacks. You will find everything you need in this repository.

Steps for enabling reconfiguration:

1. Create a custom logging provider that re-initializes the native tinylog logging provider when reloading the configuration: [ReconfigurableLoggingProvider.java](src/main/java/demo/ReconfigurableLoggingProvider.java)
2. Create a custom no-op writer that doesn't output anything, but can be used in the tinylog configuration to prevent JVM runtime optimizations that would prevent reconfiguration otherwise: [BlackHoleWriter.java](src/main/java/demo/BlackHoleWriter.java)
3. Register the custom logging provider and custom no-op writer in [META-INF/services](src/main/resources/META-INF/services) and in your [tinylog.properties](src/main/resources/tinylog.properties).
4. Happy reconfigurable logging for you [application](src/main/java/demo/Application.java) :)