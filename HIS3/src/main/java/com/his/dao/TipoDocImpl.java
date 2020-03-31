package com.his.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.his.model.TipoDoc;

@Repository("tipoDoc")
public class TipoDocImpl implements TipoDocDao{
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public List<TipoDoc> listarTodo() {
		// TODO Auto-generated method stub
		try {
			List<TipoDoc> tipDoc = em.createQuery("from TipoDoc",TipoDoc.class).getResultList();
			return tipDoc;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("-----------error--------");
			System.out.println(e);
		}
		return null;
	}

}
