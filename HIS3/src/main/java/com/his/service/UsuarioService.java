package com.his.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.his.model.Rol;
import com.his.model.Usuario;

public interface UsuarioService {

	public void save(Usuario  usuario);
	
	public void saveRol(Rol rol);
	
	public int findUsername(String username);
	
	public List<Rol> listar();
	
	public List<Usuario> listarUsuario();
	
	public Usuario findUsuario(int id);
	
	public List<Rol> findRolUsuario(int id);
	
	public List<String> listaStringRol(int id);
	
	public void delete(String rol,int idUsuario);
	
	public Rol findRolUsuario(String rol,int idUsuario);
	
	public List<Rol> adminOrUser(String nombreRol);
	
	public List<Rol> adminAndUser();
	
	
	/*------------------------------------Paginadores----------------*/
	
	public Page<Rol> adminOrUserP(String nombreRol,Pageable pageable) ;
	
	public Page<Rol> adminAndUserP(Pageable pageable);
}
