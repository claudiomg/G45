package poi.modelo.usuario;

public class RepositorioTerminal {

	public static RepositorioTerminal instance = null;

	private RepositorioTerminal(){		
	};

	public static RepositorioTerminal getInstance() {
		if(instance == null) {
			instance = new RepositorioTerminal();
		}
		return instance;
	}
	
}
