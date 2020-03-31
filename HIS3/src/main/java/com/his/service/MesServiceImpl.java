package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.MesDao;
import com.his.model.MMes;

@Service
public class MesServiceImpl implements MesService{

	@Autowired
	private MesDao mesDao;
	
	@Override
	public List<MMes> listarTodo() {
		// TODO Auto-generated method stub
		return mesDao.listarTodo();
	}

	
}
