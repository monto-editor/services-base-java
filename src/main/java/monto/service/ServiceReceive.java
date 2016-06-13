package monto.service;

import java.util.function.Consumer;

import com.google.gson.JsonElement;

import monto.service.configuration.Configuration;
import monto.service.gson.GsonMonto;
import monto.service.request.Request;
import monto.service.types.PartialConsumer;
import monto.service.types.PartialFunction;
import monto.service.types.UnrecongizedMessageException;

public class ServiceReceive {
	private String tag;
	private JsonElement contents;
	private ServiceReceive(String tag, JsonElement contents) {
		super();
		this.tag = tag;
		this.contents = contents;
	}

	public <A,E extends Exception> A match(PartialFunction<Request,A,E> onRequest, PartialFunction<Configuration,A,E> onConfiguration) throws UnrecongizedMessageException, E {
		switch(tag) {
		case "request":
			return onRequest.apply(GsonMonto.fromJson(contents,Request.class));
		case "configuration":
			return onConfiguration.apply(GsonMonto.fromJson(contents,Configuration.class));
		default:
			throw new RuntimeException(String.format("unrecognized message type %s\n", tag));
		}
	}
	
	public void matchVoid(Consumer<Request> onRequest, Consumer<Configuration> onConfiguration) throws UnrecongizedMessageException {
		this.<Void,UnrecongizedMessageException>match(req -> {onRequest.accept(req); return null;}, conf -> {onConfiguration.accept(conf); return null;});
	}
	
	public <E extends Exception> void matchExc(PartialConsumer<Request,E> onRequest, PartialConsumer<Configuration,E> onConfiguration) throws UnrecongizedMessageException,E {
		this.<Void,E>match(req -> {onRequest.accept(req); return null;}, conf -> {onConfiguration.accept(conf); return null;});
	}
	
	public String getTag() {
		return tag;
	}

	public Object getContents() {
		return contents;
	}
}