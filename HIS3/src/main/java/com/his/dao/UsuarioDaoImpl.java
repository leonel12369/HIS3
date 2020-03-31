package com.his.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.his.model.Rol;
import com.his.model.Usuario;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {

	@Autowired
	private EntityManager em;
	
	@Override
	public void save(Usuario usuario) {
		// TODO Auto-generated method stub
		try {
			if(usuario.getIdUsuario()>0) {
				em.merge(usuario);
			}
			else {
				em.persist(usuario);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("----------------error----------");
			System.out.println(e);
		}
	}

	@Override
	public void saveRol(Rol rol) {
		// TODO Auto-generated method stub
		try {
			if(rol.getIdRol()>0) {
				em.merge(rol);
			}
			else {
				em.persist(rol);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("-----------error-------");
			System.out.println(e);
		}
	}

	@Override
	public int findUsername(String username) {
		// TODO Auto-generated method stub
		try {
			Usuario usuario=(Usuario) em.createQuery("from Usuario where nombreUsuario =: username",Usuario.class).setParameter("username", username).getSingleResult();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	public List<Rol> listar() {
		// TODO Auto-generated method stub
		try {
			return em.createQuery("from Rol order by usuario",Rol.class).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
			System.out.println(e);
		}
		return null;
	}

	@Override
	public List<Usuario> listarUsuario() {
		// TODO Auto-generated method stub
		try {
			return em.createQuery("from Usuario order by idUsuario",Usuario.class).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("-error-");
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Usuario findUsuario(int id) {
		// TODO Auto-generated method stub
		try {
			return em.createQuery("from Usuario where idUsuario =: id",Usuario.class).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("-error-");
			System.out.println(e);
		}
		return null;
	}

	@Override
	public List<Rol> findRolUsuario(int id) {
		// TODO Auto-generated method stub
		try {
			return em.createQuery("from Rol where id_usuario =: id",Rol.class).setParameter("id", id).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("-error-");
			System.out.println(e);
		}
		return null;
	}

	@Override
	public List<String> listaStringRol(int id) {
		// TODO Auto-generated method stub
		try {
			return em.createQuery("select nombre from Rol where id_usuario =: id").setParameter("id", id).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("-error-");
			System.out.println(e);
		}
		return null;
	}

	@Override
	public void delete(String rol, int idUsuario) {
		// TODO Auto-generated method stub
		Rol objeto=findRolUsuario( rol,  idUsuario);
		em.remove(objeto);
	}

	@Override
	public Rol findRolUsuario(String rol, int idUsuario) {
		// TODO Auto-generated method stub
		try {
			return em.createQuery("from Rol where nombre =: rol and id_usuario=: idUsuario",Rol.class).setParameter("rol", rol).setParameter("idUsuario", idUsuario).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("-----------error-------");
			System.out.println(e);
		}
		return null;
	}


	@Override
	public List<Rol> adminAndUser() {
		// TODO Auto-generated method stub
		try {
			return em.createNativeQuery("select id_rol,nombre,id_usuario from( select row_number() over(PARTITION BY t1.id_usuario order by t1.id_usuario) as nro, t1.* from rol t1 inner join (select id_usuario from rol group by id_usuario having COUNT(id_usuario)>1)t2 on t1.id_usuario=t2.id_usuario)X where nro=1",Rol.class).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("-----------error-------");
			System.out.println(e);
		}
		return null;
	}

	@Override
	public List<Rol> adminOrUser(String nombreRol) {
		// TODO Auto-generated method stub
		try {
			return em.createNativeQuery("select t1.* from rol t1 inner join (select id_usuario from rol group by id_usuario having COUNT(id_usuario)<2)t2 on t1.id_usuario=t2.id_usuario  where t1.nombre=:nombre_rol",Rol.class).setParameter("nombre_rol", nombreRol).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("-----------error-------");
			System.out.println(e);
		}
		return null;
	}

	/*----------------------------------pagiandor----------------------*/
	@Override
	public Page<Rol> adminOrUserP(String nombreRol, Pageable pageable) {
		// TODO Auto-generated method stub
		try {
			List<Rol>lista=em.createNativeQuery("select t1.* from rol t1 inner join (select id_usuario from rol group by id_usuario having COUNT(id_usuario)<2)t2 on t1.id_usuario=t2.id_usuario  where t1.nombre=:nombre_rol",Rol.class).setParameter("nombre_rol", nombreRol).getResultList();
			Page<Rol> page = new PageImpl<>(lista,pageable,lista.size());
			System.out.println(page);
			return page;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("-----------error-------");
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Page<Rol> adminAndUserP(Pageable pageable) {
		// TODO Auto-generated method stub
		int inicio=pageable.getPageSize()*pageable.getPageNumber();
		int tamanio=pageable.getPageSize();
		try {
			int cantidad=(int) em.createNativeQuery("select COUNT(*) from( select row_number() over(PARTITION BY t1.id_usuario order by t1.id_usuario) as nro, t1.* from rol t1 inner join (select id_usuario from rol group by id_usuario having COUNT(id_usuario)>1)t2 on t1.id_usuario=t2.id_usuario)X where nro=1").getSingleResult();
			
			
			List<Rol>lista= em.createNativeQuery("select id_rol,nombre,id_usuario  from( select row_number() over(PARTITION BY t1.id_usuario order by t1.id_usuario) as nro, t1.* from rol t1 inner join (select id_usuario from rol group by id_usuario having COUNT(id_usuario)>1)t2 on t1.id_usuario=t2.id_usuario)X where nro=1 ORDER BY id_usuario OFFSET :inicio ROWS FETCH NEXT :tamanio ROWS ONLY",Rol.class)
					.setParameter("inicio", inicio)
					.setParameter("tamanio", tamanio)
					.getResultList();
			
			Page<Rol> page = new PageImpl<>(lista,pageable,cantidad);

			return page;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("---------------------error------------");
			System.out.println(e);
		}
		
		return null;
	}



}

