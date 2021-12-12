package com.megapeli.jpa.dao;

import com.megapeli.jpa.entity.Clasificacionp;
import com.megapeli.jpa.util.Conexion;

public class ClasificacionDao
extends Conexion<Clasificacionp> 
implements GenericDao<Clasificacionp> {
	
	public ClasificacionDao() {
		super(Clasificacionp.class);
		// TODO Auto-generated constructor stub
	}
}