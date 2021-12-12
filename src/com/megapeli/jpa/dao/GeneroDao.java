package com.megapeli.jpa.dao;

import com.megapeli.jpa.entity.Generop;
import com.megapeli.jpa.util.Conexion;

public class GeneroDao 
	extends Conexion<Generop> 
	implements GenericDao<Generop> {

	public GeneroDao() {
		super(Generop.class);
		// TODO Auto-generated constructor stub
	}
}
