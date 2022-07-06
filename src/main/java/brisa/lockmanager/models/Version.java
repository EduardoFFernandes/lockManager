package brisa.lockmanager.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import brisa.lockmanager.commons.utils.DateUtil;

@Entity
@Table(name = "tb_version")
public class Version extends _BaseModelId {

    private static final long serialVersionUID = -95224151288998710L;

    @JsonFormat(pattern = DateUtil.DD_MMMM_YYYY_HH_MM)
    @Column(name = "registry_date")
    private Timestamp registryDate;

    @JsonFormat(pattern = DateUtil.DD_MMMM_YYYY_HH_MM)
    @Column(name = "update_date")
    private Timestamp updateDate;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(name = "release_notes")
    private String releaseNotes;

    // ---------------------------------------------------------------------------------------------
    // Constructors
    // ---------------------------------------------------------------------------------------------
    public Version() {
        super();
    }

    // ---------------------------------------------------------------------------------------------
    // get/set
    // ---------------------------------------------------------------------------------------------

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getReleaseNotes() {
        return releaseNotes;
    }

    public void setReleaseNotes(String releaseNotes) {
        this.releaseNotes = releaseNotes;
    }

    public Timestamp getRegistryDate() {
        return registryDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setRegistryDate(Timestamp registryDate) {
        this.registryDate = registryDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

}
