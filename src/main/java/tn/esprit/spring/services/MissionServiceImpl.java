package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class MissionServiceImpl implements MissionService{

@Autowired 
MissionRepository missionRepository;
@Autowired 
EmployeRepository employeRespository;
@Autowired
DepartementRepository deptRepoistory;
@Autowired
TimesheetRepository timesheetRepository;

public int addMission(Mission mission) {
	missionRepository.save(mission);
	return mission.getId();
}


public List<Mission> getAllMission(){
	return (List<Mission>) missionRepository.findAll();
	
}
public Mission getMissionById( int missionId) {
	Mission missionManagedEntity = new Mission();

	Optional<Mission> miss=missionRepository.findById(missionId);
	if(miss.isPresent()) {
		missionManagedEntity =miss.get();
	}
	return missionManagedEntity;
}

public void deleteMissionById(int missionId) {
	Mission missionManagedEntity = new Mission();

	Optional<Mission> miss=missionRepository.findById(missionId);
	if(miss.isPresent()) {
		missionManagedEntity =miss.get();
	}	
	missionRepository.delete(missionManagedEntity);
	}

public void mettreAjourMissionName(String name ,int missionId  ) {
	Mission missionManagedEntity = new Mission();

	Optional<Mission> miss=missionRepository.findById(missionId);
	if(miss.isPresent()) {
		missionManagedEntity =miss.get();
	}	
	missionManagedEntity.setName(name);
	missionRepository.save(missionManagedEntity);
}

@Transactional 
public void affecterMissionADepartement(int missionId, int depId) {
	Mission missionManagedEntity = new Mission();
	Departement depManagedEntity = new Departement();
	Optional<Mission> miss=missionRepository.findById(missionId);
	if(miss.isPresent()) {
		missionManagedEntity =miss.get();
	}			

	Optional<Departement> dep=deptRepoistory.findById(depId);
	if(dep.isPresent()) {
		 depManagedEntity =dep.get();
	}
	
	missionManagedEntity.setDepartement(depManagedEntity);
	missionRepository.save(missionManagedEntity);
	
}
public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
	return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
}
		

public List<Employe> getAllEmployeByMission(int missionId) {
	return timesheetRepository.getAllEmployeByMission(missionId);
}

	
	
}


