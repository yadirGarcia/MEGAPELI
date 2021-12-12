package com.megapeli.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the usuariop database table.
 * 
 */
@Entity
@NamedQuery(name="Usuariop.findAll", query="SELECT u FROM Usuariop u")
public class Usuariop implements Serializable {
	private static final long serialVersionUID = 1L;

	private String apellido;

	private String email;

	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int idTipoUsuario;

	private String nombre;

	private String password;
	
	

	public Usuariop() {
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTipoUsuario() {
		return this.idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}