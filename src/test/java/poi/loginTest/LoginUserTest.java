package poi.loginTest;

import org.junit.Assert;
import org.junit.Test;

import poi.modelo.usuario.Terminal;
import poi.modelo.usuario.UsuarioPOI;
import poi.repositorios.RepositorioUsuarios;

public class LoginUserTest {

	
	@Test
	public void cuandoNoHayUsuariosDevuelveNull(){
		UsuarioPOI usuario;
		usuario = RepositorioUsuarios.getInstance().authenticateUser("pepe","lalala");
		Assert.assertTrue(usuario == null);
	}
	
	@Test
	public void cuandoHayUsuariosYnoCoincidenDatosDevuelveNull(){
		UsuarioPOI usuario;
		UsuarioPOI abasto = new Terminal("Abasto");
		abasto.setPassword("pass");
		RepositorioUsuarios.getInstance().agregarRegistro(abasto);
		usuario = RepositorioUsuarios.getInstance().authenticateUser("pepe","lalala");
		Assert.assertTrue(usuario == null);
	}
	
	@Test
	public void cuandoHayUsuariosYCoincidenDatosNOdevuelveNull(){
		UsuarioPOI usuario;
		UsuarioPOI abasto = new Terminal("Abasto");
		abasto.setPassword("pass");
		RepositorioUsuarios.getInstance().agregarRegistro(abasto);
		usuario = RepositorioUsuarios.getInstance().authenticateUser("Abasto","pass");
		Assert.assertFalse(usuario == null);
	}
	
	@Test
	public void cuandoHayUsuariosYCoincidenDatosDevuelveElUsuarioCorrecto(){
		UsuarioPOI usuario;
		UsuarioPOI abasto = new Terminal("Abasto");
		abasto.setPassword("pass");
		RepositorioUsuarios.getInstance().agregarRegistro(abasto);
		usuario = RepositorioUsuarios.getInstance().authenticateUser("Abasto","pass");
		Assert.assertTrue(usuario.getUsuario().equals("Abasto"));
		Assert.assertTrue(usuario.getPassword().equals("pass"));
	}
}
