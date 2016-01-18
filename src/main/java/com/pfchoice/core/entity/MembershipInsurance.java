package com.pfchoice.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author sarath
 */
@Entity
@Table(name = "membership_insurance") 
public class MembershipInsurance implements Serializable
{


    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="mbr_ins_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ins_id", nullable = false, referencedColumnName="insurance_id")
    private Insurance insId;
    
    @OneToOne( fetch=FetchType.EAGER , cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="mbr_id", nullable = false, referencedColumnName="mbr_id")
    private Membership mbr;
    
    @Column(name="New_Medicare_Bene_Medicaid_Flag")
    private char newBenifits;

    @Temporal(TemporalType.DATE)
    @Column(name="activitydate")
    private Date activityDate;
    
    @Column(name="activityMonth")
    private Integer activityMonth;
    
    @Temporal(TemporalType.DATE)
    @Column(name="effective_strt_dt")
    private Date effStartDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name="effecctive_end_dt")
    private Date effEndDate;
    
    @Column(name="product")
    private String product;
    
    @Column(name="product_label")
    private String productLabel;
   
    @Column(name="planID")
    private String planId;
    
    @Column(name="SRC_SYS_MBR_NBR")
    private String srcSysMbrNbr;
   
    @Column(name="risk_flag")
    private char riskFlag;
   
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
    
     
    public MembershipInsurance()
    {
    }

    public MembershipInsurance(Integer id)
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
	 * @return the insId
	 */
	public Insurance getInsId() {
		return insId;
	}

	/**
	 * @param insId the insId to set
	 */
	public void setInsId(Insurance insId) {
		this.insId = insId;
	}

	/**
	 * @return the mbrId
	 */
	public Membership getMbr() {
		return mbr;
	}

	/**
	 * @param mbrId the mbrId to set
	 */
	public void setMbrId(Membership mbr) {
		this.mbr = mbr;
	}

	/**
	 * @return the newBenifits
	 */
	public char getNewBenifits() {
		return newBenifits;
	}

	/**
	 * @param newBenifits the newBenifits to set
	 */
	public void setNewBenifits(char newBenifits) {
		this.newBenifits = newBenifits;
	}

	/**
	 * @return the activityDate
	 */
	public Date getActivityDate() {
		return activityDate;
	}

	/**
	 * @param activityDate the activityDate to set
	 */
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	/**
	 * @return the activityMonth
	 */
	public Integer getActivityMonth() {
		return activityMonth;
	}

	/**
	 * @param activityMonth the activityMonth to set
	 */
	public void setActivityMonth(Integer activityMonth) {
		this.activityMonth = activityMonth;
	}

	/**
	 * @return the effStartDate
	 */
	public Date getEffStartDate() {
		return effStartDate;
	}

	/**
	 * @param effStartDate the effStartDate to set
	 */
	public void setEffStartDate(Date effStartDate) {
		this.effStartDate = effStartDate;
	}

	/**
	 * @return the effEndDate
	 */
	public Date getEffEndDate() {
		return effEndDate;
	}

	/**
	 * @param effEndDate the effEndDate to set
	 */
	public void setEffEndDate(Date effEndDate) {
		this.effEndDate = effEndDate;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * @return the productLabel
	 */
	public String getProductLabel() {
		return productLabel;
	}

	/**
	 * @param productLabel the productLabel to set
	 */
	public void setProductLabel(String productLabel) {
		this.productLabel = productLabel;
	}

	/**
	 * @return the planId
	 */
	public String getPlanId() {
		return planId;
	}

	/**
	 * @param planId the planId to set
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
	}

	/**
	 * @return the srcSysMbrNbr
	 */
	public String getSrcSysMbrNbr() {
		return srcSysMbrNbr;
	}

	/**
	 * @param srcSysMbrNbr the srcSysMbrNbr to set
	 */
	public void setSrcSysMbrNbr(String srcSysMbrNbr) {
		this.srcSysMbrNbr = srcSysMbrNbr;
	}

	/**
	 * @return the riskFlag
	 */
	public char getRiskFlag() {
		return riskFlag;
	}

	/**
	 * @param riskFlag the riskFlag to set
	 */
	public void setRiskFlag(char riskFlag) {
		this.riskFlag = riskFlag;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MembershipInsurance))
        {
            return false;
        }
        MembershipInsurance other = (MembershipInsurance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pfchoice.core.entity.MembershipInsurance[ id=" + id + " ]";
    }

}