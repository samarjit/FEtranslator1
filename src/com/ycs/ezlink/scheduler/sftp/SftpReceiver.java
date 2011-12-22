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

public class SftpReceiver {
	private static Logger logger = Logger.getLogger(SftpReceiver.class);
	
	/**
	 * @param localParentDir local directory path not ending with "/" eg. c:/localdir
	 * @param filename filename without path eg. txnFile20110803.txt
	 * @param user 
	 * @param host
	 * @param password
	 * @param remoteFolder eg. <strong>cd</strong> ${remoteFolder}
	 * @return
	 */
	public static int getFileSFTP(String localParentDir, String filename, String user, String host, String password, String remoteFolder)  {
		ResourceBundle rb = ResourceBundle.getBundle(EzLinkConstant.PATH_CONFIG_PROPERTY_FILE_NAME);
		logger.error("SFTP file receive start");
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
		    logger.debug("receiving file in dir: "+localParentDir+", filename:"+filename+", from "+remoteFolder);
		    SftpProgressMonitor monitor = new MyProgressMonitor();
		    sftpChannel.cd(remoteFolder);
		    sftpChannel.lcd(localParentDir);
			sftpChannel.get(filename, ".",monitor, ChannelSftp.OVERWRITE);
		    sftpChannel.exit();
//		    session.disconnect();
		    logger.error("SFTP file receive successfully ["+filename+"]");
		} catch (JSchException e) {
		    logger.error("File transfer Exception ["+filename+"], ["+localParentDir+"],["+remoteFolder+"]",e);  //To change body of catch statement use File | Settings | File Templates.
		    error = -1;
		} catch ( SftpException e) {
			logger.error("SFTP Exception ["+filename+"],["+localParentDir+"],["+remoteFolder+"]",e);
			error = -2;
//		} catch (FileNotFoundException e) {
//			logger.error("File not found to transfer"+filename);
		}finally{
			 
			 session.disconnect();
		}
		return error;
	}
	
	/**
	 * @param filename The complete filename which will be after receiving file.  <br/>
	 * <strong>c:\localrepo\txn20110802.txt</strong> The parentPath will be local directory: <strong>lcd</strong> c:\localrepo. <br/>
	 * File txn20110802.txt will be fetched from <strong>cd</strong> ${remoteFolder} 
	 * @param user
	 * @param host
	 * @param password
	 * @param remotFolder <strong>cd</strong> ${remoteFolder}
	 * @return
	 */
	public static int getFileSFTP( String filename, String user, String host, String password, String remotFolder)  {
		File f = new File(filename);
		return getFileSFTP(f.getParent(), f.getName(), user, host, password, remotFolder);
	}
}
