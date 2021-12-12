package com.megapeli.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the peliculap database table.
 * 
 */
@Entity
@NamedQuery(name = "Peliculap.findAll", query = "SELECT p FROM Peliculap p")
public class Peliculap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Lob
	private String calidad;

	@Temporal(TemporalType.DATE)
	private Date fechalanzamiento;
	
	@Temporal(TemporalType.DATE)
	private Date fechapublicacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, length = 1)
	private int id;

	private int idClasificacion;

	private int idDirector;

	private int idGenero;

	private String idioma;

	private int idUsuario;

	private String imagen;

	private String link;

	private String nombre;
	
	
	
	private String pais;

	@Lob
	private String sinopsis;

	public Peliculap() {
	}

	public String getCalidad() {
		return this.calidad;
	}

	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}

	public Date getFechalanzamiento() {
		return this.fechalanzamiento;
	}

	public void setFechalanzamiento(Date fechalanzamiento) {
		this.fechalanzamiento = fechalanzamiento;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdClasificacion() {
		return this.idClasificacion;
	}

	public void setIdClasificacion(int idClasificacion) {
		this.idClasificacion = idClasificacion;
	}

	public int getIdDirector() {
		return this.idDirector;
	}

	public void setIdDirector(int idDirector) {
		this.idDirector = idDirector;
	}

	public int getIdGenero() {
		return this.idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getSinopsis() {
		return this.sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public Date getFechapublicacion() {
		return fechapublicacion;
	}

	public void setFechapublicacion(Date fechapublicacion) {
		this.fechapublicacion = fechapublicacion;
	}
}