package com.example.enteties;



import java.io.Serializable;



import java.util.Date;

import javax.validation.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.validation.constraints.Size;


import org.springframework.format.annotation.DateTimeFormat;









@Entity
public class Etudiant  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="Nom",length = 30)
	@Size(min=5,max=30,message 
		      = "About Me must be between 5 and 30 characters")
	@NotEmpty(message = "Name cannot be null")
	private String nom;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateNaissance;
	@NotEmpty(message = "Email cannot be null")
	private String email;
	private String photo;
	
	//pour JPA et POUR MOI
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	//pour MOI
	public Etudiant( String nom, Date dateNaissance, String email, String photo) {
		super();
		
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.photo = photo;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}



	

}
