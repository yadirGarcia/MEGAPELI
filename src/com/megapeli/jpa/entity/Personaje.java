package com.megapeli.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the personaje database table.
 * 
 */
@Entity
@NamedQuery(name="Personaje.findAll", query="SELECT p FROM Personaje p")
public class Personaje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int idActor;

	private int idPelicula;

	private int idRol;

	private String papel;

	public Personaje() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdActor() {
		return this.idActor;
	}

	public void setIdActor(int idActor) {
		this.idActor = idActor;
	}

	public int getIdPelicula() {
		return this.idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public int getIdRol() {
		return this.idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getPapel() {
		return this.papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

}