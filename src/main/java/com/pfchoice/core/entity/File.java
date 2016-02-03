package com.pfchoice.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author sarath
 */
@Entity(name = "file")
public class File implements Serializable
{
	
    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="file_id", nullable = false)
    private Integer id;

    @Column(name="file_name")
    private String fileName;
    
    @Column(name="file_type_code")
    private Integer fileTypeCode;
    
    @Column(name="created_date")
    private Timestamp createdDate;
    
    @Column(name="updated_date")
    private Timestamp updatedDate;
    
    @Column(name="created_by")
    private String createdBy;
    
    @Column(name="updated_by")
    private String updatedBy;
    
    @Column(name="active_ind")
    private char activeInd;
    
    public File()
    {
    }

    public File(final Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(final Integer id)
    {
        this.id = id;
    }
    
    /**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileTypeCode
	 */
	public Integer getFileTypeCode() {
		return fileTypeCode;
	}

	/**
	 * @param fileTypeCode the fileTypeCode to set
	 */
	public void setFileTypeCode(final Integer fileTypeCode) {
		this.fileTypeCode = fileTypeCode;
	}

	/**
	 * @return the createdDate
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(final Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(final Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param credtedBy the credtedBy to set
	 */
	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(final String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the activeInd
	 */
	public char getActiveInd() {
		return activeInd;
	}

	/**
	 * @param activeInd the activeInd to set
	 */
	public void setActiveInd(final char activeInd) {
		this.activeInd = activeInd;
	}
	
	@Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof File))
        {
            return false;
        }
        File other = (File) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pfchoice.core.entity.Membership[ id=" + id + " ]";
    }

}
