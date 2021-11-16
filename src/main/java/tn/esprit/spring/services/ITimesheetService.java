package tn.esprit.spring.services;

import java.util.Date;



public interface ITimesheetService {
	
	
	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin);
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId);

}
