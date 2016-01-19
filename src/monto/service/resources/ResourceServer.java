package monto.service.resources;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class ResourceServer {
	
	private int port;
	private Server server = null;
	private String resourceDirectory;

	public ResourceServer(String resourceDirectory, int port) {
		this.port = port;
		this.resourceDirectory = resourceDirectory;
	}

	public void start() throws Exception {
		server = new Server(port);
		
	    ResourceHandler resourceHandler = new ResourceHandler();
	    resourceHandler.setResourceBase(resourceDirectory);
	    resourceHandler.setDirectoriesListed(true);

	    ContextHandler context = new ContextHandler();
        context.setContextPath("/");
        context.setHandler(resourceHandler);
	    
        server.setHandler(context);
		server.start();
	}
	
	public void stop() throws Exception {
		server.stop();
	}

	public void join() throws Exception {
		server.join();
	}
}
