# Java Base Project for Monto services

This project contains base classes for creating new services for the [Monto Broker](https://github.com/monto-editor/broker).

## Creating your own services
1. Take a look at existing language services to see how to setup a new Gradle project and reference this repository.
2. Create a new class that extends the class [MontoService](src/monto/service/MontoService.java) for a new service.
3. Override `void onRequest(Request request)` which should handle source and product messages sent from the broker and send a ProductMessage to the broker using `void sendProductMessage(…)`.
4. Optionally, override `void onConfigurationMessage(Configuration message)` which should handle configuration messages and set the proper configurations in the service.

## Examples
You will need to create a MainClass to register your services at the broker. Examples for MainClasses and services can be found in the existing services, namely:

- [services-java](https://github.com/monto-editor/services-java)
- [services-javascript](https://github.com/monto-editor/services-javascript)
- [services-python](https://github.com/monto-editor/services-python)
