package com.megapeli.jpa.dao;

import com.megapeli.jpa.entity.Directorp;
import com.megapeli.jpa.util.Conexion;

public class DirectorDao 
extends Conexion<Directorp> 
implements GenericDao<Directorp> {

public DirectorDao() {
	super(Directorp.class);
	// TODO Auto-generated constructor stub
}
}
