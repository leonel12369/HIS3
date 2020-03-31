package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.his.model.HisAtenciones;


public interface AtencionDao  {
	
	public List<HisAtenciones> listarAtencionesFiltrados(String ubigeo,String tipoDoc, String mes,String anio);
	
	public List<HisAtenciones> listarAtencionesErroneasSinDistrito(String ubigeo,String tipoDoc,String longitud);
	
	public List<HisAtenciones> listarAtencionesErroneasConDistrito(String ubigeo,String tipoDoc,String longitud);
}
