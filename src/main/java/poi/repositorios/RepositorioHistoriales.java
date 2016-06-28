package poi.repositorios;

public class RepositorioHistoriales {
	
	protected static RepositorioHistoriales instance = null;
	
	public static RepositorioHistoriales getInstance(){
		if (instance == null){
			instance = new RepositorioHistoriales();
		}
		return instance;
	}
	
	

}
