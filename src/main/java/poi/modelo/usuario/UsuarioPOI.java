package poi.modelo.usuario;

public abstract class UsuarioPOI {
	private String usuario;
	private String password;
	
	public boolean isAdmin() {
		return false;
	}
	public boolean hasAccess(String usuario, String password) {
		return this.getUsuario().equals(usuario) && this.getPassword().equals(password);
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
