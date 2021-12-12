package com.megapeli.jpa.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import com.megapeli.jpa.dao.PeliculaDao;
import com.megapeli.jpa.dao.SuscripcionDao;
import com.megapeli.jpa.dao.TipoUsuarioDao;
import com.megapeli.jpa.dao.UsuarioDAO;
import com.megapeli.jpa.entity.Suscripcion;
import com.megapeli.jpa.entity.Usuariop;

@ManagedBean(name = "bean3")
@RequestScoped
public class UsuarioBean implements Serializable{

	@ManagedProperty("#{bean1}")
	private LoginBean bean1;
	
	@ManagedProperty("#{bean2}")
	private PeliculaBean bean2;

	private Usuariop actualizar= new Usuariop();

	public List<Suscripcion>  initSuscriptores() {		
		if (bean1.getValidado() != null) {
			if (bean1.getValidado().getIdTipoUsuario() == 1) {
				List<Suscripcion> suscriptores= new ArrayList<>();
				SuscripcionDao daoS = new SuscripcionDao();
				suscriptores = daoS.findByFieldListInt("idUsuario", bean1.getValidado().getId());
				return suscriptores;
			}
		}
		return null;
	}
	
	public List<Usuariop> listaAdministradores(){
		List<Usuariop> usuario= new ArrayList<>();
		UsuarioDAO dao= new UsuarioDAO();
		usuario=dao.findByFieldListInt("idTipoUsuario", 2);
		
		return usuario;
	}
	
	public Usuariop conocerUsuario(int i) {
		UsuarioDAO dao= new UsuarioDAO();
		Usuariop u= new Usuariop();
		u=dao.find(i);
		return u;
	}
	
	public Suscripcion conocerSuscriptor(int id) {
		List<Suscripcion> s= initSuscriptores();
		for(Suscripcion tmp:s) {
			if(tmp.getIdSuscripto()==id) {
				return tmp;
			}
		}
		return null;
	}
	
	
	public void dejarSeguir(Suscripcion s) {
		SuscripcionDao daoS = new SuscripcionDao();
		daoS.delete(s);
		bean2.setPeliculas(null);
	}
	
	public void dejarSeguir(int id) {
		SuscripcionDao daoS = new SuscripcionDao();
		Suscripcion s=conocerSuscriptor(id);
		daoS.delete(s);
		bean2.setPeliculas(null);
	}
	

	public void seguir(int id) {
		SuscripcionDao daoS = new SuscripcionDao();	
		Suscripcion s= new Suscripcion();
		s.setIdSuscripto(id);
		s.setIdUsuario(bean1.getValidado().getId());
		s.setFechaSuscripcion(java.sql.Date.valueOf(fechaactual()));
		daoS.insert(s);
		bean2.setPeliculas(null);
	}

	public void actualizarUsuario() {
		FacesMessage message = null;
		if (actualizar != null) {
			if(actualizar.getApellido().length()>0) {bean1.getValidado().setApellido(actualizar.getApellido());}
			if(actualizar.getNombre().length()>0) {bean1.getValidado().setNombre(actualizar.getNombre());}
			if(actualizar.getEmail().length()>0) {bean1.getValidado().setEmail(actualizar.getEmail());}
			if(actualizar.getPassword().length()>9) {bean1.getValidado().setPassword(actualizar.getPassword());}
			UsuarioDAO daoU = new UsuarioDAO();
			actualizar=bean1.getValidado();
			daoU.update(actualizar);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Actualizado Correctamente");
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Error al Actualizar Usuario");
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		PrimeFaces.current().ajax().addCallbackParam("loggeddIn", (actualizar != null) ? true : false);
	}

	public void eliminarCuenta() {
		UsuarioDAO daoU = new UsuarioDAO();
		daoU.delete(bean1.getValidado());
	}
	
	public String tipoUsuario(int id) {
		TipoUsuarioDao dao= new TipoUsuarioDao();
		return (dao.findByFieldInt("id", id).getDescripcion());
	}

	public String fechaactual() {
		Calendar c = Calendar.getInstance();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int tmp = Integer.parseInt(mes);
		mes = (tmp < 10) ? "0" + tmp : "" + tmp;
		String fechaactual = annio + "-" + mes + "-" + dia;
		return fechaactual;
	}
////////////////////////////////////GETTER Y SETTERS //////////////////////////////////// /////////////////////////////////////////

	public LoginBean getBean1() {
		return bean1;
	}

	public void setBean1(LoginBean bean1) {
		this.bean1 = bean1;
	}

	public Usuariop getActualizar() {
		return actualizar;
	}

	public void setActualizar(Usuariop actualizar) {
		this.actualizar = actualizar;
	}

	public PeliculaBean getBean2() {
		return bean2;
	}

	public void setBean2(PeliculaBean bean2) {
		this.bean2 = bean2;
	}
}
