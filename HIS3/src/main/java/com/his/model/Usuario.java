package com.his.model;
// Generated 12/03/2020 10:16:04 AM by Hibernate Tools 5.1.7.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "usuario")
public class Usuario implements java.io.Serializable {

	private int idUsuario;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombreUsuario;
	private String contrasenia;
	private Date creacion;
	private Set<Rol> rols = new HashSet<Rol>(0);

	public Usuario() {
	}

	public Usuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String nombreUsuario,
			String contrasenia, Date creacion, Set<Rol> rols) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
		this.creacion = creacion;
		this.rols = rols;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_usuario", unique = true, nullable = false)
	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "nombre", length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "apellido_paterno", length = 50)
	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	@Column(name = "apellido_materno", length = 50)
	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	@Column(name = "nombre_usuario", length = 50)
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Column(name = "contrasenia", length = 250)
	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "creacion", length = 23)
	public Date getCreacion() {
		return this.creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<Rol> getRols() {
		return this.rols;
	}

	public void setRols(Set<Rol> rols) {
		this.rols = rols;
	}

	
	 @PrePersist
	public void prePersist() {
	    creacion =new Date();
	    System.out.println(creacion);
	}
	 
}
