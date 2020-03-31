package com.his.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.his.model.MMes;

@Repository("mesDao")
public class MesDaoImpl implements MesDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<MMes> listarTodo() {
		// TODO Auto-generated method stub
		try {
			List<MMes> listaMes=em.createQuery("from MMes group by idMes,nombre order by idMes",MMes.class).getResultList();
			return listaMes;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("------error-----");
			System.out.println(e);
		}
		return null;
	}

}
