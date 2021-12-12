package com.megapeli.jpa.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.primefaces.PrimeFaces;

import com.megapeli.jpa.dao.ActorDao;
import com.megapeli.jpa.dao.DirectorDao;
import com.megapeli.jpa.dao.PeliculaDao;
import com.megapeli.jpa.dao.PersonajeDao;
import com.megapeli.jpa.dao.RolDao;
import com.megapeli.jpa.entity.Actorp;
import com.megapeli.jpa.entity.Directorp;
import com.megapeli.jpa.entity.Peliculap;
import com.megapeli.jpa.entity.Personaje;
import com.megapeli.jpa.entity.Rolp;

@ManagedBean(name = "bean5")
@SessionScoped
public class RegistroBean implements Serializable {

	@ManagedProperty("#{bean1}")
	private LoginBean bean1;
	
	private int tipo;
	private String fecha;

	private Actorp actor = new Actorp();
	private Personaje personaje = new Personaje();
	private Directorp director = new Directorp();

	public void registroActor() {
		FacesContext context = FacesContext.getCurrentInstance();
		this.tipo = 1;
		try {
			context.getExternalContext().redirect("registro.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registroDirector() {
		FacesContext context = FacesContext.getCurrentInstance();
		this.tipo = 2;
		try {
			context.getExternalContext().redirect("registro.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registroPersonaje() {
		FacesContext context = FacesContext.getCurrentInstance();
		this.tipo = 3;
		try {
			context.getExternalContext().redirect("registro.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveActor() {
		FacesMessage message = null;
		actor.setFechanacimiento(java.sql.Date.valueOf(fecha));
		ActorDao dao = new ActorDao();
		dao.insert(actor);
		actor = new Actorp();
		fecha = "";
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Actor Registrado Correctamente");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void saveDirector() {
		FacesMessage message = null;
		director.setFechaNacimiento(java.sql.Date.valueOf(fecha));
		DirectorDao dao = new DirectorDao();
		dao.insert(director);
		director = new Directorp();
		this.fecha = "";
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Director Registrado Correctamente");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void savePersonaje() {
		FacesMessage message = null;
		PersonajeDao dao = new PersonajeDao();
		dao.insert(personaje);
		personaje = new Personaje();
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Personaje Registrado Correctamente");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<SelectItem> actores() {
		List<SelectItem> item = new ArrayList<>();
		SelectItemGroup s = new SelectItemGroup("Seleccione Actor");
		ActorDao daoT = new ActorDao();
		List<Actorp> g = daoT.list();
		SelectItem[] items = new SelectItem[g.size()];
		for (int i = 0; i < g.size(); i++) {
			items[i] = new SelectItem("" + g.get(i).getId(), "" + g.get(i).getNombre());

		}
		s.setSelectItems(items);
		item.add(s);
		return item;
	}
	
	public List<SelectItem> peliculas() {
		List<SelectItem> item = new ArrayList<>();
		SelectItemGroup s = new SelectItemGroup("Seleccione Pelicula");
		PeliculaDao daoT = new PeliculaDao();
		List<Peliculap> g = daoT.findByFieldListInt("idUsuario",bean1.getValidado().getId());
		SelectItem[] items = new SelectItem[g.size()];
		for (int i = 0; i < g.size(); i++) {
			items[i] = new SelectItem("" + g.get(i).getId(), "" + g.get(i).getNombre());
		}
		s.setSelectItems(items);
		item.add(s);
		return item;
	}
	
	public List<SelectItem> roles() {
		List<SelectItem> item = new ArrayList<>();
		SelectItemGroup s = new SelectItemGroup("Seleccione Pelicula");
	    RolDao daoT = new RolDao();
		List<Rolp> g = daoT.list();
		SelectItem[] items = new SelectItem[g.size()];
		for (int i = 0; i < g.size(); i++) {
			items[i] = new SelectItem("" + g.get(i).getId(), "" + g.get(i).getDescripcion());
		}
		s.setSelectItems(items);
		item.add(s);
		return item;
	}

	///////////////////////////// GETTER Y SETTERS /////////////////////////7
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Actorp getActor() {
		return actor;
	}

	public void setActor(Actorp actor) {
		this.actor = actor;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	public Directorp getDirector() {
		return director;
	}

	public void setDirector(Directorp director) {
		this.director = director;
	}

	public LoginBean getBean1() {
		return bean1;
	}

	public void setBean1(LoginBean bean1) {
		this.bean1 = bean1;
	}
	
	
}
