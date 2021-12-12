package com.megapeli.jpa.dao;

import com.megapeli.jpa.entity.Tipousuario;
import com.megapeli.jpa.util.Conexion;

public class TipoUsuarioDao 
	extends Conexion<Tipousuario> 
	implements GenericDao<Tipousuario> {

	public TipoUsuarioDao() {
		super(Tipousuario.class);
		// TODO Auto-generated constructor stub
	}
}
