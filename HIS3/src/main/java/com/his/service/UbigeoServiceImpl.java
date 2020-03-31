package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.UbigeoDao;
import com.his.model.MUbigeo;

@Service
public class UbigeoServiceImpl implements UbigeoService{

	@Autowired
	private UbigeoDao ubigeoDao;

	@Override
	public List<MUbigeo> listarDepartamento() {
		// TODO Auto-generated method stub
		return ubigeoDao.listarDepartamento();
	}

	@Override
	public List<String> listarProvincia(String departamento) {
		// TODO Auto-generated method stub
		return ubigeoDao.listarProvincia(departamento);
	}

	@Override
	public List<String> listarDistrito(String departamento, String provincia) {
		// TODO Auto-generated method stub
		return ubigeoDao.listarDistrito(departamento, provincia);
	}

	@Override
	public List<String> extraerIdUbigeo(String departamento, String provincia, String distrito) {
		// TODO Auto-generated method stub
		return ubigeoDao.extraerIdUbigeo(departamento, provincia, distrito);
	}

	@Override
	public Object extraerUbigeoValidado(String departamento, String provincia) {
		// TODO Auto-generated method stub
		return ubigeoDao.extraerUbigeoValidado(departamento, provincia);
	}
}
