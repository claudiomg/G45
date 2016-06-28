package poi.modelo.usuario;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTerminal {

	public static RepositorioTerminal instance = null;
	public List<Terminal> terminales = new ArrayList <Terminal>(); 

	private RepositorioTerminal(){		
	};

	public static RepositorioTerminal getInstance() {
		if(instance == null) {
			instance = new RepositorioTerminal();
		}
		return instance;
	}
	
}
