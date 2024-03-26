package gestionconge.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import gestionconge.dao.Conge;
public interface CongesService {
	
	Conge create(Conge Conge);
	List<Conge> delete (int idConge );
	
	List<Conge> findAll();
	
	Conge findById(int idConge);
	
	List<Conge> findByUtilisateur(int id);

	List<Conge> findByStatus(String status);
    List<Conge> findByStatusAndUtilisateur(String status, int id);
    public int getDaysCount(String date_debut, String date_fin);
	List<Conge> update(Conge conge);
    boolean overlapDateRange(int id, String dateDebut, String dateFin);
    List<Conge> findByDayAndStatus(String status, String date);
    List<Conge> findApprovedCongesByDate(Date date);
    List<Conge> findHistoriqueConges (int id);
}
