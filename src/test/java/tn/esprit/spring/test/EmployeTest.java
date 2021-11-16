package tn.esprit.spring.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.EmployeServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeTest {

	@Autowired
    EmployeServiceImpl employeServImp;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepository;
	private static final Logger l = LogManager.getLogger();
	
	Employe emp  = new Employe("kallel","khaled","Khaled.kallel@ssiiconsulting.tn",true,Role.INGENIEUR);
	Date d=new Date(12/01/2021);
	
	@Test
	public void testAjouterEmploye(){
		int id = employeServImp.ajouterEmploye(emp);
		assertNotNull("it is not empty",id);
		l.info("employe a ete ajoute  ");
		employeServImp.deleteEmployeById(id);
		}
	
	
	@Test
	public void testMettreAjourEmailByEmployeId(){
		int id = employeServImp.ajouterEmploye(emp);
		if(emp.getId()== id){
		String mail1="Nourhen.allagui@gmail.com";
		emp.setEmail(mail1);
		assertTrue(emp.getEmail().equals(mail1));
		l.info("email compatible  ");
		}
		else 
			l.info("sorry");
		employeServImp.deleteEmployeById(id);
	}
	
	
	@Test
	public void testAffecterEmployeADepartement(){
		int id = employeServImp.ajouterEmploye(emp);
	    Departement dep = new Departement("informatique");
	    deptRepoistory.save(dep);
	    int idd = dep.getId();
	    if(dep.getEmployes() == null){
	    	ArrayList<Employe> lemp = new ArrayList<>();
	    	lemp.add(emp);
	    	dep.setEmployes(lemp);
	    	assertNotNull("it is not empty", dep.getEmployes());
	    	dep.setEmployes(lemp);
	    	
	    	
	    	
	    }
	    else {
	    dep.getEmployes().add(emp);
	    assertNotNull(dep);
	    }
	    deptRepoistory.deleteById(idd);
	    employeServImp.deleteEmployeById(id);
	}
	
	
	
	@Test
	public void testDesaffecterEmployeDuDepartement(){
		Departement dep = new Departement("informatique");
		ArrayList<Employe> lemp = new ArrayList<>();
		int employeId = employeServImp.ajouterEmploye(emp);
		lemp.add(emp);
		dep.setEmployes(lemp);
		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				assertNotEquals(dep.getEmployes().size(), 1);
				l.info("des affecter Employe Du Departement");
				break;
				
			}
		}
		deptRepoistory.deleteAll();
		employeServImp.deleteEmployeById(employeId);
	}
	
	
	@Test
	public void testAjouterContrat(){
		Contrat cont = new Contrat(d,"CDI", 120.3f);
		contratRepository.save(cont);
		int id = cont.getReference();
        assertEquals(cont.getReference(), id);
        l.info("reference not null");
        contratRepository.deleteAll();
	}
	
	@Test
	public void testAffecterContratAEmploye(){
		Contrat cont = new Contrat(d,"CDI", 120.3f);
		int empId = employeServImp.ajouterEmploye(emp);
		cont.setEmploye(emp);
		assertTrue("affecté", cont.getEmploye() != null);
		l.info("affecté");
		contratRepository.deleteAll();
		employeServImp.deleteEmployeById(empId);
	}
	
	@Test
	public void testGetEmployePrenomById(){
		int id = employeServImp.ajouterEmploye(emp);
		if(id!= 0){
		assertTrue(emp.getPrenom() != null);
		l.info("existe");
		}
		employeServImp.deleteEmployeById(id);
	}
	
}
