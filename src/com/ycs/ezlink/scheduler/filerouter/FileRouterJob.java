package com.ycs.ezlink.scheduler.filerouter;

import java.io.File;
import java.io.InputStream;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ycs.ezlink.scheduler.sftp.SftpReceiver;
import com.ycs.ezlink.scheduler.sftp.SftpSender;

@DisallowConcurrentExecution 
public class FileRouterJob implements Job  {
	
	private String fileType;
	
    	 private static Logger logger = Logger.getLogger(FileRouterJob.class);
    	 private static boolean alreadyRunning = false;
    	 
    	 public void execute(JobExecutionContext context)throws JobExecutionException {
    		 if(alreadyRunning){logger.debug("-----"+getClass().getName()+" alreadyRunning-----"); return ;}
    		// alreadyRunning = true;
    		 try {
				logger.debug("------"+getClass().getName()+" start------" );
				  
				fileType= "PAOut";
				 logger.info("Job Type: "+fileType);
				 InputStream in = FileRouterJob.class.getClassLoader().getResourceAsStream("filerouter.xml");
				 Document doc = new SAXReader().read(in);
				 Node root = doc.selectSingleNode("root");
				 String beSftpIp = null;
				 String beSftpId = null;
				 String beSftpPass = null;
				 String be_app_path = null;
				 String localPath = null;
				 if(root.selectSingleNode("be_sftp_ip") != null){
					 beSftpIp = root.selectSingleNode("be_sftp_ip").getText();
					beSftpId = root.selectSingleNode("be_sftp_id").getText();
					beSftpPass = root.selectSingleNode("be_sftp_pass").getText();
				 }
				 localPath = root.selectSingleNode("local_path").getText();
				 be_app_path = root.selectSingleNode("be_app_path").getText();
				 
				 Node filejob = root.selectSingleNode("filejob[@name='"+fileType+"']");
				 String direction = filejob.selectSingleNode("direction").getText();
				 String remote_sftp_ip = null;
				 String remote_sftp_id = null;
				 String remote_rel_path = null;
				 String remote_sftp_pass = null;
				 if(filejob.selectSingleNode("remote_sftp_ip")!= null){
					 remote_sftp_ip = filejob.selectSingleNode("remote_sftp_ip").getText();
					 remote_sftp_id = filejob.selectSingleNode("remote_sftp_id").getText();
					 remote_sftp_pass = filejob.selectSingleNode("remote_sftp_pass").getText();
					 remote_rel_path = filejob.selectSingleNode("remote_rel_path").getText();
				 }
				 Element filterNode = (Element) filejob.selectSingleNode("filter");
				 String filtertext = filterNode.getText();
				 String filterday = filterNode.attributeValue("day");
				 String filtertype = filterNode.attributeValue("type");
				 String be_rel_path = filejob.selectSingleNode("be_rel_path").getText();
				 String fileregex = null;	
				 if(filtertype.equals("regex")){
					 Calendar c = Calendar.getInstance();
					 if(filterday.equals("prev")){
						 c.add(Calendar.DATE, -1);
					 }else  if(filterday.equals("today")){
						 //no action
					 }
					 fileregex = String.format(filtertext, c);
				 }else if(filtertype.equals("class")){
					 IFileFilter fileFilter = (IFileFilter) Class.forName(filtertext).newInstance();
					 filtertext = fileFilter.getFileFilterText();
					// throw new UnsupportedOperationException();
				 }
				  
				 System.out.println(beSftpIp);
				 //action
				 int status = 0;
				 String absLocalPath = localPath +"/"+be_rel_path;
				 File fParentPath =new File(absLocalPath);
				 if(!fParentPath.exists()){
					 logger.error("localdir could not be found. Please create "+absLocalPath);
					 status = -11;
				 }
				 if(status == 0)
				 if(direction.equals("in")   ){
					 if(remote_sftp_ip != null && !remote_sftp_ip.isEmpty()){
						 //get from remote
						 status = SftpReceiver.getFileSFTP(fParentPath.getAbsolutePath(),fileregex, remote_sftp_id, remote_sftp_ip, remote_sftp_pass, remote_rel_path);
					 }
					 if(status == 0)
					 if(be_rel_path != null && be_rel_path.length() >0){
						 status = SftpSender.sendFileSFTP(fParentPath.getAbsolutePath(), fileregex, beSftpId, beSftpIp, beSftpPass, be_app_path+"/"+be_rel_path);
						 if(status == 0){
							 //deleting local temp files
							 FileUtils.cleanDirectory(fParentPath);
						 }
					 }else{
						 logger.error("backend relative path be_rel_path not defined");
					 }
				 }else if(direction.equals("out")){
					 if(be_rel_path != null && be_rel_path.length() >0){
						 status = SftpReceiver.getFileSFTP(fParentPath.getAbsolutePath(), fileregex, beSftpId, beSftpIp, beSftpPass, be_app_path+"/"+be_rel_path);
					 }
					 if(status == 0)
					 if(remote_sftp_ip != null && !remote_sftp_ip.isEmpty()){
						 //send to remote
						 status = SftpSender.sendFileSFTP(fParentPath.getAbsolutePath(),fileregex, remote_sftp_id, remote_sftp_ip, remote_sftp_pass, remote_rel_path);
						 if(status == 0){
							 FileUtils.cleanDirectory(fParentPath);
						 }
					 }else{
						 logger.error("remote remote_sftp_ip must be defined");
					 }
				 }else{
					 logger.error("direction must be either of in/out instead it is found:"+direction);
				 }
				 
				 logger.debug("process ended with status="+status );
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
    		 
    		 
    		// alreadyRunning = false;
    		 logger.debug("---"+getClass().getName()+" end---");
    	 }
    	 
    	 public static void main(String[] args) throws JobExecutionException {
    		 new FileRouterJob().execute(null);
    	 }

		public String getFileType() {
			return fileType;
		}

		public void setFileType(String jobType) {
			this.fileType = jobType;
		}
    	 
}
