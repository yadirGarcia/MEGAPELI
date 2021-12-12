package com.megapeli.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the suscripcion database table.
 * 
 */
@Entity
@NamedQuery(name="Suscripcion.findAll", query="SELECT s FROM Suscripcion s")
public class Suscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date fechaSuscripcion;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int idSuscripto;

	private int idUsuario;

	public Suscripcion() {
	}

	public Date getFechaSuscripcion() {
		return this.fechaSuscripcion;
	}

	public void setFechaSuscripcion(Date fechaSuscripcion) {
		this.fechaSuscripcion = fechaSuscripcion;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSuscripto() {
		return this.idSuscripto;
	}

	public void setIdSuscripto(int idSuscripto) {
		this.idSuscripto = idSuscripto;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}