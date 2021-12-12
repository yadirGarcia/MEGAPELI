package com.megapeli.jpa.bean;

//import java.io.ByteArrayInputStream;
import java.io.IOException;
//import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

//import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import com.megapeli.jpa.dao.PeliculaDao;
import com.megapeli.jpa.dao.PersonajeDao;
import com.megapeli.jpa.dao.SuscripcionDao;
import com.megapeli.jpa.dao.TipoUsuarioDao;
import com.megapeli.jpa.dao.ActorDao;
import com.megapeli.jpa.dao.ClasificacionDao;
import com.megapeli.jpa.dao.ComentarioDao;
import com.megapeli.jpa.dao.DirectorDao;
import com.megapeli.jpa.dao.GeneroDao;
import com.megapeli.jpa.entity.Actorp;
import com.megapeli.jpa.entity.Clasificacionp;
import com.megapeli.jpa.entity.Comentariop;
import com.megapeli.jpa.entity.Directorp;
import com.megapeli.jpa.entity.Generop;
import com.megapeli.jpa.entity.Peliculap;
import com.megapeli.jpa.entity.Personaje;
import com.megapeli.jpa.entity.Suscripcion;
import com.megapeli.jpa.entity.Tipousuario;

@ManagedBean(name = "bean2")
@SessionScoped
public class PeliculaBean implements Serializable {

	@ManagedProperty("#{bean1}")
	private LoginBean bean1;

	private List<Peliculap> peliculas;
	private Peliculap selecionada;
	private List<Peliculap> peliculagenero;
	private StreamedContent imagenPelicula;
	
	
	private List<Actorp> autores;
	private List<Personaje> personajes;

	public List<Peliculap> listaPeliculas() {
		if (peliculas == null) {
			peliculas = new ArrayList<>();
			PeliculaDao daoP = new PeliculaDao();
			SuscripcionDao daoS = new SuscripcionDao();
			List<Suscripcion> suscripcion = new ArrayList<>();

			suscripcion = daoS.findByFieldListInt("idUsuario", bean1.getValidado().getId());

			for (int i = 0; i < suscripcion.size(); i++) {
				List<Peliculap> tmp = new ArrayList<>();
				tmp = daoP.findByFieldListInt("idUsuario", suscripcion.get(i).getIdSuscripto());
				for (int j = 0; j < tmp.size(); j++) {
					peliculas.add(tmp.get(j));
				}
			}
		}
		return peliculas;
	}
	
	public List<SelectItem> generos(){
		List<SelectItem> item= new ArrayList<>();
		SelectItemGroup s = new SelectItemGroup("Tipo Genero");
		GeneroDao daoT = new GeneroDao();
		List<Generop> g = daoT.list();
		SelectItem[] items = new SelectItem[g.size()];
		for (int i = 0; i < g.size(); i++) {
			items[i] = new SelectItem("" + g.get(i).getId(), "" + g.get(i).getDescripcion());

		}
		s.setSelectItems(items);
		item.add(s);
		return item;
	}
	
	public List<SelectItem> directores(){
		List<SelectItem> item= new ArrayList<>();
		SelectItemGroup s = new SelectItemGroup("Seleccione Director");
		DirectorDao daoT = new DirectorDao();
		List<Directorp> g = daoT.list();
		SelectItem[] items = new SelectItem[g.size()];
		for (int i = 0; i < g.size(); i++) {
			items[i] = new SelectItem("" + g.get(i).getId(), "" + g.get(i).getNombre());

		}
		s.setSelectItems(items);
		item.add(s);
		return item;
	}
	
	public List<SelectItem> personajes(){
		List<SelectItem> item= new ArrayList<>();
		SelectItemGroup s = new SelectItemGroup("Seleccione Director");
		PersonajeDao daoT = new PersonajeDao();
		List<Personaje> g = daoT.list();
		SelectItem[] items = new SelectItem[g.size()];
		ActorDao dao= new ActorDao();
		for (int i = 0; i < g.size(); i++) {			
			items[i] = new SelectItem("" + g.get(i).getId(), "" +dao.findByFieldInt("id", g.get(i).getIdActor()).getNombre());
		}
		s.setSelectItems(items);
		item.add(s);
		return item;
	}
	
	public List<SelectItem> clasificaciones(){
		List<SelectItem> item= new ArrayList<>();
		SelectItemGroup s = new SelectItemGroup("Tipo Clasificación");
		ClasificacionDao daoT = new ClasificacionDao();
		List<Clasificacionp> g = daoT.list();
		SelectItem[] items = new SelectItem[g.size()];
		for (int i = 0; i < g.size(); i++) {
			items[i] = new SelectItem("" + g.get(i).getId(), "" + g.get(i).getDescripcion());

		}
		s.setSelectItems(items);
		item.add(s);
		return item;
	}

	public Directorp directorPelicula(int idDirector) {
		Directorp d = new Directorp();
		DirectorDao dao = new DirectorDao();
		d = dao.findByFieldInt("id", idDirector);
		return d;
	}

