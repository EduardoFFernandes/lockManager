package brisa.lockmanager.models;

import java.util.List;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeLocksObject {

    @Transient
    private Version version;
    @Transient
    private List<Lock> lstLock;

    // ---------------------------------------------------------------------------------------------
    // Constructors
    // ---------------------------------------------------------------------------------------------
    public ChangeLocksObject() {
        super();
    }

    // ---------------------------------------------------------------------------------------------
    // get/set
    // ---------------------------------------------------------------------------------------------

    public List<Lock> getLstLock() {
        return lstLock;
    }

    public void setLstLock(List<Lock> lstLock) {
        this.lstLock = lstLock;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

}
