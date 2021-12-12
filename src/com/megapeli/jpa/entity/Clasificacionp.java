package com.megapeli.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the clasificacionp database table.
 * 
 */
@Entity
@NamedQuery(name="Clasificacionp.findAll", query="SELECT c FROM Clasificacionp c")
public class Clasificacionp implements Serializable {
	private static final long serialVersionUID = 1L;

	private String descripcion;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=1)
	private int id;

	public Clasificacionp() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}