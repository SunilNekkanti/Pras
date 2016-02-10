package com.pfchoice.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "insurance_contract") 
public class InsuranceContract implements Serializable
{
	
    private static final long serialVersionUID = 1L;

    @Expose
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="Ins_contract_Id", nullable = false)
    private Integer id;

    @Expose
    @ManyToOne
    @JoinColumn(name="ins_id", referencedColumnName="Insurance_Id")
    private Insurance insuranceId;
    
    @Expose
    @Column(name="contract")
    private String contract;
    
    @Expose
    @Column(name="PMPM")
    private Double pmpm;
    
    @Expose
    @Column(name="start_date")
    private Date startDate;
    
    @Expose
    @Column(name="end_date")
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
    
    @Column(name="updated_by")
    private String updatedBy;
    
    @Expose
    @Column(name="active_ind")
    private char activeInd;
    
      
    public InsuranceContract()
    {
    }

    public InsuranceContract(final Integer id)
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
	 * @return the insuranceId
	 */
	public Insurance getInsuranceId() {
		return insuranceId;
	}

	/**
	 * @param insuranceId the insuranceId to set
	 */
	public void setInsuranceId(final Insurance insuranceId) {
		this.insuranceId = insuranceId;
	}

	/**
	 * @return the contract
	 */
	public String getContract() {
		return contract;
	}

	/**
	 * @param contract the contract to set
	 */
	public void setContract(final String contract) {
		this.contract = contract;
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
        if (!(object instanceof InsuranceContract))
        {
            return false;
        }
        InsuranceContract other = (InsuranceContract) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pfchoice.core.entity.InsuranceContract[ id=" + id + " ]";
    }

}
