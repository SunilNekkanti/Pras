package com.pfchoice.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "cpt_measure")
public class CPTMeasure implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="cpt_id", nullable = false)
    private Integer id;
    
    @Expose
    @Column(name="code", nullable = false)
    private String code;

    @Expose
    @Column(name="short_description")
    private String shortDescription;

    
    @Expose
    @Column(name="description")
    private String description;

    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;
    
    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_date")
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
    
    @OneToOne(cascade=CascadeType.ALL)  
    @JoinTable(name="hedis_cpt_measure",  
    joinColumns={@JoinColumn(name="cpt_id", referencedColumnName="cpt_id")},  
    inverseJoinColumns={@JoinColumn(name="hedis_msr_rule_Id", referencedColumnName="hedis_msr_rule_Id")})  
    private HedisMeasureRule hedisMeasureRule;
    
    public CPTMeasure()
    {
    }

    public CPTMeasure(final Integer id)
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
	 * @return the shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * @param shortDescription the shortDescription to set
	 */
	public void setShortDescription(final String shortDescription) {
		this.shortDescription = shortDescription;
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
	 * @return the hedisMeasureRule
	 */
	public HedisMeasureRule getHedisMeasureRule() {
		return hedisMeasureRule;
	}

	/**
	 * @param hedisMeasureRule the hedisMeasureRule to set
	 */
	public void setHedisMeasureRule(HedisMeasureRule hedisMeasureRule) {
		this.hedisMeasureRule = hedisMeasureRule;
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
        // TODO: Warning - this method won't work in the case the code fields are not set
        if (!(object instanceof CPTMeasure))
        {
            return false;
        }
        CPTMeasure other = (CPTMeasure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pfchoice.core.entity.CPTMeasure[ id=" + id + " ]";
    }

}
