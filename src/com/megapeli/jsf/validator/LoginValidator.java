package com.megapeli.jsf.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean
public class LoginValidator{

	public void validarCorreo(FacesContext fo, UIComponent uic, Object o) {
		
		fo=FacesContext.getCurrentInstance();
		String correo = o.toString().trim();

		Pattern patern = Pattern.compile("\\w+@gmail.com");
		Matcher matcher = patern.matcher((CharSequence) correo);

		if (correo.length() == 0) {
			((UIInput)uic).setValid(false);
			fo.addMessage(uic.getClientId(fo),new FacesMessage("Ingrese el campo del email"));
		} else if (correo.length() > 30) {
			throw new ValidatorException(new FacesMessage("El campo email debe ser menor de 30 caracteres"));
		} else if (!matcher.matches()) {
			throw new ValidatorException(new FacesMessage("El Correo Electronico debe ser gmail"));
		}
	}

	public void validarPassword(FacesContext fo, UIComponent uic, Object o) {
       String password=o.toString().trim();
       
       if(password.length()==0) {
    	   throw new ValidatorException(new FacesMessage("Ingrese el campo de la contraseña"));
       }else if(password.length()<10) {
    	   throw new ValidatorException(new FacesMessage("El campo de la contraseña debe ser mayor de 10 caracteres"));
       }
	}
	
	public void validarNombreYApellido(FacesContext fo, UIComponent uic, Object o) {
	       String nombre=o.toString().trim();
	       
	       if(nombre.length()==0) {
	    	   throw new ValidatorException(new FacesMessage("Ingrese el campo de la contraseña"));
	       }else if(nombre.length()>30) {
	    	   throw new ValidatorException(new FacesMessage("El campo de la contraseña debe ser menor de 30 caracteres"));
	       }
		}
}
