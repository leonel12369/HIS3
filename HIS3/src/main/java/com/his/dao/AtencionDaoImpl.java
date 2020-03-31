package com.his.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.his.model.HisAtenciones;

@Repository("atencion")
public class AtencionDaoImpl implements AtencionDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<HisAtenciones> listarAtencionesFiltrados(String ubigeo, String tipoDoc, String mes,String anio) {
		// TODO Auto-generated method stub
		return em.createQuery("from HisAtenciones where ubigeo =: ubigeo and tipoDoc=:doc and mes =:mes and anio=:anio",HisAtenciones.class)
				.setParameter("ubigeo", ubigeo)
				.setParameter("doc", tipoDoc)
				.setParameter("mes", mes)
				.setParameter("anio", anio).getResultList();
	}

	@Override
	public List<HisAtenciones> listarAtencionesErroneasSinDistrito(String ubigeo, String tipoDoc, String longitud) {
		// TODO Auto-generated method stub

		try {
			Long longitud2= Long.parseLong(longitud);
			List<HisAtenciones> listarAtencionesFiltradosSinDistrito=
					em.createQuery("from HisAtenciones where substring(ubigeo,1,4)=: ubigeo and tipoDoc=:doc and (len(nroDoc)<:longitud2 or len(nroDoc)>:longitud2) ",HisAtenciones.class)
					.setParameter("ubigeo", ubigeo)
					.setParameter("doc", tipoDoc)
					.setParameter("longitud2",longitud2)
					.getResultList();
			return listarAtencionesFiltradosSinDistrito;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("----------error-------");
			System.out.println(e);
		}
		return null;
	}

	@Override
	public List<HisAtenciones> listarAtencionesErroneasConDistrito(String ubigeo, String tipoDoc, String longitud) {
		// TODO Auto-generated method stub
		try {
			Long longitud2= Long.parseLong(longitud);
			List<HisAtenciones> listarAtencionesFiltradosSinDistrito=
					em.createQuery("from HisAtenciones where ubigeo =: ubigeo and tipoDoc=:doc and (len(nroDoc)<:longitud2 or len(nroDoc)>:longitud2) ",HisAtenciones.class)
					.setParameter("ubigeo", ubigeo)
					.setParameter("doc", tipoDoc)
					.setParameter("longitud2", longitud2)
					.getResultList();
			return listarAtencionesFiltradosSinDistrito;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("----------error-------");
			System.out.println(e);
		}
		return null;
	}


	





}
