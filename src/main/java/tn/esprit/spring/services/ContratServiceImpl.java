package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;

@Service
public class ContratServiceImpl implements IContratService {
	
	@Autowired
	ContratRepository repo ;

	@Override
	public int ajouterContrat(Contrat c) {
		repo.save(c);
		return c.getReference();
	}

	@Transactional
	public void deleteContratByReference(int ref) {
		Contrat contratManagedEntity = new Contrat();
		Optional<Contrat> e = repo.findById(ref);
		if(e.isPresent()) {
		contratManagedEntity = e.get();}

		repo.delete(contratManagedEntity);
		
	}

	@Override
	public Contrat getContratByReference(int ref) {
		Contrat contratManagedEntity = new Contrat();
		Optional<Contrat> c = repo.findById(ref);
		if(c.isPresent()) {
			contratManagedEntity = c.get();}
		return contratManagedEntity;
	}

	@Override
	public List<Contrat> getAllContrats() {
	
		return (List<Contrat>) repo.findAll();
	}
	

}
