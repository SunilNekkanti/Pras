package com.pfchoice.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;


/**
 *
 * @author Mohanasundharam
 */
@Entity
@Table(name = "provider_contract") 
public class ProviderContract implements Serializable
{
	
    private static final long serialVersionUID = 1L;

    @Expose
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="prvdr_contract_id", nullable = false)
    private Integer id;
    
    @Expose
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prvdr_id", nullable = false)    
    private Provider providerId;

    @Expose
    @Column(name="contract_NBR")
    private String contractNBR;
    
    @Expose
    @Column(name="PMPM")
    private Double pmpm;
    
    @Expose
    @Column(name="startdate")
    private Date startDate;
    
    @Expose
    @Column(name="enddate")
    private Date endDate;
       
    @Expose
    @Column(name="created_date")
    private Timestamp createdDate;
    
    @Expose
    @Column(name="updated_date")
    private Timestamp updatedDate;
    
    @Expose
    @Column(name="created_by")
    private String createdBy;
    
    @Expose
    @Column(name="updated_by")
    private String updatedBy;
    
    @Expose
    @Column(name="active_ind",insertable=false)
    private char activeInd;
    
   
    public ProviderContract()
    {
    }

    public ProviderContract(final Integer id)
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
	 * @return the providerId
	 */
	public Provider getProviderId() {
		return providerId;
	}

	/**
	 * @param providerId the providerId to set
	 */
	public void setProviderId(final Provider providerId) {
		this.providerId = providerId;
	}
	
	
	

	/**
	 * @return the contractNBR
	 */
	public String getContractNBR() {
		return contractNBR;
	}

	/**
	 * @param contractNBR the contractNBR to set
	 */
	public void setContractNBR(final String contractNBR) {
		this.contractNBR = contractNBR;
	}

	/**
	 * @return the pmpm
	 */
	public Double getPmpm() {
		return pmpm;
	}

	/**
	 * @param pmpm the pmpm to set
	 */
	public void setPmpm(final Double pmpm) {
		this.pmpm = pmpm;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
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
        if (!(object instanceof ProviderContract))
        {
            return false;
        }
        ProviderContract other = (ProviderContract) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pfchoice.core.entity.ProviderContract[ id=" + id + " ]";
    }

}
