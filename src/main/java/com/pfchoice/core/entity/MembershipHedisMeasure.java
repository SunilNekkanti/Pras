package com.pfchoice.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
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

import com.google.gson.annotations.Expose;

/**
 *
 * @author sarath
 */
@Entity(name = "membership_hedis_measure")
public class MembershipHedisMeasure implements Serializable
{
	
    private static final long serialVersionUID = 1L;

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="mbr_hedis_msr_Id", nullable = false)
    private Integer id;

  //  @Expose
    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="mbr_id", nullable = false, referencedColumnName="mbr_id")
    private Membership mbr;
    
    @Expose
    @OneToOne( fetch = FetchType.EAGER)
    @JoinColumn(name="hedis_msr_rule_id", nullable = false, referencedColumnName="hedis_msr_rule_id")
    private HedisMeasureRule hedisMeasureRule;
    
    @Expose
    @Temporal(TemporalType.DATE)
    @Column(name="due_date")
    private Date dueDate;
    
    @Expose
    @Temporal(TemporalType.DATE)
    @Column(name="date_of_service")
    private Date dos;
    
    @Expose
    @Column(name="follow_up_ind")
    private Character followUpInd;
    
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
    
      
    public MembershipHedisMeasure()
    {
    }

    public MembershipHedisMeasure(final Integer id)
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
	 * @return the mbr
	 */
	public Membership getMbr() {
		return mbr;
	}

	/**
	 * @param mbr the mbr to set
	 */
	public void setMbr(final Membership mbr) {
		this.mbr = mbr;
	}

	
	/**
	 * @return the hedisMeasureRule
	 */
	public HedisMeasureRule getHedisMeasureRule() {
		return hedisMeasureRule;
	}

	/**
	 * @param hedisMeasureRule the hedisMeasureRule to set
	 */
	public void setHedisMeasureRule(final HedisMeasureRule hedisMeasureRule) {
		this.hedisMeasureRule = hedisMeasureRule;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(final Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the dos
	 */
	public Date getDos() {
		return dos;
	}

	/**
	 * @param dos the dos to set
	 */
	public void setDos(final Date dos) {
		this.dos = dos;
	}

	/**
	 * @return the followUpInd
	 */
	public Character getFollowUpInd() {
		return followUpInd;
	}

	/**
	 * @param followUpInd the followUpInd to set
	 */
	public void setFollowUpInd(final Character followUpInd) {
		this.followUpInd = followUpInd;
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
        if (!(object instanceof MembershipHedisMeasure))
        {
            return false;
        }
        MembershipHedisMeasure other = (MembershipHedisMeasure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pfchoice.core.entity.MemberhsipHedisMeasure[ id=" + id + " ]";
    }

}
