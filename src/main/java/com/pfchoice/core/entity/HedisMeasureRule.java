package com.pfchoice.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.google.gson.annotations.Expose;

/**
 *
 * @author sarath
 */
@Entity
@Table(name = "hedis_measure_rule") 
public class HedisMeasureRule implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="hedis_msr_rule_Id", nullable = false)
    private Integer id;

    @Expose
    @OneToOne
    @JoinColumn(name="hedis_id", referencedColumnName="qlty_msr_id")
    private HedisMeasure hedisMeasure;
    
    
    @Expose
    @OneToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="hedis_cpt_measure",  
    joinColumns={@JoinColumn(name="hedis_msr_rule_Id", referencedColumnName="hedis_msr_rule_Id")},  
    inverseJoinColumns={@JoinColumn(name="cpt_id", referencedColumnName="cpt_id")}) 
    private Set<CPTMeasure> cptCodes;
    
    @Expose
    @OneToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="hedis_icd_measure",  
    joinColumns={@JoinColumn(name="hedis_msr_rule_Id", referencedColumnName="hedis_msr_rule_Id")},  
    inverseJoinColumns={@JoinColumn(name="icd_id", referencedColumnName="icd_id")}) 
    private Set<ICDMeasure> icdCodes ;
    
    @Expose
    @OneToOne(fetch=FetchType.EAGER )
    @JoinColumn(name="gender_id", referencedColumnName="gender_id")
    private Gender genderId;
    
    @Expose
    @Column(name="lower_age_limit")
    private BigDecimal lowerAgeLimit;
    
    @Expose
    @Column(name="upper_age_limit")
    private BigDecimal upperAgeLimit;
    
    @Expose
    @Temporal(TemporalType.DATE)
    @Column(name="age_effective_from")
    private Date ageEffectiveFrom;
    
    @Expose
    @Temporal(TemporalType.DATE)
    @Column(name="age_effective_to")
    private Date ageEffectiveTo;
    
    @Expose
    @Column(name="effective_year")
    private Integer	effectiveYear;
    
    @Expose
    @Column(name="created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Expose
    @Column(name="updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    
    @Expose
    @Column(name="created_by")
    private String createdBy;
    
    @Expose
    @Column(name="updated_by")
    private String updatedBy;
    
    @Expose
    @Column(name="active_ind",insertable=false)
    private Character activeInd;
    
    public HedisMeasureRule()
    {
    }

    public HedisMeasureRule(final Integer id)
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
	 * @return the hedisMeasure
	 */
	public HedisMeasure getHedisMeasure() {
		return hedisMeasure;
	}

	/**
	 * @param hedisMeasure the hedisMeasure to set
	 */
	public void setHedisMeasure(final HedisMeasure hedisMeasure) {
		this.hedisMeasure = hedisMeasure;
	}

	/**
	 * @return the effectiveYear
	 */
	public Integer getEffectiveYear() {
		return effectiveYear;
	}

	/**
	 * @param effectiveYear the effectiveYear to set
	 */
	public void setEffectiveYear(final Integer effectiveYear) {
		this.effectiveYear = effectiveYear;
	}

	/**
	 * @return the cptCodes
	 */
	public Set<CPTMeasure> getCptCodes() {
		return cptCodes;
	}

	/**
	 * @param cptCodes the cptCodes to set
	 */
	public void setCptCodes(Set<CPTMeasure> cptCodes) {
		this.cptCodes = cptCodes;
	}

	/**
	 * @return the icdCodes
	 */
	public Set<ICDMeasure> getIcdCodes() {
		return icdCodes;
	}

	/**
	 * @param icdCodes the icdCodes to set
	 */
	public void setIcdCodes(Set<ICDMeasure> icdCodes) {
		this.icdCodes = icdCodes;
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
	public void setGenderId(Gender genderId) {
		this.genderId = genderId;
	}

	/**
	 * @return the lowerAgeLimit
	 */
	public BigDecimal  getLowerAgeLimit() {
		return lowerAgeLimit;
	}

	/**
	 * @param lowerAgeLimit the lowerAgeLimit to set
	 */
	public void setLowerAgeLimit(BigDecimal  lowerAgeLimit) {
		this.lowerAgeLimit = lowerAgeLimit;
	}

	/**
	 * @return the upperAgeLimit
	 */
	public BigDecimal  getUpperAgeLimit() {
		return upperAgeLimit;
	}

	/**
	 * @param upperAgeLimit the upperAgeLimit to set
	 */
	public void setUpperAgeLimit(BigDecimal  upperAgeLimit) {
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
	public void setAgeEffectiveFrom(Date ageEffectiveFrom) {
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
	public void setAgeEffectiveTo(Date ageEffectiveTo) {
		this.ageEffectiveTo = ageEffectiveTo;
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
        if (!(object instanceof HedisMeasureRule))
        {
            return false;
        }
        HedisMeasureRule other = (HedisMeasureRule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pfchoice.core.entity.HedisMeasureRule[ id=" + id + " ]";
    }

}
