package brisa.lockmanager.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import brisa.lockmanager.commons.utils.DateUtil;

@Entity
@Table(name = "tb_client")
public class Client extends _BaseModelId {

	private static final long serialVersionUID = -2188114092186464428L;

	@JsonFormat(pattern = DateUtil.DD_MMMM_YYYY_HH_MM)
	@Column(name = "registry_date")
	private Timestamp registryDate;

	@JsonFormat(pattern = DateUtil.DD_MMMM_YYYY_HH_MM)
	@Column(name = "update_date")
	private Timestamp updateDate;

	@Column(length = 255, nullable = false)
	private String name;

	@Column(length = 20, nullable = false)
	private String cellphone;

	@Column(length = 255, nullable = false)
	private String email;

	@Column(length = 30, nullable = false)
	private String identifier;

	@Column
	private String address;

	// ---------------------------------------------------------------------------------------------
	// Constructors
	// ---------------------------------------------------------------------------------------------
	public Client() {
		super();
	}

	// ---------------------------------------------------------------------------------------------
	// get/set
	// ---------------------------------------------------------------------------------------------

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

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getCellphone() {
		return "(" + this.cellphone.substring(0, 2) + ") " + this.cellphone.substring(2, 12) ;
	}

	public void setCellphone(final String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
