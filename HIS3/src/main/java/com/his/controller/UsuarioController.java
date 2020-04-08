package com.his.controller;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.his.model.Rol;
import com.his.model.Usuario;
import com.his.paginador.PageRender;
import com.his.service.UsuarioService;
import com.itextpdf.text.log.SysoCounter;



@Controller
@RequestMapping("/usuario")
@SessionAttributes("usuario")
public class UsuarioController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/crear")
	public String crear() {
		return "/views/usuario/creacionUsuario";
	}
	

	@RequestMapping(value="/guardar", method=RequestMethod.POST,consumes={ MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String guardar(@RequestBody Map<String, Object> json) {
		System.out.println(json);
		if(json.get("idUsuario")==null) {
			String nombre= (String)json.get("nombre");
			String apellidoPaterno= (String) json.get("apellidoPaterno");
			String apellidoMaterno= (String) json.get("apellidoMaterno");
			String nombreUsuario= (String) json.get("nombreUsuario");
			String contrasenia= (String) json.get("contrasenia");
			List<String>listRol= (List<String>) json.get("listRol");
			
			Usuario usuario=new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellidoPaterno(apellidoPaterno);
			usuario.setApellidoMaterno(apellidoMaterno);
			usuario.setNombreUsuario(nombreUsuario);
			contrasenia=passwordEncoder.encode(contrasenia);
			usuario.setContrasenia(contrasenia);
			//usuario.setIdUsuario(5);
			usuarioService.save(usuario);
			
			for(int i=0;i<listRol.size();i++) {
				Rol rol=new Rol();
				rol.setNombre(listRol.get(i));
				rol.setUsuario(usuario);
				usuarioService.saveRol(rol);
			}
		}
		else {
			int idUsuario= (int) json.get("idUsuario");
			String nombre= (String)json.get("nombre");
			String apellidoPaterno= (String) json.get("apellidoPaterno");
			String apellidoMaterno= (String) json.get("apellidoMaterno");
			String nombreUsuario= (String) json.get("nombreUsuario");
			String contrasenia= (String) json.get("contrasenia");
			/*SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
			Date fechaDate = null;
			String creacion =(String) json.get("creacion");
			System.out.println("creacion"+creacion);
			try {
				fechaDate=formato.parse(creacion);
				System.out.println(fechaDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			Date date = new Date( );
			List<String>listRol= (List<String>) json.get("listRol");
			Usuario usuario=new Usuario();
			usuario.setIdUsuario(idUsuario);
			usuario.setNombre(nombre);
			usuario.setApellidoPaterno(apellidoPaterno);
			usuario.setApellidoMaterno(apellidoMaterno);
			usuario.setNombreUsuario(nombreUsuario);
			usuario.setContrasenia(contrasenia);
			usuario.setCreacion(date);
			usuarioService.save(usuario);
			
			
			List<String> lista_borrar=usuarioService.listaStringRol(idUsuario);
			List<String> lista_guardar= new ArrayList<String>();
			for(int i=0;i<listRol.size();i++) {
				lista_guardar.add(listRol.get(i));
			}
			System.out.println("lista_rol: "+lista_guardar);
			/*------------------------------------INTERSECCION DE LISTAS-------------------------------------------------*/
			List<String> interseccion=new ArrayList<String>();
			for(int i=0;i<lista_borrar.size();i++) {
				if(lista_guardar.contains(lista_borrar.get(i))) {
					interseccion.add(lista_borrar.get(i));
				}
			}
			System.out.println("-----------------------------------------------");
			System.out.println("lista_borrar: "+lista_borrar);
			System.out.println("lista_guardar: "+lista_guardar);
			System.out.println("interseccion: "+interseccion);
			System.out.println("------------------------------------------------");
			/*-------------------------------Eliminar datos de lista original-------------------------------*/
			List<String> lista_original2=lista_borrar;
			for(int i=0;i<lista_original2.size();i++) {
				for(int j=0;j<interseccion.size();j++) {
					if(interseccion.get(j).equals(lista_original2.get(i))) {
						lista_borrar.remove(i);
					}
				}
			}
			System.out.println("lista_borrar:"+lista_borrar);
			/*-------------------------------Eliminar datos de lista front------------------------*/
			List<String> lista_frond2=lista_guardar;
			for(int i=0;i<lista_frond2.size();i++) {
				for(int j=0;j<interseccion.size();j++) {
					if(interseccion.get(j).equals(lista_frond2.get(i))) {
						lista_guardar.remove(i);
					}
				}
			}
			System.out.println("lista_guardar :"+lista_guardar);
			/*---------------------------------------Editar Rol-----------------------------------------*/
			if(lista_borrar.size()>0) {
				for(int i=0;i<lista_borrar.size();i++) {
					System.out.println("borrar: "+lista_borrar.get(i));
					//usuarioService.saveRol(rol);
					usuarioService.delete(lista_borrar.get(i), idUsuario);
				}
			}
			if(lista_guardar.size()>0) {
				for(int i=0;i<lista_guardar.size();i++) {
					System.out.println("guardar: "+lista_guardar.get(i));
					Rol rol=new Rol();
					rol.setUsuario(usuario);
					rol.setNombre(lista_guardar.get(i));
					usuarioService.saveRol(rol);
				}
			}
			
			
		}
		return "null";
	}
	
	@RequestMapping(value="/verificar", method=RequestMethod.GET)
	public @ResponseBody int validarUserName(@RequestParam(value="username")String username) {
		System.out.println(username);
		int existencia =usuarioService.findUsername(username);
		System.out.println(existencia);
		if(existencia==0) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	@RequestMapping(value="/listar",method=RequestMethod.GET)
	public String listar(@RequestParam(name="page",defaultValue="0") int page,
			Model model) {
		System.out.println("page controller: "+page);
		/*
		List<Rol> lista=usuarioService.listar();
		List<Rol> union=new ArrayList<Rol>();
		Map<Integer, Rol> Lista_user= new HashMap<Integer, Rol>();
		Map<Integer, Rol> Lista_admin= new HashMap<Integer, Rol>();
		Map<Integer, Rol> limpiar_user= new HashMap<Integer, Rol>();
		Map<Integer, Rol> union_map= new HashMap<Integer, Rol>();
		for(Rol r : lista) {
			if(r.getNombre().equals("ROLE_ADMIN")) {
				Lista_admin.put(r.getUsuario().getIdUsuario(), r);
			}
			else {
				Lista_user.put(r.getUsuario().getIdUsuario(), r);
			}
		}
		for (Integer i : Lista_user.keySet()) {
	        if (!Lista_admin.containsKey(i)) {
	        	limpiar_user.put(i, Lista_user.get(i));
	        }
	    }
	    for (Integer i : Lista_admin.keySet()) {
	        if (!Lista_user.containsKey(i)) {
	        	limpiar_user.put(i, Lista_admin.get(i));
	        }
	    }
	    union_map.putAll(limpiar_user);
	    union_map.putAll(Lista_admin);
	    for(Entry<Integer, Rol> p : union_map.entrySet()) {
	    	union.add(p.getValue());	
		}
		model.addAttribute("lista", union);
		*/
		
		/*-----------------------------------------------------*/
		
		Pageable pagerequest=PageRequest.of(page, 5);
		//Pageable pagerequest2=PageRequest.of(page, 3);
		//Pageable pagerequest3=PageRequest.of(page, 3);
		//Page<Rol> lista_admin=usuarioService.adminOrUserP("ROLE_ADMIN", pagerequest2);
		//Page<Rol> lista_user=usuarioService.adminOrUserP("ROLE_USER", pagerequest3);
		Page<Rol> lista_adminAndUser=usuarioService.adminAndUserP(pagerequest);

		
		//PageRender<Rol> pageRender_admin =new PageRender<>("/listar",lista_admin);
		//PageRender<Rol> pageRender_user =new PageRender<>("/listar",lista_user);
		/*System.out.println("lista_adminAndUser.getTotalElements(): "+lista_adminAndUser.getTotalElements());
		System.out.println("lista_adminAndUser.getSize(): "+lista_adminAndUser.getSize());
		for(Rol r:lista_adminAndUser){
			System.out.println(r.getIdRol());
		}*/
		//PageRender<Rol> pageRender_admin=new PageRender<>("/usuario/listar",lista_admin);
		//PageRender<Rol> pageRender_User=new PageRender<>("/usuario/listar",lista_user);
		PageRender<Rol> pageRender_adminAndUser=new PageRender<>("/usuario/listar",lista_adminAndUser);
		
		//System.out.println(lista_adminAndUser.getSize());
		//model.addAttribute("admin", lista_admin);
		//model.addAttribute("user",lista_user);
		model.addAttribute("adminuser",lista_adminAndUser);
		
		//model.addAttribute("page_admin", pageRender_admin);
		//model.addAttribute("page_user", pageRender_User);
		model.addAttribute("page_adminAndUser", pageRender_adminAndUser);
		
		model.addAttribute("admin", usuarioService.adminOrUser("ROLE_ADMIN"));
		model.addAttribute("user", usuarioService.adminOrUser("ROLE_USER"));
		/*model.addAttribute("adminuser", usuarioService.adminAndUser());*/
		
		return "/views/usuario/listarUsuario";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(value="id")int id, Model model) {
		model.addAttribute("usuario",usuarioService.findUsuario(id));
		Usuario u=usuarioService.findUsuario(id);
		System.out.println(u.getCreacion());
		model.addAttribute("rol",usuarioService.findRolUsuario(id));

		return "/views/usuario/editarUsuario";
	}
	
	
	@GetMapping("/listarPrueba")
	public String listarPrueba(Model model) {
		model.addAttribute("adminAndUser", usuarioService.adminAndUser());
		model.addAttribute("admin", usuarioService.adminOrUser("ROLE_ADMIN"));
		model.addAttribute("user", usuarioService.adminOrUser("ROLE_USER"));
		return "/views/usuario/listarhtml";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id")int id,RedirectAttributes flash) {
		if(id>0) {
			System.out.println(id);
			usuarioService.deleteRol(id);
			usuarioService.deleteUsuario(id);
			flash.addFlashAttribute("success", "PRODUCTO ELIMINADO");
		}
		return "redirect:/usuario/listarPrueba";
	}
}
