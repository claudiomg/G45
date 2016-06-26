package poi.modelo.usuario;

public class RepositorioAdministrador {

	public static RepositorioAdministrador instance = null;

	private RepositorioAdministrador(){		
	};

	public static RepositorioAdministrador getInstance() {
		if(instance == null) {
			instance = new RepositorioAdministrador();
		}
		return instance;
	}

}
