package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.TipoDocDao;
import com.his.model.TipoDoc;

@Service
public class TipoDocServiceImpl implements TipoDocService{

	@Autowired
	private TipoDocDao tipoDocDao;
	
	@Override
	public List<TipoDoc> listarTodo() {
		// TODO Auto-generated method stub
		return tipoDocDao.listarTodo();
	}

	
}
