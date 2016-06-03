package poi.jobs;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class QuartzListener extends QuartzInitializerListener {
	private static final Logger LOG = LoggerFactory.getLogger(QuartzListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        super.contextInitialized(sce);
        ServletContext ctx = sce.getServletContext();
        StdSchedulerFactory factory = (StdSchedulerFactory) ctx.getAttribute(QUARTZ_FACTORY_KEY);
        try {
            Scheduler scheduler = factory.getScheduler();
            JobDetail job = JobBuilder.newJob(CGPServiceJob.class).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("job1").withSchedule(
                    CronScheduleBuilder.cronSchedule("0 15 0 * * ?")// se ejecuta todos los dias a las 00:15 am
            ).startNow().build();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (Exception e) {
            LOG.error("Ocurri\u00f3 un error al calendarizar el trabajo", e);
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        super.contextDestroyed(sce);
    }
}
