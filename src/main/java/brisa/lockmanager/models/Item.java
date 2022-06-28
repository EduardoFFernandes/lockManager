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

import com.fasterxml.jackson.annotation.JsonFormat;

import brisa.lockmanager.commons.utils.DateUtil;

@Entity
@Table(name = "tb_item")
public class Item extends _BaseModelId {

    private static final long serialVersionUID = 2872707560912382028L;

    @JsonFormat(pattern = DateUtil.DD_MMMM_YYYY_HH_MM)
    @Column(name = "registry_date")
    private Timestamp registryDate;

    @JsonFormat(pattern = DateUtil.DD_MMMM_YYYY_HH_MM)
    @Column(name = "update_date")
    private Timestamp updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_purchase")
    private Purchase purchase;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lock")
    private Lock lock;

    @Column(length = 50)
    private ItemStatus status;

    private boolean sensor;

    private BigDecimal price;

    @Column(name = "installation_location", length = 150)
    private String installationLocation;

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
}
