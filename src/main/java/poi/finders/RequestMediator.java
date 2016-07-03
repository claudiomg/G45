package poi.finders;

import spark.Request;

public class RequestMediator {

	private Request concreteRequest;

	public RequestMediator(Request request) {
		this.setConcreteRequest(request);
	}

	private void setConcreteRequest(Request request) {
		this.concreteRequest = request;
	}
	
	private Request getConcreteRequest() {
		return this.concreteRequest;
	}

}
