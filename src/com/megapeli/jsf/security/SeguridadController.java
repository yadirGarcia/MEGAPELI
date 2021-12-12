package com.megapeli.jsf.security;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.megapeli.jpa.bean.AdministradorBean;
import com.megapeli.jpa.bean.LoginBean;
import com.megapeli.jpa.bean.PeliculaBean;
import com.megapeli.jpa.bean.UsuarioBean;
import com.megapeli.jpa.entity.Usuariop;

@ManagedBean(name = "security")
@RequestScoped
public class SeguridadController implements Serializable {

	@ManagedProperty("#{bean1}")
	private LoginBean bean1;
	
	@ManagedProperty("#{bean2}")
	private PeliculaBean bean2;
	
	@ManagedProperty("#{bean3}")
	private UsuarioBean bean3;
	
	@ManagedProperty("#{bean4}")
	private AdministradorBean bean4;

	public void verificarSesion() {

		FacesContext context = FacesContext.getCurrentInstance();
		Usuariop validado = bean1.getValidado();
		if (validado == null) {
			try {
				context.getExternalContext().redirect("login.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void verificarLogin() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuariop validado = bean1.getValidado();
		if (validado != null) {
			try {
				context.getExternalContext().redirect("inicio.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void menuGenero() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuariop validado = bean1.getValidado();
		if (validado.getIdTipoUsuario()==1) {
			try {
				context.getExternalContext().redirect("inicio.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void usuario() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuariop validado = bean1.getValidado();
		if (validado.getIdTipoUsuario()!=1) {
			try {
				context.getExternalContext().redirect("inicio.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		this.setBean1(new LoginBean());
		this.setBean2(new PeliculaBean());
		this.setBean3(new UsuarioBean());
		this.setBean4(new AdministradorBean());
	}

	/////////////////////////////////// GETTER Y SETTERS ///////////////////////////
	public LoginBean getBean1() {
		return bean1;
	}

	public void setBean1(LoginBean bean1) {
		this.bean1 = bean1;
	}

	public PeliculaBean getBean2() {
		return bean2;
	}

	public void setBean2(PeliculaBean bean2) {
		this.bean2 = bean2;
	}

	public UsuarioBean getBean3() {
		return bean3;
	}

	public void setBean3(UsuarioBean bean3) {
		this.bean3 = bean3;
	}

	public AdministradorBean getBean4() {
		return bean4;
	}

	public void setBean4(AdministradorBean bean4) {
		this.bean4 = bean4;
	}
}
