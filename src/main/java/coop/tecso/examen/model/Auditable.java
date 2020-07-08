package coop.tecso.examen.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public class Auditable extends AbstractPersistentObject {

	private static final long serialVersionUID = 8056984335550804448L;

	@Version
    private long versionNumber;
    
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date creationTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date modificationTimestamp;

	public long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(long versionNumber) {
		this.versionNumber = versionNumber;
	}

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public Date getModificationTimestamp() {
		return modificationTimestamp;
	}

	public void setModificationTimestamp(Date modificationTimestamp) {
		this.modificationTimestamp = modificationTimestamp;
	}
}
