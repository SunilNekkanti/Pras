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


/**
 *
 * @author Mohanasundharam
 */
@Entity
@Table(name = "provider_contract") 
public class ProviderContract implements Serializable
{
	
    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="prvdr_contract_id", nullable = false)
    private Integer id;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prvdr_id", nullable = false)    
    private Provider providerId;

    @Column(name="contract_NBR")
    private String contractNBR;
    
    @Column(name="PMPM")
    private Double pmpm;
    
    @Column(name="startdate")
    private Date startDate;
    
    @Column(name="enddate")
    private Date endDate;
       
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
    
   
    
      
    public ProviderContract()
    {
    }

    public ProviderContract(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
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
	public void setProviderId(Provider providerId) {
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
	public void setContractNBR(String contractNBR) {
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
	public void setPmpm(Double pmpm) {
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
	public void setStartDate(Date startDate) {
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
	public void setEndDate(Date endDate) {
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
	public void setCreatedDate(Timestamp createdDate) {
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
	public void setUpdatedDate(Timestamp updatedDate) {
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
