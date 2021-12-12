package com.megapeli.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the generop database table.
 * 
 */
@Entity
@NamedQuery(name="Generop.findAll", query="SELECT g FROM Generop g")
public class Generop implements Serializable {
	private static final long serialVersionUID = 1L;

	private String descripcion;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	public Generop() {
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