package brisa.lockmanager.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import brisa.lockmanager.commons.utils.DateUtil;

@Entity
@Table(name = "tb_item")
public class Item extends _BaseModelId {


	private static final long serialVersionUID = -4756385321153775454L;

	@JsonFormat(pattern = DateUtil.DD_MMMM_YYYY_HH_MM)
    @Column(name = "registry_date")
    private Timestamp registryDate;

    @JsonFormat(pattern = DateUtil.DD_MMMM_YYYY_HH_MM)
    @Column(name = "update_date")
    private Timestamp updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_purchase")
    @JsonIgnore
    private Purchase purchase;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lock")
    @JsonIgnore
    private Lock lock;

    @Column(length = 50)
    private ItemStatus status;

    private boolean sensor;

    private BigDecimal price;

    @Column(name = "installation_location", length = 150)
    private String installationLocation;
    
    @Transient
    private String lockSerialNumber;

    @Transient
    private String lockModelName;

    // ---------------------------------------------------------------------------------------------
    // Constructors
    // ---------------------------------------------------------------------------------------------
    public Item() {
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

    public Purchase getPurchase() {
        return purchase;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getInstallationLocation() {
        return installationLocation;
    }

    public void setRegistryDate(Timestamp registryDate) {
        this.registryDate = registryDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public boolean isSensor() {
        return sensor;
    }

    public void setSensor(boolean sensor) {
        this.sensor = sensor;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setInstallationLocation(String installationLocation) {
        this.installationLocation = installationLocation;
    }

	public String getLockSerialNumber() {
		if (this.lock != null) {
			return lock.getSerialNumber();
		}
		return null;
	}

	public String getLockModelName() {
		if (this.lock != null && this.lock.getLockModel() != null) {
			return lock.getLockModel().getName();
		}
		return null;
	}

}
