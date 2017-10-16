package com.github.marcinseweryn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.PatientDAO;
import com.github.marcinseweryn.model.Patient;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientDAO patientDAO;
	
	@Autowired
	UserService userService;
	
	@Override
	public void updatePatient(Integer ID, Patient patient) {
		String columns = "";
		
		List<Integer> userID = new ArrayList<Integer>();
		userID.add(ID);
		
		userService.updateUsers(userID, patient);
		
		if(patient.getSensitizations() != null){
			if(!patient.getSensitizations().equals("")){
				columns += "sensitizations = '" + patient.getSensitizations() + "' ,";
			}
		}
		if(patient.getSolidDrugs() != null){
			if(!patient.getSolidDrugs().equals("")){
				columns += "solidDrugs = '" + patient.getSolidDrugs() + "' ,";
			}
		}
		if(patient.getChronicDiseases() != null){
			if(!patient.getChronicDiseases().equals("")){
				columns += "chronicDiseases = '" + patient.getChronicDiseases() + "' ,";
			}
		}

		
		if(columns.length() > 0){
			columns = columns.substring(0, columns.length() - 1);
		}
		
		if(!columns.equals("")){
			patientDAO.updatePatient(ID, columns);
		}

	}		
		
}
