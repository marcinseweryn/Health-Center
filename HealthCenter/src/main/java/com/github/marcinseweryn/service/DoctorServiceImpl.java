package com.github.marcinseweryn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.DoctorDAO;
import com.github.marcinseweryn.model.Doctor;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorDAO doctorDAO;
	
	@Override
	public List<Integer> findDoctorsIDsBySpecialization(String specialization) {
		
		return doctorDAO.findDoctorsIDsBySpecialization(specialization);
	}

	@Override
	public List<Doctor> findDoctors(Doctor doctor) {
		String where = "";
		
		if(doctor.getID() != null){
			if(!doctor.getID().equals("")){
				where += " d.ID = '" + doctor.getID() + "' and "; 
			}
		}
		if(doctor.getName() != null){
			if(!doctor.getName().equals("")){
				where += " d.name = '" + doctor.getName() + "' and ";
			}
		}
		if(doctor.getSurname() != null){
			if(!doctor.getSurname().equals("")){
				where += " d.surname = '" + doctor.getSurname() + "' and ";
			}
		}
		if(doctor.getGender() != null){
			if(!doctor.getGender().equals("")){
				where += " d.gender = '" + doctor.getGender() + "' and ";
			}
		}
		if(doctor.getCity() != null){
			if(!doctor.getCity().equals("")){
				where += " d.city = '" + doctor.getCity() + "' and ";
			}
		}
		if(doctor.getInformation() != null){
			if(!doctor.getInformation().equals("")){
				where += " d.information = '" + doctor.getInformation() + "' and ";
			}
		}
		if(doctor.getSpecialization_1() != null){
			if(!doctor.getSpecialization_1().equals("")){
				where += " d.specialization_1 = '" + doctor.getSpecialization_1() + "' or ";
			}
		}
		if(doctor.getSpecialization_2() != null){
			if(!doctor.getSpecialization_2().equals("")){
				where += " d.specialization_2 = '" + doctor.getSpecialization_2() + "' or ";
			}
		}
		if(doctor.getSpecialization_3() != null){
			if(!doctor.getSpecialization_3().equals("")){
				where += " d.specialization_3 = '" + doctor.getSpecialization_3() + "' or ";
			}
		}
		
		if(where.length() > 0){
			String temp = "WHERE";
			temp += where;
			where = temp;
			where = where.substring(0, where.length() - 4);
		}
		
		return doctorDAO.findDoctors(where);
	}

	@Override
	public void addDoctor(Doctor doctor) {
		
		if(!doctor.getPesel().equals("")){
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(doctor.getPassword());
			
			doctor.setEnabled("1");
			doctor.setPassword(hashedPassword);
			doctorDAO.addDoctor(doctor);
		}

	}

	@Override
	public void updateDoctorByID(Doctor doctor, Integer ID) {
		String columns = "";
		
		if(doctor.getSpecialization_1() != null){
			if(!doctor.getSpecialization_1().equals("")){
				columns += "d.specialization_1 = '" + doctor.getSpecialization_1() + "' ,"; 
			}
		}
		if(doctor.getSpecialization_2() != null){
			if(!doctor.getSpecialization_2().equals("")){
				columns += "d.specialization_2 = '" + doctor.getSpecialization_2() + "' ,";
			}
		}
		if(doctor.getSpecialization_3() != null){
			if(!doctor.getSpecialization_3().equals("")){
				columns += "d.specialization_3 = '" + doctor.getSpecialization_3() + "' ,";
			}
		}
		if(doctor.getInformation() != null){
			if(!doctor.getInformation().equals("")){
				columns += "d.information = '" + doctor.getInformation() + "' ,";
			}
		}
		
		
		if(columns.length() > 0){
			columns = columns.substring(0, columns.length() - 1);
		}
		
		if(!columns.equals("")){
			doctorDAO.updateDoctorByID(columns, ID);
		}
		
	}


	@Override
	public Doctor findDoctorByID(Integer ID) {
		
		return doctorDAO.findDoctorByID(ID);
	}

}
