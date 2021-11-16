package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Contrat;

public interface IContratService {
	public int ajouterContrat(Contrat c);
    public void deleteContratByReference(int ref);
    public Contrat getContratByReference(int ref);
    public List<Contrat> getAllContrats();
}
