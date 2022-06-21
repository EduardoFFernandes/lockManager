package brisa.lockmanager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_lock_model")
public class LockModel extends _BaseModelId {

    private static final long serialVersionUID = -2662409103201603452L;

    @Column(name = "name")
    private String name;
    @Column(name = "firmware_version")
    private String firmwareVersion;

    // ---------------------------------------------------------------------------------------------
    // Constructors
    // ---------------------------------------------------------------------------------------------
    public LockModel() {
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



    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }


    public void setFirmwareVersion(final String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }


}
