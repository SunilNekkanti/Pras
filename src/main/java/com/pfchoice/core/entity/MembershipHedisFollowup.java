package com.pfchoice.core.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Mohanasundharam
 */
@Entity(name = "membership_hedis_followup")
public class MembershipHedisFollowup implements Serializable
{
	
    private static final long serialVersionUID = 1L;

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="mbr_hedis_followup_id", nullable = false)
    private Integer id;

//  @Expose
    @OneToOne( fetch=FetchType.LAZY )
    @JoinColumn(name="mbr_id", nullable = false, referencedColumnName="mbr_id")
    private Membership mbr;
    
    @Expose
    @Column(name="followup_details")
    private String followupDetails;
    
    @Expose
    @Column(name="date_of_contact")
    @Temporal(TemporalType.DATE)
    private Date dateOfContact;
    
    @Transient
    private List<Map<Integer,String>> mbrHedisMeasureIds;
    
    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_date")
    private Date updatedDate;
    
    @Expose
    @Column(name="created_by")
    private String createdBy;
    
    @Column(name="updated_by")
    private String updatedBy;
    
    @Expose
    @Column(name="active_ind",insertable=false)
    private Character activeInd;
    
      
    public MembershipHedisFollowup()
    {
    }

    public MembershipHedisFollowup(final Integer id)
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
	public void setMbr(Membership mbr) {
		this.mbr = mbr;
	}

	/**
	 * @return the followupDetails
	 */
	public String getFollowupDetails() {
		return followupDetails;
	}

	/**
	 * @param followupDetails the followupDetails to set
	 */
	public void setFollowupDetails(String followupDetails) {
		this.followupDetails = followupDetails;
	}

	/**
	 * @return the dateOfContact
	 */
	public Date getDateOfContact() {
		return dateOfContact;
	}

	/**
	 * @param dateOfContact the dateOfContact to set
	 */
	public void setDateOfContact(Date dateOfContact) {
		this.dateOfContact = dateOfContact;
	}

	/**
	 * @return the ruleIds
	 */
	public List<Map<Integer, String>> getMbrHedisMeasureIds() {
		return mbrHedisMeasureIds;
	}

	/**
	 * @param ruleIds the ruleIds to set
	 */
	public void setMbrHedisMeasureIds(List<Map<Integer, String>> mbrHedisMeasureIds) {
		this.mbrHedisMeasureIds = mbrHedisMeasureIds;
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
        if (!(object instanceof MembershipHedisFollowup))
        {
            return false;
        }
        MembershipHedisFollowup other = (MembershipHedisFollowup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pfchoice.core.entity.MemberhsipHedisFollowup[ id=" + id + " ]";
    }

}
