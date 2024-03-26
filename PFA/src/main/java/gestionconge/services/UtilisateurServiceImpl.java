package gestionconge.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gestionconge.dao.Utilisateur;
import gestionconge.repositories.UtilisateurRepository;


@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	
	@Override
	public List<Utilisateur> findAll() {
		return utilisateurRepository.findAll();
	}

	@Override
	public Utilisateur findById (int id) {
		return utilisateurRepository.findOne(id);
	}

	@Override
	public Utilisateur Edit(Utilisateur utilisateur) {
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public Utilisateur findOneByEmail(String email) {
		return utilisateurRepository.findOneByEmail(email);
	}

	@Override
	public Utilisateur update(Utilisateur utilisateur) {
		utilisateurRepository.save(utilisateur);
		return null;
	}

	@Override
	public Utilisateur findOneByEmailAndPassword(String email, String password) {
		return utilisateurRepository.findOneByEmailAndPassword(email, password);
	}
}
