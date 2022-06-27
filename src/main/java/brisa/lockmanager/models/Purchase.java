package brisa.lockmanager.models;

import java.sql.Timestamp;
import java.util.Date;
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

    @JsonFormat(pattern = DateUtil.YYYY_MM_DD_T_HH_MM_SS_SSSXXX)
    @Column(name = "purchase_date")
    private Date purchaseDate;

    @JsonFormat(pattern = DateUtil.YYYY_MM_DD_T_HH_MM_SS_SSSXXX)
    @Column(name = "due_date")
    private Date dueDate;

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

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

}
