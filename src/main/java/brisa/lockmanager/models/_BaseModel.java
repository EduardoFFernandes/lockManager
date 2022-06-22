package brisa.lockmanager.models;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@MappedSuperclass
public class _BaseModel implements Serializable {

    private static final long serialVersionUID = -7997398371288457042L;

    // ---------------------------------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------------------------------
    public _BaseModel() {
        super();
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    // ---------------------------------------------------------------------------------------------
    // Enums
    // ---------------------------------------------------------------------------------------------
    public enum Role {

        ROLE_ADMIN("ADMIN"),
        ROLE_CUSTOMER("CUSTOMER");

        public static final String ROLE_ADMIN_STR = "ROLE_ADMIN";
        public static final String ROLE_CUSTOMER_STR = "ROLE_CUSTOMER";

        private String templateName;

        // ---------------------------------------------------------------------------------------------
        // Constructors
        // ---------------------------------------------------------------------------------------------
        private Role(final String templateName) {
            this.templateName = templateName;
        }

        // ---------------------------------------------------------------------------------------------
        // get
        // ---------------------------------------------------------------------------------------------
        public String templateName() {
            return this.templateName;
        }
    }

    public enum ItemStatus {

        INSTALLED,
        OFF,
        DEFECTIVE

    }
}
