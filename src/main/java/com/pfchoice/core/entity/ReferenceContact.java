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

/**
 *
 * @author sarath
 */
@Entity(name = "reference_contact")
public class ReferenceContact implements Serializable
{
	
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="ref_cnt_Id", nullable = false)
    private Integer id;

    @OneToOne( fetch=FetchType.LAZY )
    @JoinColumn(name="mbr_id",  referencedColumnName="mbr_id")
    private Membership mbr;
    
    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="insurance_id",  referencedColumnName="insurance_id")
    private Insurance ins;
    
    @OneToOne(  fetch = FetchType.LAZY)
    @JoinColumn(name="prvdr_id",  referencedColumnName="prvdr_id")
    private Provider prvdr;
    
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
    
    @OneToOne( fetch = FetchType.LAZY,mappedBy="refContact")
    private Contact cnt;
    
      
    public ReferenceContact()
    {
    }

    public ReferenceContact(final Integer id)
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
	 * @return the ins
	 */
	public Insurance getIns() {
		return ins;
	}

	/**
	 * @param ins the ins to set
	 */
	public void setIns(final Insurance ins) {
		this.ins = ins;
	}

	/**
	 * @return the prvdr
	 */
	public Provider getPrvdr() {
		return prvdr;
	}

	/**
	 * @param prvdr the prvdr to set
	 */
	public void setPrvdr(final Provider prvdr) {
		this.prvdr = prvdr;
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
	public char getActiveInd() {
		return activeInd;
	}

	/**
	 * @param activeInd the activeInd to set
	 */
	public void setActiveInd(final char activeInd) {
		this.activeInd = activeInd;
	}
	
	/**
	 * @return the cnt
	 */
	public Contact getCnt() {
		return cnt;
	}

	/**
	 * @param cnt the cnt to set
	 */
	public void setCnt(final Contact cnt) {
		this.cnt = cnt;
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
        if (!(object instanceof ReferenceContact))
        {
            return false;
        }
        ReferenceContact other = (ReferenceContact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pfchoice.core.entity.Contact[ id=" + id + " ]";
    }

}
