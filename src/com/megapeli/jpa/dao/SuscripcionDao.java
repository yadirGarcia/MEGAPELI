package com.megapeli.jpa.dao;

import com.megapeli.jpa.entity.Suscripcion;
import com.megapeli.jpa.util.Conexion;

public class SuscripcionDao
extends Conexion<Suscripcion> 
implements GenericDao<Suscripcion> {

public SuscripcionDao() {
		super(Suscripcion.class);
		// TODO Auto-generated constructor stub
	}
}
