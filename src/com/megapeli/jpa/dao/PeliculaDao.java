package com.megapeli.jpa.dao;

import com.megapeli.jpa.entity.Peliculap;
import com.megapeli.jpa.util.Conexion;

public class PeliculaDao
extends Conexion<Peliculap> 
implements GenericDao<Peliculap> {
	
	public PeliculaDao() {
		super(Peliculap.class);
		// TODO Auto-generated constructor stub
	}
}
