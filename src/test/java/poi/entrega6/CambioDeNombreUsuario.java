package poi.entrega6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.modelo.usuario.Terminal;
import poi.modelo.usuario.UsuarioPOI;


public class CambioDeNombreUsuario implements WithGlobalEntityManager{
		
	    UsuarioPOI user = new Terminal("jorge");
	    
	    @Before
		public void setUp(){
			
			user.setPassword("666");
		}
	    
	    @Test
		public void crearUsuarioTest(){
	    	
	    	// Crear y persistir Usuario 
			user.persistir(user);
			
	        //Recuperar Usuario 
			
			String UsuarioABuscar = "jorge";

					entityManager()
					.createQuery("from UsuarioPOI l where l.usuario like :usuario", UsuarioPOI.class)
					.setParameter("usuario", "%" + UsuarioABuscar + "%").getSingleResult().getId();
			
			// ModificarCombreDeNombreUsuario
					entityManager().getTransaction().begin();
					UsuarioPOI usu2 = entityManager().find(UsuarioPOI.class, user.getId());
					usu2.setUsuario("carlos");
					entityManager().getTransaction().commit();
					

		    // ConfirmarCambioDeNombre

			      	Assert.assertEquals(entityManager().find(UsuarioPOI.class, user.getId()).getUsuario(),"carlos");
			
			      //	createQuery("from UsuarioPOI l where l.UsuarioId = :id", UsuarioPOI.class)
				//	.setParameter("id", idRecuperado).getSingleResult()
					
					}
	}


