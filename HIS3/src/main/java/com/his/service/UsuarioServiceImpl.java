package com.his.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.his.dao.UsuarioDao;
import com.his.model.Rol;
import com.his.model.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	@Transactional
	public void save(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public void saveRol(Rol rol) {
		// TODO Auto-generated method stub
		usuarioDao.saveRol(rol);
	}

	@Override
	public int findUsername(String username) {
		// TODO Auto-generated method stub
		return usuarioDao.findUsername(username);
	}

	@Override
	public List<Rol> listar() {
		// TODO Auto-generated method stub
		return usuarioDao.listar();
	}

	@Override
	public List<Usuario> listarUsuario() {
		// TODO Auto-generated method stub
		return usuarioDao.listarUsuario();
	}

	@Override
	public Usuario findUsuario(int id) {
		// TODO Auto-generated method stub
		return usuarioDao.findUsuario(id);
	}

	@Override
	public List<Rol> findRolUsuario(int id) {
		// TODO Auto-generated method stub
		return usuarioDao.findRolUsuario(id);
	}

	@Override
	public List<String> listaStringRol(int id) {
		// TODO Auto-generated method stub
		return usuarioDao.listaStringRol(id);
	}

	@Override
	@Transactional
	public void delete(String rol, int idUsuario) {
		// TODO Auto-generated method stub
		usuarioDao.delete(rol, idUsuario);
	}

	@Override
	public Rol findRolUsuario(String rol, int idUsuario) {
		// TODO Auto-generated method stub
		return usuarioDao.findRolUsuario(rol, idUsuario);
	}

	@Override
	public List<Rol> adminAndUser() {
		// TODO Auto-generated method stub
		return usuarioDao.adminAndUser();
	}

	@Override
	public List<Rol> adminOrUser(String nombreRol) {
		// TODO Auto-generated method stub
		return usuarioDao.adminOrUser(nombreRol);
	}

	/*--------------------------------paginadores--------------------------------*/
	@Override
	public Page<Rol> adminOrUserP(String nombreRol, Pageable pageable) {
		// TODO Auto-generated method stub
		return usuarioDao.adminOrUserP(nombreRol, pageable);
	}

	@Override
	public Page<Rol> adminAndUserP(Pageable pageable) {
		// TODO Auto-generated method stub
		return usuarioDao.adminAndUserP(pageable);
	}
	
	

}
