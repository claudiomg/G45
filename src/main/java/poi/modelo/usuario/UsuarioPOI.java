package poi.modelo.usuario;

public abstract class UsuarioPOI {
	private String usuario;
	private String password;
	
	public boolean isAdmin() {
		return false;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
