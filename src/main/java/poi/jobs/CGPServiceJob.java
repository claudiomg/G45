package poi.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import poi.repositorios.RepositorioCGPExternos;

public class CGPServiceJob implements Job {
	public CGPServiceJob() {
    }

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		RepositorioCGPExternos repo = RepositorioCGPExternos.getInstance();
		repo.actualizarRepositorio();
	}
}
