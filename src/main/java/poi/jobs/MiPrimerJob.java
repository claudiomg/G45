package poi.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MiPrimerJob implements Job {
	public MiPrimerJob() {
    }

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		 System.err.println("Hello World!  MyJob is executing.");
	}
}
