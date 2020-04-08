package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.his.dao.AtencionDao;
import com.his.model.HisAtenciones;

@Service
public class AtencionesServiceImpl implements AtencionesService{

	@Autowired
	private AtencionDao atencionDao;
	
	@Override
	public List<HisAtenciones> listarAtencionesFiltrados(String ubigeo, String tipoDoc, String mes, String anio) {
		// TODO Auto-generated method stub
		return atencionDao.listarAtencionesFiltrados(ubigeo, tipoDoc, mes, anio);
	}

	@Override
	public List<HisAtenciones> listarAtencionesErroneasSinDistrito(String ubigeo, String tipoDoc, String longitud) {
		// TODO Auto-generated method stub
		return atencionDao.listarAtencionesErroneasSinDistrito(ubigeo, tipoDoc, longitud);
	}

	@Override
	public List<HisAtenciones> listarAtencionesErroneasConDistrito(String ubigeo, String tipoDoc, String longitud) {
		// TODO Auto-generated method stub
		return atencionDao.listarAtencionesErroneasConDistrito(ubigeo, tipoDoc, longitud);
	}
	
	
	
	
	/*--------------------------------------------PAGINADORES--------------------------------------------------*/
	
	
	@Override
	public Page<HisAtenciones> listarAtencionesFiltradosP(String ubigeo, String tipoDoc, String mes, String anio,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return atencionDao.listarAtencionesFiltradosP(ubigeo, tipoDoc, mes, anio, pageable);
	}

	@Override
	public Page<HisAtenciones> listarAtencionesErroneasSinDistritoP(String ubigeo, String tipoDoc, String longitud,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return atencionDao.listarAtencionesErroneasSinDistritoP(ubigeo, tipoDoc, longitud, pageable);
	}

	@Override
	public Page<HisAtenciones> listarAtencionesErroneasConDistritoP(String ubigeo, String tipoDoc, String longitud,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return atencionDao.listarAtencionesErroneasConDistritoP(ubigeo, tipoDoc, longitud, pageable);
	}


	
}
