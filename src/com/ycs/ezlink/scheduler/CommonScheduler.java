package com.ycs.ezlink.scheduler;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ycs.ezlink.scheduler.filerouter.DirListenerImpl;

public class CommonScheduler {
	private static Logger logger = LoggerFactory.getLogger(CommonScheduler.class);
	public static final String CONFIG = "quartz.properties";
	
	public static final String JOB_NAME = CommonScheduler.class.getSimpleName();
    public static final String JOB_GROUP = "DEFAULT";

    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	     
		
		Scheduler scheduler;
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			String dirListenerKey = "MyDirListener";
            long minUpdateAge = 5000L;
			scheduler.getContext().put(dirListenerKey, new DirListenerImpl());
			scheduler.start();
			/*StdSchedulerFactory sf = new StdSchedulerFactory(CONFIG);
            Scheduler scheduler = sf.getScheduler();

            DirListenerImpl dirListener = new DirListenerImpl();
            String dir = "C:\\Users\\Samarjit\\Desktop\\sharpkeys2\\dir_scanner";//System.getProperty("user.home");
            String dirListenerKey = "MyDirListener";
            long minUpdateAge = 5000L;
            scheduler.getContext().put(dirListenerKey, dirListener);
            logger.info("Created dir listener on {}", dir);

            JobDetail jobDetail = JobBuilder.newJob(DirectoryScanJob.class)
                            .withIdentity(JOB_NAME, JOB_GROUP)
                            .usingJobData(DirectoryScanJob.DIRECTORY_NAME, dir)
                            .usingJobData(DirectoryScanJob.DIRECTORY_SCAN_LISTENER_NAME, dirListenerKey)
                            .usingJobData(DirectoryScanJob.MINIMUM_UPDATE_AGE, minUpdateAge)
                            .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                            .withIdentity(JOB_NAME)
                            .withSchedule(
                            		SimpleScheduleBuilder.simpleSchedule()
                                            .repeatForever()
                                            .withIntervalInMilliseconds(3000))
                            .build();
            scheduler.scheduleJob(jobDetail, trigger);
            logger.info("Job {} scheduled.", jobDetail.getKey());

            logger.info("Start scheduler and let it run for 60 secs.");
            scheduler.start();
            Thread.sleep(60 * 1000L);
            scheduler.shutdown();
            logger.info("Scheduler is done.");*/
//			FileRouterJob fr = new FileRouterJob();
//			fr.execute(null);

			/*Logger logger = Logger.getRootLogger();
			Enumeration appenders = logger.getAllAppenders();
			for  (; appenders.hasMoreElements();) {
				Appender elm = (Appender) appenders.nextElement();
				
				System.out.println(elm.getName());
			}
			 
		 
			logger.removeAllAppenders();
			PropertyConfigurator.configureAndWatch(CommonScheduler.class.getResource("/log4j.properties").getPath());
			appenders = logger.getAllAppenders();
			for  (; appenders.hasMoreElements();) {
				Appender elm = (Appender) appenders.nextElement();
				
				System.out.println(elm.getName());
			}*/
			
			 
		} catch (Exception e) {
			e.printStackTrace();
		}

         // and start it off
	}

	
}
