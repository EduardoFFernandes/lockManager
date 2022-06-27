package brisa.lockmanager.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

    @JsonFormat(pattern = DateUtil.DD_MMMM_YYYY_HH_MM)
    @Column(name = "purchase_date")
    private Timestamp purchaseDate;

    @JsonFormat(pattern = DateUtil.DD_MMMM_YYYY_HH_MM)
    @Column(name = "due_date")
    private Timestamp dueDate;

    // bi-directional many-to-one association to Account
    @JsonIgnore
    @OneToMany(mappedBy = "purchase")
    private List<Item> lstPurchaseItem;

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

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public Timestamp getDueDate() {
        return dueDate;
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

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public List<Item> getLstPurchaseItem() {
        return lstPurchaseItem;
    }

    public void setLstPurchaseItem(List<Item> lstPurchaseItem) {
        this.lstPurchaseItem = lstPurchaseItem;
    }

}
