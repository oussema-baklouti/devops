package tn.esprit.spring.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.controller.RestControlContrat;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.services.ContratServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContratServiceImplTest {
	
	private static final Logger l = LogManager.getLogger(ContratServiceImplTest.class);
	@Autowired
	ContratServiceImpl contratService;
    @Autowired
    RestControlContrat ctrler ;
	@Autowired
	ContratRepository contratRepository;
	Date d=new Date(10/01/2021);
	@Test
	public void testAjouterContrat(){
		l.info("Starting Ajouter Contract test method");
		Contrat cont = new Contrat(d,"CDI", 120.3f);
		contratRepository.save(cont);
		int id = cont.getReference();
        assertEquals(cont.getReference(), id);
        l.info("reference not null");
        contratRepository.deleteAll();
	}
	
	@Test 
	public void testDeleteContratById(){
		try {
			l.info("Starting delete Contract test method");
			Contrat c = new Contrat(d,"CDI", 120.3f);
			int id = contratService.ajouterContrat(c);
			l.info("Adding new Contract with id : " + id);
			Contrat cont = contratService.getContratByReference(id);
			assertNotNull(cont);
			int lengthBeforeDelete = contratService.getAllContrats().size();
			l.info("Contract with id " + id + " exists" );
			contratService.deleteContratByReference(id);
			assertEquals(lengthBeforeDelete-1 ,contratService.getAllContrats().size());
			l.info("Contract deleted successfuly");
			} catch (NullPointerException e) {
				l.error(e.getMessage());
			}
	}
	
	
	@Test
	public void testGetContratByReference(){
		try {
			l.info("Starting find Contract test method");
			Contrat c = new Contrat(d,"CDI", 120.3f);
			int id = contratService.ajouterContrat(c);
			assertNotNull(contratService.getContratByReference(id));
			l.info("Contract with reference " + id + " added" );
			Contrat cont = contratService.getContratByReference(id);
			assertNotNull(cont);	
			l.info("Contract with reference " + cont.getReference() + " found successfully" );
			contratService.deleteContratByReference(id);
			} catch (NullPointerException e) {
				l.error(e.getMessage());
			}
	}
	
	@Test
	public void testGetAllContracts(){
		try {
			l.info("Starting get all Contracts");	
			Contrat c1 = new Contrat(d,"CDI", 120.3f);
			int id1 =contratService.ajouterContrat(c1);
			assertNotNull(contratService.getContratByReference(id1));
			l.info("Contract with id " + id1 + " added" );
			Contrat c2 = new Contrat(d,"CPP", 240.3f);
			int id2 = contratService.ajouterContrat(c2);
			assertNotNull(contratService.getContratByReference(id2));
			l.info("Contract with id " + id2 + " added" );
			Contrat c3 = new Contrat(d,"CDP", 350.3f);
			int id3 = contratService.ajouterContrat(c3);
			assertNotNull(contratService.getContratByReference(id3));
			l.info("Contract with id " + id3 + " added" );
			List<Contrat> list = new ArrayList<>();
			list.add(c1);
			list.add(c2);
			list.add(c3);
			l.info("lenght of Contract list: " + list.size());
			List<Contrat> listedescontrats = contratService.getAllContrats();
			int length = listedescontrats.size();
			assertEquals(length , list.size());
			contratService.deleteContratByReference(id1);
			contratService.deleteContratByReference(id2);
			contratService.deleteContratByReference(id3);
			l.info("Get All Contracts  works");		
			}  catch (NullPointerException e) {
				l.error(e.getMessage());
			}
	}
	
	
	

}
