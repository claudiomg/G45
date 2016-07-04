package poi.repositorios;

public class RepositorioPOI extends RepositorioAbstractoPOI {
	
	protected static RepositorioPOI instance;
	protected RepositorioPOI() { /*Existe para anular la instanciacion*/ };
	public static RepositorioPOI getInstance() {
		if(instance == null) {
			instance = new RepositorioPOI();
		}
		return instance;
	}
}

