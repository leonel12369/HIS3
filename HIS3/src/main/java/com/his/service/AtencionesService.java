package com.his.service;

import java.util.List;

import com.his.model.HisAtenciones;

public interface AtencionesService {
	
	public List<HisAtenciones> listarAtencionesFiltrados(String ubigeo,String tipoDoc, String mes,String anio);
	
	public List<HisAtenciones> listarAtencionesErroneasSinDistrito(String ubigeo,String tipoDoc,String longitud);

	public List<HisAtenciones> listarAtencionesErroneasConDistrito(String ubigeo,String tipoDoc,String longitud);
}
