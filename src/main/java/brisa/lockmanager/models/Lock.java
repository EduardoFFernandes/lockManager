package brisa.lockmanager.models;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import brisa.lockmanager.commons.utils.DateUtil;

/**
 * The persistent class for the tb_lock database table.
 *
 */
@Entity
@Table(name = "tb_lock")
//@SerialNumberUnique(fieldId = "id", fieldSerialNumber = "serialNumber")
public class Lock extends _BaseModelId {

	private static final long serialVersionUID = 929153996749512858L;

	@Column(name = "serial_number", nullable = false)
	private String serialNumber;

	@Column(name = "address")
	private String address;

	@JsonFormat(pattern = DateUtil.YYYY_MM_DD_T_HH_MM_SS_SSSXXX)
	@Column(name = "registry_date")
	private Timestamp registryDate;

	@JsonFormat(pattern = DateUtil.YYYY_MM_DD_T_HH_MM_SS_SSSXXX)
	@Column(name = "update_date")
	private Timestamp updateDate;

	// uni-directional many-to-one association to Address
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_model", nullable = false)
	private LockModel lockModel;

	// uni-directional many-to-one association to Address
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_client", nullable = false)
	private Client client;

	// uni-directional many-to-one association to Address
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_warehouse", nullable = false)
	private Warehouse warehouse;

	// ---------------------------------------------------------------------------------------------
	// Constructors
	// ---------------------------------------------------------------------------------------------
	public Lock() {
		super();
	}

	// ---------------------------------------------------------------------------------------------
	// Transients
	// ---------------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------------
	// get/set
	// ---------------------------------------------------------------------------------------------

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(final String serialNumber) {
		this.serialNumber = serialNumber;
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

	public LockModel getLockModel() {
		return this.lockModel;
	}

	public void setLockModel(final LockModel lockModel) {
		this.lockModel = lockModel;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(final Client client) {
		this.client = client;
	}

	public Warehouse getWarehouse() {
		return this.warehouse;
	}

	public void setWarehouse(final Warehouse warehouse) {
		this.warehouse = warehouse;
	}

}
