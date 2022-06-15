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

@Entity
@Table(name = "tb_warehouse")
public class Warehouse extends _BaseModelId {

    private static final long serialVersionUID = -95224151288998710L;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @JsonFormat(pattern = DateUtil.YYYY_MM_DD_T_HH_MM_SS_SSSXXX)
    @Column(name = "registry_date")
    private Timestamp registryDate;

    @JsonFormat(pattern = DateUtil.YYYY_MM_DD_T_HH_MM_SS_SSSXXX)
    @Column(name = "update_date")
    private Timestamp updateDate;

    @Column(name = "id_address", updatable = false, insertable = false)
    private Long idAddress;

    //uni-directional many-to-one association to Address
    @JsonIgnoreProperties({
            "hibernateLazyInitializer",
            "handler"
    })
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address", nullable = false)
    private Address address;

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

    public Long getIdAddress() {
        return this.idAddress;
    }

    public void setIdAddress(final Long idAddress) {
        this.idAddress = idAddress;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }
}
