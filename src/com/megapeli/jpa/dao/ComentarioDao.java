package com.megapeli.jpa.dao;

import com.megapeli.jpa.entity.Comentariop;
import com.megapeli.jpa.util.Conexion;

public class ComentarioDao 
extends Conexion<Comentariop> 
implements GenericDao<Comentariop> {
	
	public ComentarioDao() {
		super(Comentariop.class);
		// TODO Auto-generated constructor stub
	}
}
