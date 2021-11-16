package tn.esprit.spring.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.DepartmentServicelmpl;
import tn.esprit.spring.services.MissionServiceImpl;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MissionServiceImplTest {
	private static final Logger l = LogManager.getLogger(MissionServiceImpl.class);
	
	@Autowired
	MissionServiceImpl missionservice;
	@Autowired
	DepartmentServicelmpl ds;
	
	
	
	@Test
	//CRUD test 
	public void testAddMission() throws ParseException {
		l.info("start adding test");
		Mission mission = new Mission("marketing mission" , "marketing our product to customers in foreign countries");
		int Id = missionservice.addMission(mission);
		Mission miss =missionservice.getMissionById(Id);
		assertNotNull(miss);
		l.info("Entreprise with id(" + mission.getId() +") , Name(" + mission.getName() + " added successfuly");
		missionservice.deleteMissionById(Id);		
		
		
		
	}
	@Test 
	public void testDeleteMissionById() {
	Mission mission= new Mission("dummy mission" , "dummy mission");	
	int Id=	missionservice.addMission(mission);
	assertNotNull(missionservice.getMissionById(Id));
	l.info("Mission with id " + Id + " exists" );
	int lengthBeforeDelete = missionservice.getAllMission().size();
	missionservice.deleteMissionById(Id);
	assertEquals(lengthBeforeDelete-1 , missionservice.getAllMission().size());
	l.info("Mission deleted successfuly");

	}
	@Test
	public void testGetMissionById(){

		Mission mission = new Mission("dummy mission","dummy mission");
		int Id = missionservice.addMission(mission);
		assertNotNull(missionservice.getMissionById(Id));
		l.info("Mission with id " + Id + " exists" );
		Mission miss= missionservice.getMissionById(Id);
		assertNotNull(miss);	
		l.info("Mission with id " + Id + " was found successfuly : id(" + miss.getId() 
		+") , Name(" + miss.getName() );
		missionservice.deleteMissionById(Id);
		}
@Test
public void testAffecterMissionADepartement() {
	Mission miss= new Mission("mission", "mission description");
	miss.setId(1);
    Departement dep = new Departement("informatique");
    dep.setId(1);
    if(dep.getMissions()== null){
    	ArrayList<Mission> temp = new ArrayList();
    	temp.add(miss);
    	dep.setMissions(temp);
    	assertNotNull("it is not empty", dep.getMissions());
    	l.info("cc " +dep.getEmployes());
    	dep.setMissions(temp);
    	
    	
    	
    }
    else {
    dep.getMissions().add(miss);
    assertNotNull(dep);
    ds.deleteDepartementById(dep.getId());
    l.debug("good " +dep.getEmployes());
    }
}
}
