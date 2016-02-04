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
@Table(name = "hedis_measure")
public class HedisMeasure implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="qlty_msr_id", nullable = false)
    private Integer id;
    
    @Column(name="code", nullable = false)
    private String code;

    @Column(name="description")
    private String description;

    @OneToOne(fetch=FetchType.EAGER )
    @JoinColumn(name="gender_id", referencedColumnName="gender_id")
    private Gender genderId;
    
    @OneToOne( fetch=FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="qlty_msr_group_id", nullable = false, referencedColumnName="qlty_msr_group_id")
    private HedisMeasureGroup hedisMsrGrp;
    
    @Column(name="lower_age_limit")
    private Integer lowerAgeLimit;
    
    @Column(name="upper_age_limit")
    private Integer upperAgeLimit;
    
    @Temporal(TemporalType.DATE)
    @Column(name="age_effective_from")
    private Date ageEffectiveFrom;
    
    @Temporal(TemporalType.DATE)
    @Column(name="age_effective_to")
    private Date ageEffectiveTo;
    
   // @Column(name="goal")
    //private Double goal;
    
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
    
    
    public HedisMeasure()
    {
    }

    public HedisMeasure(final Integer id)
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
	 * @return the qltyMsrGrpId
	 */
	public HedisMeasureGroup getHedisMsrGrp() {
		return hedisMsrGrp;
	}

	/**
	 * @param qltyMsrGrpId the qltyMsrGrpId to set
	 */
	public void setHedisMsrGrp(final HedisMeasureGroup hedisMsrGrp) {
		this.hedisMsrGrp = hedisMsrGrp;
	}

	/**
	 * @return the genderId
	 */
	public Gender getGenderId() {
		return genderId;
	}

	/**
	 * @param genderId the genderId to set
	 */
	public void setGenderId(final Gender genderId) {
		this.genderId = genderId;
	}

	/**
	 * @return the lowerAgeLimit
	 */
	public Integer getLowerAgeLimit() {
		return lowerAgeLimit;
	}

	/**
	 * @param lowerAgeLimit the lowerAgeLimit to set
	 */
	public void setLowerAgeLimit(final Integer lowerAgeLimit) {
		this.lowerAgeLimit = lowerAgeLimit;
	}

	/**
	 * @return the upperAgeLimit
	 */
	public Integer getUpperAgeLimit() {
		return upperAgeLimit;
	}

	/**
	 * @param upperAgeLimit the upperAgeLimit to set
	 */
	public void setUpperAgeLimit(final Integer upperAgeLimit) {
		this.upperAgeLimit = upperAgeLimit;
	}

	/**
	 * @return the ageEffectiveFrom
	 */
	public Date getAgeEffectiveFrom() {
		return ageEffectiveFrom;
	}

	/**
	 * @param ageEffectiveFrom the ageEffectiveFrom to set
	 */
	public void setAgeEffectiveFrom(final Date ageEffectiveFrom) {
		this.ageEffectiveFrom = ageEffectiveFrom;
	}

	/**
	 * @return the ageEffectiveTo
	 */
	public Date getAgeEffectiveTo() {
		return ageEffectiveTo;
	}

	/**
	 * @param ageEffectiveTo the ageEffectiveTo to set
	 */
	public void setAgeEffectiveTo(final Date ageEffectiveTo) {
		this.ageEffectiveTo = ageEffectiveTo;
	}

	/**
	 * @return the goal
	 */
	/*public Double getGoal() {
		return goal;
	}
*/
	/**
	 * @param goal the goal to set
	 */
	/*public void setGoal(Double goal) {
		this.goal = goal;
	}*/

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
        if (!(object instanceof HedisMeasure))
        {
            return false;
        }
        HedisMeasure other = (HedisMeasure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pfchoice.core.entity.HedisMeasure[ id=" + id + " ]";
    }

}