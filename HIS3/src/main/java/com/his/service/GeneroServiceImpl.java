package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.InGeneroDao;
import com.his.model.MSexo;

@Service
public class GeneroServiceImpl implements IGeneroService{

	@Autowired
	private InGeneroDao generoDao;
	
	@Override
	public List<MSexo> listarTodo() {
		// TODO Auto-generated method stub
		return generoDao.listarTodo();
	}

	
}
