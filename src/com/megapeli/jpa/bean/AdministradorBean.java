package com.megapeli.jpa.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.megapeli.jpa.dao.PeliculaDao;
import com.megapeli.jpa.dao.SuscripcionDao;
import com.megapeli.jpa.entity.Peliculap;
import com.megapeli.jpa.entity.Suscripcion;

@ManagedBean(name = "bean4")
@SessionScoped
public class AdministradorBean implements Serializable {

	@ManagedProperty("#{bean1}")
	private LoginBean bean1;
	private List<Peliculap> peliculas;
	
	
	private Peliculap guardar= new Peliculap();
	private String lanzamiento,publicacion;
	
	
	public void savePelicula() {
	  FacesMessage message = null;
	  if(guardar!=null) {
		  guardar.setIdUsuario(bean1.getValidado().getId());
		  guardar.setFechalanzamiento(java.sql.Date.valueOf(lanzamiento));
		  guardar.setFechapublicacion(java.sql.Date.valueOf(publicacion));
		  PeliculaDao dao= new PeliculaDao();
		  dao.insert(guardar);
		  guardar= new Peliculap();
		  this.lanzamiento="";
		  this.publicacion="";
		  message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Pelicula Guardada");
	  } else {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Error al guardar Pelicula");
		} 
	  FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<Peliculap> listaPeliculas(int id) {
		peliculas = new ArrayList<>();
		PeliculaDao daoP = new PeliculaDao();
		peliculas = daoP.findByFieldListInt("idUsuario", id);
		return peliculas;
	}

	public int seguidores(int id) {
		SuscripcionDao dao = new SuscripcionDao();
		return (dao.findByFieldListInt("idSuscripto", id).size());
	}

	public List<Suscripcion> listaSuscriptores() {
		SuscripcionDao dao = new SuscripcionDao();
		return dao.findByFieldListInt("idSuscripto", bean1.getValidado().getId());
	}

	public int peliculasPublicadas(int id) {
		PeliculaDao daoP = new PeliculaDao();
		return daoP.findByFieldListInt("idUsuario", id).size();
	}
	
	public void eliminarPelicula(Peliculap p) {
		PeliculaDao dao= new PeliculaDao();
		dao.delete(p);
	}

	public boolean esSuscriptor(int id) {
		SuscripcionDao dao = new SuscripcionDao();
		Boolean esSuscriptor = false;
		List<Suscripcion> s = new ArrayList<>();
		s = dao.findByFieldListInt("idUsuario", bean1.getValidado().getId());
		for (Suscripcion tmp : s) {
			if (tmp.getIdSuscripto() == id) {
				esSuscriptor = true;
			}
		}
		return esSuscriptor;
	}

	//////////////////////////////////////// GETTER Y SETTERS
	//////////////////////////////////////// //////////////////////////////
	public LoginBean getBean1() {
		return bean1;
	}

	public void setBean1(LoginBean bean1) {
		this.bean1 = bean1;
	}

	public List<Peliculap> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Peliculap> peliculas) {
		this.peliculas = peliculas;
	}

	public Peliculap getGuardar() {
		return guardar;
	}

	public void setGuardar(Peliculap guardar) {
		this.guardar = guardar;
	}	
	public String getLanzamiento() {
		return lanzamiento;
	}

	public void setLanzamiento(String lanzamiento) {
		this.lanzamiento = lanzamiento;
	}

	public String getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(String publicacion) {
		this.publicacion = publicacion;
	}
}
