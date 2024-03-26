package gestionconge.dao;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@Table(name = "Conge")
public class Conge implements Serializable{
	private static final long serialVersionUID=1L;
	
	
	@Id
    @Column(name = "idConge")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idConge;
	@Column(name="dateDemande")
	private Date dateDemande;
	@Column(name="dateDebut")
	private Date dateDebut;
	@Column(name="dateFin")
	private Date dateFin;
	@Column (name="commentaire")
	private String commentaire;
	@Column (name="status")
	private String status;
	@Column(name = "type")
	private String type;
	@Column(name = "duree")
	private float duree;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne
    @JoinColumn(name="id", nullable=false)
	private Utilisateur utilisateur;

	public int getIdConge() {
		return idConge;
	}

	public void setIdConge(int idConge) {
		this.idConge = idConge;
	}

	public Date getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public float getDuree() {
		return duree;
	}
	public void setDuree(float duree) {
		this.duree = duree;
	}

	
	
	
}
