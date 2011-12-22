package com.ycs.ezlink.scheduler.filerouter;

import java.io.File;
import java.io.FileFilter;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.quartz.jobs.DirectoryScanJob;
import org.quartz.jobs.DirectoryScanListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyDirScanJob extends DirectoryScanJob {
	/*  78 */   private final Logger log = LoggerFactory.getLogger(super.getClass());
	public void execute(JobExecutionContext context)
			/*     */     throws JobExecutionException
			/*     */   {
			/*  87 */     JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
			/*  88 */     SchedulerContext schedCtxt = null;
			/*     */     try {
			/*  90 */       schedCtxt = context.getScheduler().getContext();
			/*     */     } catch (SchedulerException e) {
			/*  92 */       throw new JobExecutionException("Error obtaining scheduler context.", e, false);
			/*     */     }
			/*     */ 
			/*  95 */     String dirName = mergedJobDataMap.getString("DIRECTORY_NAME");
			/*  96 */     String listenerName = mergedJobDataMap.getString("DIRECTORY_SCAN_LISTENER_NAME");
			/*     */ 
			/*  98 */     if (dirName == null) {
			/*  99 */       throw new JobExecutionException("Required parameter 'DIRECTORY_NAME' not found in merged JobDataMap");
			/*     */     }
			/*     */ 
			/* 102 */     if (listenerName == null) {
			/* 103 */       throw new JobExecutionException("Required parameter 'DIRECTORY_SCAN_LISTENER_NAME' not found in merged JobDataMap");
			/*     */     }
			/*     */ 
			/* 107 */     DirectoryScanListener listener = (DirectoryScanListener)schedCtxt.get(listenerName);
			/*     */ 
			/* 109 */     if (listener == null) {
			/* 110 */       throw new JobExecutionException("DirectoryScanListener named '" + listenerName + "' not found in SchedulerContext");
			/*     */     }
			/*     */ 
			/* 114 */     long lastDate = -1L;
			/* 115 */     if (mergedJobDataMap.containsKey("LAST_MODIFIED_TIME")) {
			/* 116 */       lastDate = mergedJobDataMap.getLong("LAST_MODIFIED_TIME");
			/*     */     }
			/*     */ 
			/* 119 */     long minAge = 5000L;
			/* 120 */     if (mergedJobDataMap.containsKey("MINIMUM_UPDATE_AGE")) {
			/* 121 */       minAge = mergedJobDataMap.getLong("MINIMUM_UPDATE_AGE");
			/*     */     }
			/* 123 */     long maxAgeDate = System.currentTimeMillis() + minAge;
			/*     */ 
			/* 125 */     File[] updatedFiles = getUpdatedOrNewFiles(dirName, lastDate, maxAgeDate);
			/*     */ 
			/* 127 */     if (updatedFiles == null) {
			/* 128 */       this.log.warn("Directory '" + dirName + "' does not exist.");
			/* 129 */       return;
			/*     */     }
			/*     */ 
			/* 132 */     long latestMod = lastDate;
			/* 133 */     for (File updFile : updatedFiles) {
			/* 134 */       long lm = updFile.lastModified();
			/* 135 */       latestMod = (lm > latestMod) ? lm : latestMod;
			/*     */     }
			/*     */ 
			/* 138 */     if (updatedFiles.length > 0)
			/*     */     {
			/* 140 */       this.log.info("Directory '" + dirName + "' contents updated, notifying listener.");
			/* 141 */       listener.filesUpdatedOrAdded(updatedFiles);
			/* 142 */     } else if (this.log.isDebugEnabled()) {
			/* 143 */       this.log.debug("Directory '" + dirName + "' contents unchanged.");
			/*     */     }
			/*     */ 
			/* 147 */     context.getJobDetail().getJobDataMap().put("LAST_MODIFIED_TIME", latestMod);
			/*     */   }
	
	protected File[] getUpdatedOrNewFiles(String dirName, long lastDate, long maxAgeDate)
	{
	  File dir = new File(dirName);
	  if ((!(dir.exists())) || (!(dir.isDirectory()))) {
	    return null;
	  }
	  
	  File[] files = dir.listFiles(new MyFileFilter(lastDate, maxAgeDate));
	  
	  if (files == null) {
	    files = new File[0];
	  }
	  return files;
	}
	
	private class MyFileFilter implements FileFilter{
		private long maxAgeDate;
		private long lastDate;
		public  MyFileFilter(long lastDate,long maxAgeDate){
			this.lastDate = lastDate;
			this.maxAgeDate = maxAgeDate;
		}
		public boolean accept(File pathname){
			System.out.println("pathname.lastModified():"+pathname.lastModified());
			System.out.println("lastDate:"+lastDate);
			System.out.println("maxAgeDate:"+maxAgeDate);
		      return ((pathname.lastModified() > lastDate) && (pathname.lastModified() < maxAgeDate));
		    }
	}
}
