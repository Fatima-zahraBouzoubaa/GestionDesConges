package gestionconge.services;

import java.util.Date;
import java.util.List;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import gestionconge.dao.Utilisateur;
import gestionconge.dao.Conge;
import gestionconge.repositories.UtilisateurRepository;
import gestionconge.repositories.CongeRepository;

import java.util.Arrays;
import java.util.Calendar;

@Service
public class CongesServiceImpl implements CongesService{
	
	@Autowired
	private CongeRepository congeRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
		
	@Override
	public Conge create(Conge Conge) {
		return congeRepository.save(Conge);
	}

	@Override
	public List<Conge> delete(int id) {
		congeRepository.delete(congeRepository.findOne(id));
		return congeRepository.findAll();
	}

	@Override
	public List<Conge> findAll() {
		return congeRepository.findAll();
	}

	@Override
	public Conge findById(int id) {
		return congeRepository.findOne(id);
	}
	@Override
	public List<Conge> findByStatusAndUtilisateur(String status, int id) {
		return congeRepository.findByStatusAndUtilisateur(status, id);
	}

	@Override
	public List<Conge> findByUtilisateur(int id) {

		return congeRepository.findByUtilisateur(id);
	}

	@Override
	public List<Conge> findByStatus(String status) {

		return congeRepository.findByStatus(status);
	}
	@Override
	public List<Conge> findByDayAndStatus(String status, String date) {
		return congeRepository.findAllByDayAndStatus(status, date);
	}


	@Override
	public int getDaysCount(String dateDebut, String dateFin) {
	    int numberOfDays = 0;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	        Date debut = sdf.parse(dateDebut);
	        Date fin = sdf.parse(dateFin);

	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(debut);

	        while (calendar.getTime().before(fin)) {
	            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
	                numberOfDays++;
	            }
	            calendar.add(Calendar.DATE, 1);
	        }

	        int dayOfWeekDebut = calendar.get(Calendar.DAY_OF_WEEK);
	        int dayOfWeekFin = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	        if (dayOfWeekDebut != Calendar.SATURDAY && dayOfWeekDebut != Calendar.SUNDAY
	                || dayOfWeekFin != Calendar.SATURDAY && dayOfWeekFin != Calendar.SUNDAY) {
	            numberOfDays++;
	        }
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    return numberOfDays;
	}

	@Override
	public List<Conge> update(Conge conge) {

			 congeRepository.save(conge);
			 if(conge.getType().equals("Annuel") && conge.getStatus().equals("approved")) {
				 Utilisateur utilisateur = conge.getUtilisateur();
				 if(utilisateur.getSolde_conge() < conge.getDuree()) utilisateur.setSolde_conge(0);
				 else utilisateur.setSolde_conge(utilisateur.getSolde_conge() - conge.getDuree());
				utilisateurRepository.save(utilisateur);
			 }
			 return congeRepository.findByStatus("pending");
	}

	@Override
	public boolean overlapDateRange(int id, String dateDebut, String dateFin) {

			return (congeRepository.overlapDateRange(id, dateDebut, dateFin).size() > 0);
		
	}

	@Override
	public List<Conge> findApprovedCongesByDate(Date date) {
		return congeRepository.findApprovedCongesByDate(date);
		
	}

	@Override
	public List<Conge> findHistoriqueConges(int id) {
		return congeRepository.findHistoriqueConges(id);
	}


}
