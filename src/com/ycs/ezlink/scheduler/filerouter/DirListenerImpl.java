package com.ycs.ezlink.scheduler.filerouter;

import java.io.File;
import java.util.Date;

import org.quartz.jobs.DirectoryScanListener;

public class DirListenerImpl implements DirectoryScanListener{

	@Override
	public void filesUpdatedOrAdded(File[] fileChanged) {
		for (File file : fileChanged) {
			System.out.println(new Date(file.lastModified()) +" "+ file.getName());
		}
	}

}
