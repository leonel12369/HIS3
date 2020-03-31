package com.his.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.his.model.MSexo;

@Repository("genero")
public class GeneroDaoImpl  implements InGeneroDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<MSexo> listarTodo() {
		// TODO Auto-generated method stub
		try{
		List<MSexo> a=em.createQuery("from MSexo",MSexo.class).getResultList();
			//System.out.println(a);
			//System.out.println(a.get(1).getDescSexo());
			return em.createQuery("from MSexo",MSexo.class).getResultList();
			
		}catch(Exception e) {
			System.out.println("----------------Mensaje de error-----------");
			System.out.println(e);
			return null;
		}
		
	}

	
}

