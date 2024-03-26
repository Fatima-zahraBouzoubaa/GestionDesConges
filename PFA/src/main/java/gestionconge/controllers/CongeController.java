package gestionconge.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gestionconge.dao.Conge;
import gestionconge.services.CongesService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping({"/api/absences"})
public class CongeController {
	@Autowired
    private CongesService Congeservice;
	
	@GetMapping(path = {"/{id}"})
    public Conge findOne(@PathVariable("id") int id){
        return Congeservice.findById(id); 
    }
	
	@GetMapping
    public List<Conge> findAll(){
		List<Conge> conge = Congeservice.findAll(); 
        return conge; 
    }
	
	@GetMapping(path = {"/status/{status}"})
    public List<Conge> findByStatus(@PathVariable("status") String status){
        return Congeservice.findByStatus(status); 
    }
	
	@GetMapping(path = {"/status/{status}/utilisateur/{code}"})
    public List<Conge> findByStatusAndUtilisateur(@PathVariable("status") String status, @PathVariable("code") int code){
        return Congeservice.findByStatusAndUtilisateur(status, code); 
    }
	
	@GetMapping(path = {"/Conge"})
    public List<Conge> findHistorique(){
		List<Conge> conge = Congeservice.findByStatus("approved"); 
		conge.addAll(findByStatus("declined")); 
        return conge;
    }
	

    @GetMapping(path = "/approvedCongesToday/{jour}/{mois}/{annee}")
    public List<Conge> findApprovedCongesByDate(
            @PathVariable("jour") int jour,
            @PathVariable("mois") int mois,
            @PathVariable("annee") int annee) {

        String dateString = String.format("%04d/%02d/%02d", annee, mois, jour);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return Congeservice.findApprovedCongesByDate(date);
    }
	@GetMapping(path = {"/status/{status}/date/{date}"})
    public List<Conge> findByDayAndStatus(@PathVariable("status") String status, @PathVariable("date") String date){
        return Congeservice.findByDayAndStatus(status, date); 
    }
	
	@GetMapping(path = {"/utilisateur/{code}"})
    public List<Conge> findByUtilisateur(@PathVariable("code") int code){
        return Congeservice.findByUtilisateur(code); 
    }
	
	@GetMapping(path = {"/utilisateur/{code}/debut/{dateDebut}/fin/{dateFin}"})
    public boolean findOverlapDateRangeByEmployee(@PathVariable("code") int code, @PathVariable("dateDebut") String dateDebut, @PathVariable("dateFin") String dateFin){
        return Congeservice.overlapDateRange(code, dateDebut, dateFin);
    }
	
	@GetMapping(path = {"/debut/{dateDebut}/fin/{dateFin}"})
    public int getDaysCount(@PathVariable("dateDebut") String dateDebut, @PathVariable("dateFin") String dateFin){
	 	return Congeservice.getDaysCount(dateDebut, dateFin);
    }
	
	@PostMapping
    public Conge create(@RequestBody Conge conge){
        return Congeservice.create(conge);
    }
	
	@PatchMapping(path = {"/{id}"})
	public List<Conge> update(@PathVariable("id") int id, @RequestBody Conge conge) {
		conge.setIdConge(id);
		return Congeservice.update(conge);
	}
	
	
	@PutMapping(path = {"/{id}"})
	public List<Conge> updateState(@PathVariable("id") int id, @RequestBody String status) {
		Conge conge = Congeservice.findById(id);
		conge.setStatus(status);
		return Congeservice.update(conge);
	}
	
	@DeleteMapping(path ={"/{id}"})
	public List<Conge> delete(@PathVariable("id") int id) {
		return Congeservice.delete(id);
	}
	
	@GetMapping(path = {"/historiqueConges/{id}"})
	public List<Conge> getHistoriqueConge(@PathVariable("id") int id){
		return Congeservice.findHistoriqueConges(id);
	}
}
