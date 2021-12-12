package com.megapeli.jpa.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.PrimeFaces;
import com.megapeli.jpa.dao.TipoUsuarioDao;
import com.megapeli.jpa.dao.UsuarioDAO;
import com.megapeli.jpa.entity.Tipousuario;
import com.megapeli.jpa.entity.Usuariop;

@ManagedBean(name = "bean1")
@SessionScoped
public class LoginBean implements Serializable {

	private Usuariop usuario = new Usuariop();
	private Usuariop validado = null;

	private String fecha;
	private List<SelectItem> tipousuarios;

	@PostConstruct
	public void tipoUsuario() {
		if (tipousuarios == null) {
			tipousuarios = new ArrayList<SelectItem>();
			SelectItemGroup s = new SelectItemGroup("Tipo Usuario");
			TipoUsuarioDao daoT = new TipoUsuarioDao();
			List<Tipousuario> tipoUsuario = daoT.list();
			SelectItem[] items = new SelectItem[tipoUsuario.size()];
			for (int i = 0; i < tipoUsuario.size(); i++) {
				items[i] = new SelectItem("" + tipoUsuario.get(i).getId(), "" + tipoUsuario.get(i).getDescripcion());

			}
			s.setSelectItems(items);
			tipousuarios.add(s);
		}
	}

	public String logearse() {
		if (validado == null) {
			FacesMessage message = null;
			UsuarioDAO daoU = new UsuarioDAO();

			Usuariop us = daoU.findByField("email", usuario.getEmail());
			if (us != null) {
				if (usuario.getPassword().contentEquals(us.getPassword())) {
					this.validado = us;
					this.usuario = new Usuariop();
					return "inicio?faces-redirect=true";
				} else {
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Contraseña Incorrecta");
				}
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "REGISTRASE", "Usuario No existe");
			}
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return "login?faces-redirect=true";
	}

	public String registrarse() {
		FacesMessage message = null;
		UsuarioDAO daoU = new UsuarioDAO();
		Usuariop us = daoU.findByField("email", usuario.getEmail());
		if (us == null) {
			usuario.setFechaNacimiento(java.sql.Date.valueOf(fecha));
			Calendar c = Calendar.getInstance();
			String dia = Integer.toString(c.get(Calendar.DATE));
			String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
			String annio = Integer.toString(c.get(Calendar.YEAR));
			int tmp = Integer.parseInt(mes);
			mes = (tmp < 10) ? "0" + tmp : "" + tmp;
			String fechaactual = annio + "-" + mes + "-" + dia;
			usuario.setFechaRegistro(java.sql.Date.valueOf(fechaactual));
			daoU.insert(usuario);
			this.usuario = new Usuariop();
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
					"Te registrarse correctamente ahora inicia sesion");
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Email ya en USO");
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		PrimeFaces.current().ajax().addCallbackParam("loggeddIn", (us != null) ? true : false);
		return "logi?faces-redirect=truen";
	}

	public void forgotPassword() {
		FacesMessage message = null;
		UsuarioDAO daoU = new UsuarioDAO();
		Usuariop us = daoU.findByField("email", usuario.getEmail());
		if (us != null) {
			if (usuario.getPassword().length() >= 10) {
				us.setPassword(usuario.getPassword());
				daoU.update(us);
				this.usuario = new Usuariop();
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
						"Cambiaste Correctamente la contraseña");
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Contraseña muy corta");
			}
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Email No Existe!");
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		PrimeFaces.current().ajax().addCallbackParam("loggedIn", (us != null) ? true : false);
	}

	/////////////////////////////////////////////// GETTER Y SETTERS
	/////////////////////////////////////////////// ///////////////////////////

	public Usuariop getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuariop usuario) {
		this.usuario = usuario;
	}

	public Usuariop getValidado() {
		return validado;
	}

	public void setValidado(Usuariop validado) {
		this.validado = validado;
	}

	public List<SelectItem> getTiposUsuarios() {
		return tipousuarios;
	}

	public void setUsuarios(List<SelectItem> tipousuarios) {
		this.tipousuarios = tipousuarios;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<SelectItem> getTipousuarios() {
		return tipousuarios;
	}

	public void setTipousuarios(List<SelectItem> tipousuarios) {
		this.tipousuarios = tipousuarios;
	}

}
