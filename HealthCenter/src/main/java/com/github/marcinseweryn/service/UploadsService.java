package com.github.marcinseweryn.service;

import com.github.marcinseweryn.model.Uploads;

public interface UploadsService {

	public void save(String fileName, byte[] file);
	
	public Uploads read(String fileName);
}
