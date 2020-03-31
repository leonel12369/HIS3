package com.his.model;
// Generated 12/03/2020 10:16:04 AM by Hibernate Tools 5.1.7.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MTurnoAtencion generated by hbm2java
 */
@Entity
@Table(name = "m_turno_atencion")
public class MTurnoAtencion implements java.io.Serializable {

	private String codAtencion;
	private String descAtencion;
	private Set<HisAtenciones> hisAtencioneses = new HashSet<HisAtenciones>(0);

	public MTurnoAtencion() {
	}

	public MTurnoAtencion(String codAtencion) {
		this.codAtencion = codAtencion;
	}

	public MTurnoAtencion(String codAtencion, String descAtencion, Set<HisAtenciones> hisAtencioneses) {
		this.codAtencion = codAtencion;
		this.descAtencion = descAtencion;
		this.hisAtencioneses = hisAtencioneses;
	}

	@Id

	@Column(name = "cod_atencion", unique = true, nullable = false, length = 1)
	public String getCodAtencion() {
		return this.codAtencion;
	}

	public void setCodAtencion(String codAtencion) {
		this.codAtencion = codAtencion;
	}

	@Column(name = "desc_atencion", length = 6)
	public String getDescAtencion() {
		return this.descAtencion;
	}

	public void setDescAtencion(String descAtencion) {
		this.descAtencion = descAtencion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "MTurnoAtencion")
	public Set<HisAtenciones> getHisAtencioneses() {
		return this.hisAtencioneses;
	}

	public void setHisAtencioneses(Set<HisAtenciones> hisAtencioneses) {
		this.hisAtencioneses = hisAtencioneses;
	}

}
