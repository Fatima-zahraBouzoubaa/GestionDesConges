package gestionconge.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Utilisateur")

public class Utilisateur implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@Column(name = "email")
    private String email;
	@Column(name = "solde_conge")
	private float solde_conge;
	@Column(name = "fonction")
	private String fonction;
	@Column(name = "password")
	private String password;
	public String getFonction() {
		return fonction;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public float getSolde_conge() {
		return solde_conge;
	}
	public void setSolde_conge(float solde_conge) {
		this.solde_conge = solde_conge;
	}
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch =FetchType.LAZY) 
	private List<Conge> conges;
	
	@ManyToOne
	@JoinColumn(name="role", nullable=false)
	private Role role;
	

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Conge> getConges() {
		return conges;
	}
	public void setConges(List<Conge> conges) {
		this.conges = conges;
	}
	
			
}
