package com.his.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.his.model.HisAtenciones;

public interface AtencionesService {
	
	public List<HisAtenciones> listarAtencionesFiltrados(String ubigeo,String tipoDoc, String mes,String anio);
	
	public List<HisAtenciones> listarAtencionesErroneasSinDistrito(String ubigeo,String tipoDoc,String longitud);

	public List<HisAtenciones> listarAtencionesErroneasConDistrito(String ubigeo,String tipoDoc,String longitud);
	
	
	/*-----------------------------PAGINADORES------------------------------------*/
	public Page<HisAtenciones> listarAtencionesFiltradosP(String ubigeo,String tipoDoc, String mes,String anio,Pageable pageable );
	
	public Page<HisAtenciones> listarAtencionesErroneasSinDistritoP(String ubigeo,String tipoDoc,String longitud,Pageable pageable );
	
	public Page<HisAtenciones> listarAtencionesErroneasConDistritoP(String ubigeo,String tipoDoc,String longitud,Pageable pageable );
	
}
