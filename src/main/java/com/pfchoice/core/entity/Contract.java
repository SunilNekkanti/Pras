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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Mohanasundharam
 */
@Entity(name = "contract")
public class Contract implements Serializable
{
	
    private static final long serialVersionUID = 1L;

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="contract_Id", nullable = false)
    private Integer id;

    @Expose
    @Column(name="contract_NBR")
    private String contractNBR;
    
    @Expose
    @Column(name="PMPM")
    private Double PMPM;

    @Expose
    @Column(name="start_date")
    private Date startDate;
    
    @Expose
    @Column(name="end_date")
    private Date endDate;
    
    @Expose
    @OneToOne(  fetch = FetchType.EAGER)
    @JoinColumn(name="ref_contract_id", referencedColumnName="ref_contract_id")
    private ReferenceContract referenceContract;
    
    @Expose
    @OneToOne(  fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="file_upload_id", referencedColumnName="file_upload_id")
    private FilesUpload filesUpload;
    
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
    
    @Transient
    private Integer insPrvdrId;
    
      
    public Contract()
    {
    }

    public Contract(final Integer id)
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
	 * @return the pMPM
	 */
	public Double getPMPM() {
		return PMPM;
	}

	/**
	 * @param pMPM the pMPM to set
	 */
	public void setPMPM(final Double pMPM) {
		PMPM = pMPM;
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
	 * @return the referenceContract
	 */
	public ReferenceContract getReferenceContract() {
		return referenceContract;
	}

	/**
	 * @param referenceContract the referenceContract to set
	 */
	public void setReferenceContract(final ReferenceContract referenceContract) {
		this.referenceContract = referenceContract;
	}

	/**
	 * @return the filesUpload
	 */
	public FilesUpload getFilesUpload() {
		return filesUpload;
	}

	/**
	 * @param filesUpload the filesUpload to set
	 */
	public void setFilesUpload(FilesUpload filesUpload) {
		this.filesUpload = filesUpload;
	}

	/**
	 * @return the insPrvdrId
	 */
	public Integer getInsPrvdrId() {
		return insPrvdrId;
	}

	/**
	 * @param insPrvdrId the insPrvdrId to set
	 */
	public void setInsPrvdrId(Integer insPrvdrId) {
		this.insPrvdrId = insPrvdrId;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contract))
        {
            return false;
        }
        Contract other = (Contract) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pfchoice.core.entity.Contract[ id=" + id + " ]";
    }

}
