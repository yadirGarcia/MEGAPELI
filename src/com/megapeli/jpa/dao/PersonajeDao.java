package com.megapeli.jpa.dao;

import com.megapeli.jpa.entity.Personaje;
import com.megapeli.jpa.util.Conexion;

public class PersonajeDao
extends Conexion<Personaje> 
implements GenericDao<Personaje> {

public PersonajeDao() {
		super(Personaje.class);
		// TODO Auto-generated constructor stub
	}
}
