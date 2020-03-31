package com.his.model;
// Generated 12/03/2020 10:16:04 AM by Hibernate Tools 5.1.7.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * HisMaestroPersonal generated by hbm2java
 */
@Entity
@Table(name = "his_maestro_personal")
public class HisMaestroPersonal implements java.io.Serializable {

	private String dni;
	private String codpsal;
	private String nombre;
	private Integer plaza;
	private String cod2000;
	private String codProf;
	private String codCond;
	private Date fechaIng;
	private Date fechaBaja;
	private Set<HisAtenciones> hisAtencioneses = new HashSet<HisAtenciones>(0);

	public HisMaestroPersonal() {
	}

	public HisMaestroPersonal(String dni) {
		this.dni = dni;
	}

	public HisMaestroPersonal(String dni, String codpsal, String nombre, Integer plaza, String cod2000, String codProf,
			String codCond, Date fechaIng, Date fechaBaja, Set<HisAtenciones> hisAtencioneses) {
		this.dni = dni;
		this.codpsal = codpsal;
		this.nombre = nombre;
		this.plaza = plaza;
		this.cod2000 = cod2000;
		this.codProf = codProf;
		this.codCond = codCond;
		this.fechaIng = fechaIng;
		this.fechaBaja = fechaBaja;
		this.hisAtencioneses = hisAtencioneses;
	}

	@Id

	@Column(name = "dni", unique = true, nullable = false, length = 8)
	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Column(name = "codpsal", length = 11)
	public String getCodpsal() {
		return this.codpsal;
	}

	public void setCodpsal(String codpsal) {
		this.codpsal = codpsal;
	}

	@Column(name = "nombre", length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "plaza", precision = 6, scale = 0)
	public Integer getPlaza() {
		return this.plaza;
	}

	public void setPlaza(Integer plaza) {
		this.plaza = plaza;
	}

	@Column(name = "cod_2000", length = 9)
	public String getCod2000() {
		return this.cod2000;
	}

	public void setCod2000(String cod2000) {
		this.cod2000 = cod2000;
	}

	@Column(name = "cod_prof", length = 2)
	public String getCodProf() {
		return this.codProf;
	}

	public void setCodProf(String codProf) {
		this.codProf = codProf;
	}

	@Column(name = "cod_cond", length = 2)
	public String getCodCond() {
		return this.codCond;
	}

	public void setCodCond(String codCond) {
		this.codCond = codCond;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_ing", length = 10)
	public Date getFechaIng() {
		return this.fechaIng;
	}

	public void setFechaIng(Date fechaIng) {
		this.fechaIng = fechaIng;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_baja", length = 10)
	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hisMaestroPersonal")
	public Set<HisAtenciones> getHisAtencioneses() {
		return this.hisAtencioneses;
	}

	public void setHisAtencioneses(Set<HisAtenciones> hisAtencioneses) {
		this.hisAtencioneses = hisAtencioneses;
	}

}
