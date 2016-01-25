package com.pfchoice.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sarath
 */
@Entity
@Table(name = "lu_state")
public class State implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="code", nullable = false)
    private Integer code;

    @Column(name="description")
    private String description;

    @Column(name="shot_name")
    private String shortName;
    
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
    
    @Column(name="active_ind")
    private char activeInd;
    
    @OneToOne(mappedBy="stateCode",  fetch = FetchType.EAGER)
    private ZipCode zipCode;
    
    public State()
    {
    }

    public State(Integer code)
    {
        this.code = code;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
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
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	
	/**
	 * @return the zipCode
	 */
	public ZipCode getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(ZipCode zipCode) {
		this.zipCode = zipCode;
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
	public void setCreatedDate(Date createdDate) {
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
	public void setUpdatedDate(Date updatedDate) {
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
	public void setCreatedBy(String createdBy) {
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
	public void setUpdatedBy(String updatedBy) {
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
	public void setActiveInd(char activeInd) {
		this.activeInd = activeInd;
	}

	@Override
    public int hashCode()
    {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the code fields are not set
        if (!(object instanceof State))
        {
            return false;
        }
        State other = (State) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pfchoice.core.entity.State[ code=" + code + " ]";
    }

}
