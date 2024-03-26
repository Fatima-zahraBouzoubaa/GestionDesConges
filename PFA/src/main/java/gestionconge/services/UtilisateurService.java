package gestionconge.services;

import java.util.List;
import gestionconge.dao.Utilisateur;
public interface UtilisateurService {
	
	
	
	List<Utilisateur> findAll();
	
	Utilisateur findById(int id);
	
	Utilisateur findOneByEmail (String email);
	
	Utilisateur Edit(Utilisateur utilisateur);

	Utilisateur update(Utilisateur utilisateur);
	
	Utilisateur findOneByEmailAndPassword(String email, String password);
	

}
