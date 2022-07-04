package brisa.lockmanager.models;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import brisa.lockmanager.commons.utils.DateUtil;

@Entity
@Table(name = "tb_purchase")
public class Purchase extends _BaseModelId {

	private static final long serialVersionUID = 8339307493249944086L;

	@JsonFormat(pattern = DateUtil.YYYY_MM_DD_T_HH_MM_SS_SSSXXX)
	@Column(name = "registry_date")
	private Timestamp registryDate;

	@JsonFormat(pattern = DateUtil.YYYY_MM_DD_T_HH_MM_SS_SSSXXX)
	@Column(name = "update_date")
	private Timestamp updateDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_client", nullable = false)
	private Client client;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "purchase_date")
	private LocalDate purchaseDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "due_date")
	private LocalDate dueDate;

	// bi-directional many-to-one association to Purchase
	@JsonIgnore
	@OneToMany(mappedBy = "purchase")
	private List<Item> lstPurchaseItem;

	@Transient
	private boolean hasPurchasedItems;

	// ---------------------------------------------------------------------------------------------
	// Constructors
	// ---------------------------------------------------------------------------------------------

	public Purchase() {
		super();
	}

	// ---------------------------------------------------------------------------------------------
	// get/set
	// ---------------------------------------------------------------------------------------------

	public Timestamp getRegistryDate() {
		return registryDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public Client getClient() {
		return client;
	}

	public void setRegistryDate(Timestamp registryDate) {
		this.registryDate = registryDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Item> getLstPurchaseItem() {
		return lstPurchaseItem;
	}

	public void setLstPurchaseItem(List<Item> lstPurchaseItem) {
		this.lstPurchaseItem = lstPurchaseItem;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public boolean isHasPurchasedItems() {
		return this.lstPurchaseItem.size() > 0;
	}

}
