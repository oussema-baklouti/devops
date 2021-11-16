package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.services.IContratService;

@RestController
public class RestControlContrat {

	@Autowired
	IContratService contratService;
	
	@PostMapping("/addContrat")
	@ResponseBody
	public Contrat ajoutContrat(){
		Contrat c=new Contrat();
		contratService.ajouterContrat(c);
		return c;
	}
	
	@GetMapping("/retrieveAllContrats")
	@ResponseBody
	public List<Contrat> getAllContrats(){
		return contratService.getAllContrats();
		
	}
	
	@DeleteMapping("/effacerContratById/{idcontrat}") 
	@ResponseBody
	public void effacerContratById(@PathVariable("idcontrat")int contratId) {
		contratService.deleteContratByReference(contratId);
	}
	
	@GetMapping(value = "retrieveContratById/{idContrat}")
    @ResponseBody
	public Contrat getContratById(@PathVariable("idContrat") int contratId) {

		return contratService.getContratByReference(contratId);
	}
	

}
