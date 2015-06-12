Java Base Project for Monto services
====================================

This project contains base classes for creating new services for the [monto broker](https://github.com/monto-editor/broker)

Creating your own services
--------------------------
1. Import the jar library located in the `dist` folder or the sources into your project
2. Create a class for each service that extends the class `de.tudarmstadt.stg.monto.service.MontoService`
3. Override `ProductMessage onMessage(List<Message> messages)` which should handle Version Message requests sent from the broker and return a Product Message to the broker
4. Instantiate your class and provide it the address and the zeromq context.

Example main using ecmascript/javascript services
--------------------------------------
```
String addr = "tcp://localhost:";
List<MontoService> services = new ArrayList<>();
Context context = ZMQ.context(1);

Runtime.getRuntime().addShutdownHook(new Thread() {
	@Override
	public void run() {
        System.out.println("terminating...");
        context.term();
        for (MontoService service : services) {
			service.stop();
		}
		System.out.println("terminated");
	}
});

services.add(new ECMAScriptTokenizer(addr + 5010, context));
services.add(new ECMAScriptParser(addr + 5011, context));
services.add(new ECMAScriptOutliner(addr + 5012, context));
services.add(new ECMAScriptCodeCompletion(addr + 5013, context));

for (MontoService service : services) {
	service.start();
}
```