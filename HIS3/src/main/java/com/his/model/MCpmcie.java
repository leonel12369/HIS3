package com.his.model;
// Generated 12/03/2020 10:16:04 AM by Hibernate Tools 5.1.7.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MCpmcie generated by hbm2java
 */
@Entity
@Table(name = "m_cpmcie")
public class MCpmcie implements java.io.Serializable {

	private String codCpmcie;
	private String descCpmcie;

	public MCpmcie() {
	}

	public MCpmcie(String codCpmcie) {
		this.codCpmcie = codCpmcie;
	}

	public MCpmcie(String codCpmcie, String descCpmcie) {
		this.codCpmcie = codCpmcie;
		this.descCpmcie = descCpmcie;
	}

	@Id

	@Column(name = "cod_cpmcie", unique = true, nullable = false, length = 15)
	public String getCodCpmcie() {
		return this.codCpmcie;
	}

	public void setCodCpmcie(String codCpmcie) {
		this.codCpmcie = codCpmcie;
	}

	@Column(name = "desc_cpmcie")
	public String getDescCpmcie() {
		return this.descCpmcie;
	}

	public void setDescCpmcie(String descCpmcie) {
		this.descCpmcie = descCpmcie;
	}

}
