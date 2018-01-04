package com.github.marcinseweryn.dao;

import com.github.marcinseweryn.model.Uploads;

public interface UploadsDAO {
	
	public void save(Uploads file);
	
	public Uploads read(String fileName);
	
	public void updateFileByFileName(String fileName, byte[] file);
	
	public Uploads findUploadsByFileName(String fileName);
	
}
