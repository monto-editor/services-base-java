Java Base Project for Monto services
====================================

This project contains base classes for creating new services for the [monto broker](https://github.com/monto-editor/broker)

Creating your own services
--------------------------
1. Import the jar libraries located in the `dist` and in the `lib` folder
2. Create a new class that extends the class `de.tudarmstadt.stg.monto.service.MontoService` for a new service
3. Override `ProductMessage onVersionMessage(List<Message> messages)` which should handle Version Messages sent from the broker and return a Product Message to the broker
4. Override `void onConfigurationMessage(List<Message> messages)` which should handle Configuration Messages and set the proper configurations in the service.

Example main from ecmascript/javascript services
--------------------------------------
```
String address = "tcp://*";
String regAddress = "tcp://*:5004";
ZContext context = new ZContext(1);
List<MontoService> services = new ArrayList<>();

Runtime.getRuntime().addShutdownHook(new Thread() {
	@Override
    public void run() {
    	System.out.println("terminating...");
        for (MontoService service : services) {
        	service.stop();
	    }
        context.destroy();
        System.out.println("everything terminated, good bye");
    }
});

services.add(new ECMAScriptTokenizer(context, address, regAddress, "ecmascriptTokenizer"));
services.add(new ECMAScriptParser(context, address, regAddress, "ecmascriptParser"));
services.add(new ECMAScriptOutliner(context, address, regAddress, "ecmascriptOutliner"));
services.add(new ECMAScriptCodeCompletion(context, address, regAddress, "ecmascriptCodeCompletioner"));
services.add(new FlowTypeChecker(context, address, regAddress, "ecmascriptFlowTypeChecker"));

for (MontoService service : services) {
	service.start();
}
```