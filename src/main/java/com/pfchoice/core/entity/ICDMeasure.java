package com.pfchoice.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

/**
 *
 * @author sarath
 */
@Entity
@Table(name = "icd_measure")
public class ICDMeasure implements Serializable
{

    private static final long serialVersionUID = 1L;

    
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="icd_id", nullable = false)
    private Integer id;
    
    @Expose
    @Column(name="code", nullable = false)
    private String code;

    @Expose
    @Column(name="description")
    private String description;
    
    @Expose
    @Transient
    private String codeAndDescription;
    
    @Expose
    @Column(name="hcc")
    private String hcc;
    
    @Expose
    @Column(name="rxhcc")
    private String rxhcc;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_date")
    private Date updatedDate;
    
    @Column(name="created_by")
    private String createdBy;
    
    @Column(name="updated_by")
    private String updatedBy;
   
    @Expose
    @Column(name="active_ind",insertable=false)
    private Character activeInd;
    
    public ICDMeasure()
    {
    }

    public ICDMeasure(final Integer id)
    {
        this.id = id;
    }

    /**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	public String getCode()
    {
        return code;
    }

    public void setCode(final String code)
    {
        this.code = code;
    }
    
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the codeAndDescription
	 */
	public String getCodeAndDescription() {
		String codedescription = this.code+" ("+this.description +")";
		return codedescription;
	}

	/**
	 * @param codeAndDescription the codeAndDescription to set
	 */
	public void setCodeAndDescription(String codeAndDescription) {
		this.codeAndDescription = codeAndDescription;
	}

	/**
	 * @return the hcc
	 */
	public String getHcc() {
		return hcc;
	}

	/**
	 * @param hcc the hcc to set
	 */
	public void setHcc(String hcc) {
		this.hcc = hcc;
	}

	/**
	 * @return the rxhcc
	 */
	public String getRxhcc() {
		return rxhcc;
	}

	/**
	 * @param rxhcc the rxhcc to set
	 */
	public void setRxhcc(String rxhcc) {
		this.rxhcc = rxhcc;
	}
	
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(final Date updatedDate) {
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
	public Character getActiveInd() {
		return activeInd;
	}

	/**
	 * @param activeInd the activeInd to set
	 */
	public void setActiveInd(final Character activeInd) {
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
        // TODO: Warning - this method won't work in the case the code fields are not set
        if (!(object instanceof ICDMeasure))
        {
            return false;
        }
        ICDMeasure other = (ICDMeasure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pfchoice.core.entity.ICDMeasure[ id=" + id + " ]";
    }

}
