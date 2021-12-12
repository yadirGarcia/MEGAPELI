package com.megapeli.jpa.dao;

import com.megapeli.jpa.entity.Actorp;
import com.megapeli.jpa.util.Conexion;

public class ActorDao 
	extends Conexion<Actorp> 
	implements GenericDao<Actorp> {
		
		public ActorDao() {
			super(Actorp.class);
			// TODO Auto-generated constructor stub
		}
	}
