package com.ycs.ezlink.scheduler.sftp;

import java.io.File;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;
import com.ycs.ezlink.util.EzLinkConstant;

public class SftpSender {
	private static Logger logger = Logger.getLogger(SftpSender.class);
	/**
	 * @param parentPath 
	 * @param filename Filename without path eg. txnFile20110803* it will match txnFile20110803_1.txt txnFile20110803_2.txt
	 * @param user
	 * @param host
	 * @param password
	 * @param remoteFolder
	 * @return
	 */
	public static int sendFileSFTP(String localParentDir, String filename, String user, String host, String password, String remoteFolder)  {
		logger.error("SFTP file transferred start");
		JSch jsch = new JSch();
		Session session = null;
		int error = 0;
		try {
		    session = jsch.getSession(user,host , 22);
		    session.setConfig("StrictHostKeyChecking", "no");
		    session.setPassword(password);
//		    UserInfo ui=new MyUserInfo();
//		      session.setUserInfo(ui);
		    session.connect();

		    Channel channel = session.openChannel("sftp");
		    channel.connect();
		    ChannelSftp sftpChannel = (ChannelSftp) channel;
		    logger.debug("sending file from dir: "+localParentDir+", filename:"+filename+", to "+remoteFolder);
		    SftpProgressMonitor monitor = new MyProgressMonitor();
		    sftpChannel.cd(remoteFolder);
		    sftpChannel.lcd(localParentDir);
			sftpChannel.put(filename, "." ,monitor, ChannelSftp.OVERWRITE);
			sftpChannel.exit();
//		    session.disconnect();
		    logger.error("SFTP file transferred successfully ["+filename+"]");
		} catch (JSchException e) {
		    logger.error("File transfer Exception ["+filename+"], ["+localParentDir+"],["+remoteFolder+"]",e);  //To change body of catch statement use File | Settings | File Templates.
		    error = -1;
		} catch (SftpException e) {
			logger.error("SFTP Exception ["+filename+"], ["+localParentDir+"],["+remoteFolder+"]",e);
			error = -2;
//		} catch (FileNotFoundException e) {
//			logger.error("File not found to transfer"+filename);
		}finally{
			 session.disconnect();
		}
		return error;
	}
	
	
	/**
	 * @param fileWithPath should be complete file with localdir path
	 * @param user
	 * @param host
	 * @param password
	 * @param remoteFolder
	 * @return
	 */
	public static int sendFileSFTP( String fileWithPath, String user, String host, String password, String remoteFolder)  {
		ResourceBundle rb = ResourceBundle.getBundle(EzLinkConstant.PATH_CONFIG_PROPERTY_FILE_NAME);
		File f = new File(fileWithPath);
		return sendFileSFTP(f.getParent(), f.getName(), user, host, password, remoteFolder);	
	}
}
