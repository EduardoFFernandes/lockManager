package brisa.lockmanager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_address")
public class Address extends _BaseModelId {

    private static final long serialVersionUID = -4653836588127667150L;

    @Column(length = 150)
    private String address;

    @Column(length = 50)
    private String city;

    @Column(name = "country_iso2", nullable = false, length = 2)
    private String countryIso2;

    @Column(name = "country_name", nullable = false, length = 50)
    private String countryName;

    @Column(name = "country_time_zone", length = 50)
    private String countryTimeZone;

    @Column(name = "country_utc_offset", length = 6)
    private String countryUtcOffset;

    @Column(length = 50)
    private String state;

    @Column(name = "zip_code", length = 10)
    private String zipCode;

    // ---------------------------------------------------------------------------------------------
    // Constructors
    // ---------------------------------------------------------------------------------------------
    public Address() {
        super();
    }

    public Address(final String countryIso2, final String countryName, final String countryTimeZone, final String countryUtcOffset) {
        this();
        this.countryIso2 = countryIso2;
        this.countryName = countryName;
        this.countryTimeZone = countryTimeZone;
        this.countryUtcOffset = countryUtcOffset;
    }

    // ---------------------------------------------------------------------------------------------
    // get/set
    // ---------------------------------------------------------------------------------------------
    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getCountryIso2() {
        return this.countryIso2;
    }

    public void setCountryIso2(final String countryIso2) {
        this.countryIso2 = countryIso2;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(final String countryName) {
        this.countryName = countryName;
    }

    public String getCountryTimeZone() {
        return this.countryTimeZone;
    }

    public void setCountryTimeZone(final String countryTimeZone) {
        this.countryTimeZone = countryTimeZone;
    }

    public String getState() {
        return this.state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountryUtcOffset() {
        return this.countryUtcOffset;
    }

    public void setCountryUtcOffset(final String countryUtcOffset) {
        this.countryUtcOffset = countryUtcOffset;
    }

}