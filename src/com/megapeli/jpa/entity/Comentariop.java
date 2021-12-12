package com.megapeli.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the comentariop database table.
 * 
 */
@Entity
@NamedQuery(name="Comentariop.findAll", query="SELECT c FROM Comentariop c")
public class Comentariop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Lob
	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fechaComentario;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=1)
	private int id;

	private int idPelicula;

	private int idUsuario;

	public Comentariop() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaComentario() {
		return this.fechaComentario;
	}

	public void setFechaComentario(Date fechaComentario) {
		this.fechaComentario = fechaComentario;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPelicula() {
		return this.idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}