package gestionconge.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestionconge.dao.Conge;
import gestionconge.dao.Utilisateur;
import gestionconge.services.UtilisateurService;
@CrossOrigin(origins= "*")
@RestController
@RequestMapping({"/api/utilisateur"})

public class UtilisateurController {

		@Autowired
		private UtilisateurService utilisateurService;
		
		@GetMapping(path = {"/{id}"})
		public Utilisateur findOne(@PathVariable("id") int id) {
			return utilisateurService.findById(id);
		}
		@PutMapping(path= {"/{id}"})
		public Utilisateur updateSoldeConge(@PathVariable("id") int id, @RequestBody float solde_conge) {
			 Utilisateur utilisateur= utilisateurService.findById(id);
			 utilisateur.setSolde_conge(solde_conge);
			 return utilisateurService.update(utilisateur);
			}

		@GetMapping(path = {"/{email}/email"})
		public Utilisateur findOneByEmail(@PathVariable("email") String email) {
			System.out.println(email);
			return utilisateurService.findOneByEmail(email);
		}
		@PostMapping
		public Utilisateur matchEmailAndPassword (@RequestBody Utilisateur utilisateur) {
			return utilisateurService.findOneByEmailAndPassword(utilisateur.getEmail(), utilisateur.getPassword());

			
		}
		
		@GetMapping
		public List<Utilisateur> findUtilisateur(){
			return utilisateurService.findAll();
		}
}
