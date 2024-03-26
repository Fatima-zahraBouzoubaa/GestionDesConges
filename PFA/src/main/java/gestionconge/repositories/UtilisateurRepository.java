package gestionconge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gestionconge.dao.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	
	
	
	void delete(Utilisateur utilisateur);
	
	List<Utilisateur> findAll();
	
	Utilisateur findOne(int id);
	
	
	@Query("SELECT u from Utilisateur u where nom= :nom")
	Utilisateur findByName(@Param("nom") String nom);
	
	@Query("SELECT u from Utilisateur u where email like :email%")
	Utilisateur findOneByEmail(@Param("email") String email);
	
	@Query("SELECT u from Utilisateur u where email like :email and password like :password")
	Utilisateur findOneByEmailAndPassword(@Param("email") String email ,@Param("password") String password);
	
	@SuppressWarnings("unchecked")
	Utilisateur save(Utilisateur utilisateur);
}
