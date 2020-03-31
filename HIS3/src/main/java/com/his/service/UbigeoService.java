package com.his.service;

import java.util.List;

import com.his.model.MUbigeo;

public interface UbigeoService {

	public List<MUbigeo> listarDepartamento();
	
	public List<String> listarProvincia(String departamento);
	
	public List<String> listarDistrito(String departamento, String provincia);
	
	public List<String> extraerIdUbigeo(String departamento, String provincia, String distrito);
	
	public Object extraerUbigeoValidado(String departamento, String provincia);
}
