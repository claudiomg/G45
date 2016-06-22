package poi.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import poi.repositorios.RepositorioAbstracto;
import poi.repositorios.RepositorioBancosExternos;


public class BancosServiceJob implements Job {
	public BancosServiceJob() {
    }

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		RepositorioAbstracto repo = RepositorioBancosExternos.getInstance();
		try {
			repo.limpiarPOIs();
		} catch (Exception e) {
			System.out.println("Error en la conexion: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
