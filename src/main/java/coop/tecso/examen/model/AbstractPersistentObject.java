package coop.tecso.examen.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Abstract base persistent object that provides object identification and basic
 * auditory fields.
 */
@MappedSuperclass
public abstract class AbstractPersistentObject implements Serializable {

	private static final long serialVersionUID = -975560023284258938L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object other) {
        if (super.equals(other)) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (this.getClass() != other.getClass()) {
            return false;
        }

        AbstractPersistentObject persistentObject = (AbstractPersistentObject) other;
        return getId() != null && getId().equals(persistentObject.getId());

    }


    @Override
    public int hashCode() {

        if (getId() != null) {
            return this.getClass().hashCode() + getId().hashCode();
        } else {
            return super.hashCode();
        }
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass());
        sb.append(" ID:");
        sb.append(id);
        return sb.toString();
    }

    @Transient
    public String getDescription() {
        return toString();
    }

}
