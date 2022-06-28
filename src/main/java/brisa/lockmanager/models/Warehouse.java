package brisa.lockmanager.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import brisa.lockmanager.commons.utils.DateUtil;

@Entity
@Table(name = "tb_warehouse")
public class Warehouse extends _BaseModelId {

	private static final long serialVersionUID = -95224151288998710L;

	@Column(name = "name", length = 255, nullable = false)
	private String name;

	@JsonFormat(pattern = DateUtil.DD_MMMM_YYYY_HH_MM)
	@Column(name = "registry_date")
	private Timestamp registryDate;

	@JsonFormat(pattern = DateUtil.DD_MMMM_YYYY_HH_MM)
	@Column(name = "update_date")
	private Timestamp updateDate;

	@Column(name = "address")
	private String address;

	// ---------------------------------------------------------------------------------------------
	// Constructors
	// ---------------------------------------------------------------------------------------------
	public Warehouse() {
		super();
	}

	// ---------------------------------------------------------------------------------------------
	// Transients
	// ---------------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------------
	// get/set
	// ---------------------------------------------------------------------------------------------

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Timestamp getRegistryDate() {
		return this.registryDate;
	}

	public void setRegistryDate(final Timestamp registryDate) {
		this.registryDate = registryDate;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
