package brisa.lockmanager.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class _BaseModelId extends _BaseModel {

    private static final long serialVersionUID = 975871428696810270L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    // ---------------------------------------------------------------------------------------------
    // get/set
    // ---------------------------------------------------------------------------------------------
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    // ---------------------------------------------------------------------------------------------
    // * @see java.lang.Object#equals(java.lang.Object)
    // ---------------------------------------------------------------------------------------------
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final _BaseModelId other = (_BaseModelId) obj;
        return Objects.equals(this.id, other.id);
    }

    // ---------------------------------------------------------------------------------------------
    // * @see java.lang.Object#hashCode()
    // ---------------------------------------------------------------------------------------------
    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
}