	public List<Personaje> listaPersonajePelicula(int idPelicula) {
		personajes = new ArrayList<>();
		PersonajeDao dao = new PersonajeDao();
		personajes= dao.findByFieldListInt("idPelicula", idPelicula);
		return personajes;
	}

	public List<Comentariop> listaComentarioPelicula(int idPelicula) {
		List<Comentariop> c = new ArrayList<>();
		ComentarioDao dao = new ComentarioDao();
		c = dao.findByFieldListInt("idPelicula", idPelicula);
		return c;
	}

	public void peliculaSelec(Peliculap p) {
		this.selecionada = new Peliculap();
		this.autores= new ArrayList<>();
		this.listaPersonajePelicula(p.getId());
		ActorDao dao = new ActorDao();
		for(Personaje pe: personajes) {
			autores.add(dao.findByFieldInt("id",pe.getIdActor()));
		}
		selecionada=p;
	}
	
	public Generop conocerGenero(int id) {
		GeneroDao dao= new GeneroDao();
		return (dao.findByFieldInt("id", id));
	}
	
	public Clasificacionp conocerClasificacion(int id) {
		ClasificacionDao dao= new ClasificacionDao();
		return (dao.findByFieldInt("id", id));
	}

	/******************* PELICULA POR GENERO *********************************/
	public void accion() {
		FacesContext context = FacesContext.getCurrentInstance();
		recursivo("accion");
		try {
			context.getExternalContext().redirect("selecionada.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void animacion() {
		FacesContext context = FacesContext.getCurrentInstance();
		recursivo("animacion");
		try {
			context.getExternalContext().redirect("selecionada.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void aventura() {
		FacesContext context = FacesContext.getCurrentInstance();
		recursivo("aventura");
		try {
			context.getExternalContext().redirect("selecionada.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cienciaFiccion() {
		FacesContext context = FacesContext.getCurrentInstance();
		recursivo("ciencia ficcion");
		try {
			context.getExternalContext().redirect("selecionada.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void comedia() {
		FacesContext context = FacesContext.getCurrentInstance();
		recursivo("comedia");
		try {
			context.getExternalContext().redirect("selecionada.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void crimen() {
		FacesContext context = FacesContext.getCurrentInstance();
		recursivo("crimen");
		try {
			context.getExternalContext().redirect("selecionada.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void drama() {
		FacesContext context = FacesContext.getCurrentInstance();
		recursivo("drama");
		try {
			context.getExternalContext().redirect("selecionada.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fantasia() {
		FacesContext context = FacesContext.getCurrentInstance();
		recursivo("fantasia");
		try {
			context.getExternalContext().redirect("selecionada.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void misterio() {
		FacesContext context = FacesContext.getCurrentInstance();
		recursivo("misterio");
		try {
			context.getExternalContext().redirect("selecionada.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void romantica() {
		FacesContext context = FacesContext.getCurrentInstance();
		recursivo("romantica");
		try {
			context.getExternalContext().redirect("selecionada.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void suspenso() {
		FacesContext context = FacesContext.getCurrentInstance();
		recursivo("suspenso");
		try {
			context.getExternalContext().redirect("selecionada.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void terror() {
		FacesContext context = FacesContext.getCurrentInstance();
		recursivo("terror");
		try {
			context.getExternalContext().redirect("selecionada.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void recursivo(String genero) {
		List<Peliculap> tmp = this.peliculas;
		peliculagenero = new ArrayList<>();
		GeneroDao daoG = new GeneroDao();
		Generop g = daoG.findByField("descripcion", genero);
		for (Peliculap p : tmp) {
			if (p.getIdGenero() == g.getId()) {
				peliculagenero.add(p);
			}
		}
	}
/////////////////////////////////////////////// GETTER Y SETTERS ///////////////////////////

	public List<Peliculap> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Peliculap> peliculas) {
		this.peliculas = peliculas;
	}

	public LoginBean getBean1() {
		return bean1;
	}

	public void setBean1(LoginBean bean1) {
		this.bean1 = bean1;
	}

	public Peliculap getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Peliculap selecionada) {
		this.selecionada = selecionada;
	}

	public List<Peliculap> getPeliculagenero() {
		return peliculagenero;
	}

	public void setPeliculagenero(List<Peliculap> peliculagenero) {
		this.peliculagenero = peliculagenero;
	}

	public StreamedContent getImagenPelicula() {
		return imagenPelicula;
	}

	public void setImagenPelicula(StreamedContent imagen) {
		this.imagenPelicula = imagen;
	}

	public List<Actorp> getAutores() {
		return autores;
	}

	public void setAutores(List<Actorp> autores) {
		this.autores = autores;
	}

	public List<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<Personaje> personajes) {
		this.personajes = personajes;
	}


	
	

	/*
	 * public StreamedContent imagenPelicula() { 
	 * PeliculaDao daoP = new PeliculaDao(); 
	 * InputStream input = new  ByteArrayInputStream(daoP.find(1).getImagen());
	 * imagenPelicula= (new DefaultStreamedContent(input, "image/jpg")); return imagenPelicula; }
	 */
}
