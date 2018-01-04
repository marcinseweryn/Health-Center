package com.github.marcinseweryn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.UploadsDAO;
import com.github.marcinseweryn.model.Uploads;

@Service
public class UploadsServiceImpl implements UploadsService {

	@Autowired
	private UploadsDAO uploadsDAO;
	
	@Override
	public void save(String fileName, byte[] file) {

		if(uploadsDAO.read(fileName) != null){
			uploadsDAO.updateFileByFileName(fileName, file);
		}else{	
			Uploads uploads = new Uploads();
			uploads.setFile(file);
			uploads.setFileName(fileName);
			
			uploadsDAO.save(uploads);
		}
	}

	@Override
	public Uploads read(String fileName) {
		
		return uploadsDAO.read(fileName);
	}

	@Override
	public void updateFileByFileName(String fileName, byte[] file) {
		
		uploadsDAO.updateFileByFileName(fileName, file);
	}

	@Override
	public Uploads findUploadsByFileName(String fileName) {

		return uploadsDAO.findUploadsByFileName(fileName);
	}

}
