package com.megapeli.jpa.dao;

import com.megapeli.jpa.entity.Usuariop;
import com.megapeli.jpa.util.Conexion;

public class UsuarioDAO 
	extends Conexion<Usuariop> 
	implements GenericDao<Usuariop> {

	public UsuarioDAO() {
	 super(Usuariop.class);
		// TODO Auto-generated constructor stub
	}
}
