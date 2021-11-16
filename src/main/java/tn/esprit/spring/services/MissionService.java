package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;

public interface MissionService {

	
	public int addMission(Mission mission);
	public void deleteMissionById(int missionId);
	public Mission getMissionById( int missionId) ;
	public void affecterMissionADepartement(int missionId, int depId);
	
	public List<Mission> findAllMissionByEmployeJPQL(int employeId);
	public List<Employe> getAllEmployeByMission(int missionId);
	public List<Mission> getAllMission();
	public void mettreAjourMissionName(String name ,int missionId  );
}
