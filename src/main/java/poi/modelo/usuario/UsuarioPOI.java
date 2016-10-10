package poi.modelo.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UsuarioPOI implements WithGlobalEntityManager {
	@Id
	@GeneratedValue
	@Column(name = "id_usuario")
	private Long UsuarioId;
	@Column(name = "NombreUsuario")
	private String usuario;
	@Column(name = "PasswordUsuario")
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
	public void persistir(UsuarioPOI user) {
		entityManager().getTransaction().begin();
		entityManager().persist(user);
		entityManager().getTransaction().commit();
	}
}
