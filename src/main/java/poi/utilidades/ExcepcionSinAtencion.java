package poi.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import javax.persistence.*;


import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

@Entity
@Table (name = "ExcepcionesSinAtencion")
public class ExcepcionSinAtencion implements WithGlobalEntityManager {
	
	@Id
	@GeneratedValue
	@Column(name = "ExcepcionSinAtencionId")
	private Long ExcepcionSinAtencionId;
	
	@Convert (converter = LocalDateTimeConverter.class)
	@ElementCollection
	@CollectionTable(
	        name="feriados",
	        joinColumns=@JoinColumn(name="ExcepcionId")
	  )
	
	private List<LocalDateTime> feriados = new ArrayList<LocalDateTime>();

	public void setFeriados(List<LocalDateTime> feriados) {
		this.feriados = feriados;
	}

	public List<LocalDateTime> getFeriados() {
		return feriados;
	}
	
	public Long getExcepcionSinAtencionId (){
		return ExcepcionSinAtencionId;
	}

	public void agregarFeriados(LocalDateTime unFeriado){
		feriados.add(unFeriado);
	}
	
	public boolean noEsUnFeriado(LocalDateTime diaActual){
		return this.feriados.stream().noneMatch(unDia -> unDia.getDayOfMonth()== diaActual.getDayOfMonth())
				|| this.feriados.stream().noneMatch(unDia -> unDia.getMonth() == diaActual.getMonth());
	}
	
	public void persistir(){
		entityManager().getTransaction().begin();
		entityManager().persist(this);
		entityManager().getTransaction().commit();
	}	
}
