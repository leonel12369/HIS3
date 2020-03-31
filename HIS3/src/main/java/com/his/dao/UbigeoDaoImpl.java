package com.his.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.his.model.MUbigeo;

@Repository("ubigeo")
public class UbigeoDaoImpl implements UbigeoDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<MUbigeo> listarDepartamento() {
		// TODO Auto-generated method stub
		try {
			List<MUbigeo>listaDepartamento= em.createQuery("select departamento from MUbigeo group by departamento order by departamento"
					).getResultList();
			return listaDepartamento;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("-----------------------error----------");
			System.out.println(e);
		}
		return null;
	}

	@Override
	public List<String> listarProvincia(String departamento) {
		// TODO Auto-generated method stub
		try {
			List<String>listaProvincia=em.createQuery("select provincia from MUbigeo"
					+ " where departamento =: departamento group by provincia order by provincia")
					.setParameter("departamento", departamento)
					.getResultList();
			//System.out.println(listaProvincia);
			return listaProvincia;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("------------error--------------");
			System.out.println(e);
		}
		return null;
	}

	@Override
	public List<String> listarDistrito(String departamento, String provincia) {
		// TODO Auto-generated method stub
		try {
			List<String> listaDistrito= em.createQuery("select distrito from MUbigeo"
					+ " where departamento =: departamento and provincia =: provincia group by distrito order by distrito")
					.setParameter("departamento", departamento)
					.setParameter("provincia",provincia)
					.getResultList();
			return listaDistrito;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("---------error----------");
			System.out.println(e);
		}
		return null;
	}

	@Override
	public List<String> extraerIdUbigeo(String departamento, String provincia, String distrito) {
		// TODO Auto-generated method stub
		try {
			List<String> idUbigeo= em.createQuery("select idUbigeo from MUbigeo "
					+ "where departamento =: departamento and provincia =: provincia and distrito =: distrito")
					.setParameter("departamento", departamento)
					.setParameter("provincia", provincia)
					.setParameter("distrito", distrito).getResultList();
			return idUbigeo;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("-----error-----");
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Object extraerUbigeoValidado(String departamento, String provincia) {
		// TODO Auto-generated method stub
		try {
			Object listaUbigeoValidada=em.createQuery("select substring(idUbigeo,1,4) from MUbigeo where departamento=:departamento and provincia =: provincia group by substring(idUbigeo,1,4)")
					.setParameter("departamento", departamento)
					.setParameter("provincia", provincia)
					.getSingleResult();
			
			return listaUbigeoValidada; 
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("---------error----");
			System.out.println(e);
		}
		return null;
	}
	
	

}
