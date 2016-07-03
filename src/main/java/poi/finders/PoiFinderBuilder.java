package poi.finders;

public class PoiFinderBuilder {

	private RequestMediator request;

	public PoiFinderBuilder(RequestMediator request) {
		this.setRequest(request);
	}
	
	public PoiFinder buildFinder() {
		// TODO Auto-generated method stub
		return null;
	}

	private void setRequest(RequestMediator request) {
		this.request = request;
	}
	
	private RequestMediator getRequest() {
		return this.request;
	}

}
