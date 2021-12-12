package com.megapeli.jpa.dao;

import com.megapeli.jpa.entity.Rolp;
import com.megapeli.jpa.util.Conexion;

public class RolDao 
extends Conexion<Rolp> 
implements GenericDao<Rolp> {

public RolDao() {
		super(Rolp.class);
		// TODO Auto-generated constructor stub
	}
}