package com.his.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

	
	/*--------------------------------------------PAGINADORES--------------------------------------------------*/
	@Override
	public Page<HisAtenciones> listarAtencionesFiltradosP(String ubigeo, String tipoDoc, String mes, String anio,
			Pageable pageable) {
		// TODO Auto-generated method stub
		int inicio=pageable.getPageSize()*pageable.getPageNumber();
		int tamanio=pageable.getPageSize();
		try {
			int cantidad=(int) em.createNativeQuery("select count(*) from his_atenciones where ubigeo =:ubigeo and tipo_doc=:doc and mes =:mes and anio=:anio")
								.setParameter("ubigeo", ubigeo)
								.setParameter("doc", tipoDoc)
								.setParameter("mes", mes)
								.setParameter("anio", anio)
								.getSingleResult();
			
			List<HisAtenciones>lista=em.createNativeQuery("select * from his_atenciones where ubigeo =:ubigeo and tipo_doc=:doc and mes =:mes and anio=:anio ORDER BY num_reg OFFSET :inicio ROWS FETCH NEXT :tamanio ROWS ONLY",HisAtenciones.class)
									.setParameter("ubigeo", ubigeo)
									.setParameter("doc", tipoDoc)
									.setParameter("mes", mes)
									.setParameter("anio", anio)
									.setParameter("inicio", inicio)
									.setParameter("tamanio", tamanio)
									.getResultList();
									
			Page<HisAtenciones> page = new PageImpl<>(lista,pageable,cantidad);
			return page;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("----------error-------");
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Page<HisAtenciones> listarAtencionesErroneasSinDistritoP(String ubigeo, String tipoDoc, String longitud,
			Pageable pageable) {
		// TODO Auto-generated method stub
		int inicio=pageable.getPageSize()*pageable.getPageNumber();
		int tamanio=pageable.getPageSize();
		try {
			Long longitud2= Long.parseLong(longitud);
			
			int cantidad=(int)em.createNativeQuery("select count(*) from his_atenciones where substring(ubigeo,1,4)=:ubigeo and tipo_doc=:doc and (len(nro_doc)<:longitud2 or len(nro_doc)>:longitud2) ")
							.setParameter("ubigeo", ubigeo)
							.setParameter("doc", tipoDoc)
							.setParameter("longitud2",longitud2)
							.getSingleResult();
							
			List<HisAtenciones> listarAtencionesFiltradosSinDistrito=
					em.createNativeQuery("select * from his_atenciones where substring(ubigeo,1,4)=:ubigeo and tipo_doc=:doc and (len(nro_doc)<:longitud2 or len(nro_doc)>:longitud2) ORDER BY num_reg OFFSET :inicio ROWS FETCH NEXT :tamanio ROWS ONLY",HisAtenciones.class)
					.setParameter("ubigeo", ubigeo)
					.setParameter("doc", tipoDoc)
					.setParameter("longitud2",longitud2)
					.setParameter("inicio", inicio)
					.setParameter("tamanio", tamanio)
					.getResultList();
			
			Page<HisAtenciones> page = new PageImpl<>(listarAtencionesFiltradosSinDistrito,pageable,cantidad);
			return page;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("----------error-------");
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Page<HisAtenciones> listarAtencionesErroneasConDistritoP(String ubigeo, String tipoDoc, String longitud,
			Pageable pageable) {
		// TODO Auto-generated method stub
		int inicio=pageable.getPageSize()*pageable.getPageNumber();
		int tamanio=pageable.getPageSize();
		try {
			
			Long longitud2= Long.parseLong(longitud);
			
			int cantidad=(int) em.createNativeQuery("select count(*) from his_atenciones where ubigeo =:ubigeo and tipo_doc=:doc and (len(nro_doc)<:longitud2 or len(nro_doc)>:longitud2) ")
							.setParameter("ubigeo", ubigeo)
							.setParameter("doc", tipoDoc)
							.setParameter("longitud2", longitud2)
							.getSingleResult();
			
			
			List<HisAtenciones> listarAtencionesFiltradosSinDistrito=
					em.createNativeQuery("select * from his_atenciones where ubigeo =:ubigeo and tipo_doc=:doc and (len(nro_doc)<:longitud2 or len(nro_doc)>:longitud2) ORDER BY num_reg OFFSET :inicio ROWS FETCH NEXT :tamanio ROWS ONLY",HisAtenciones.class)
					.setParameter("ubigeo", ubigeo)
					.setParameter("doc", tipoDoc)
					.setParameter("longitud2", longitud2)
					.setParameter("inicio", inicio)
					.setParameter("tamanio", tamanio)
					.getResultList();
			Page<HisAtenciones> page = new PageImpl<>(listarAtencionesFiltradosSinDistrito,pageable,cantidad);
			return page;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("----------error-------");
			System.out.println(e);
		}
		return null;
	}

}
