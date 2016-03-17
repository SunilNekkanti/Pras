package com.pfchoice.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "membership") 
public class Membership implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Expose
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="mbr_id", nullable = false)
    private Integer id;

    @Expose
    @Column(name="mbr_firstname")
    private String firstName;
    
    @Expose
    @Column(name="mbr_lastname")
    private String lastName;

    @Expose
    @ManyToOne(fetch=FetchType.EAGER )
    @JoinColumn(name="mbr_genderid", referencedColumnName="gender_id")
    private Gender genderId;
    
    @Expose
    @ManyToOne(fetch=FetchType.EAGER )
    @JoinColumn(name="mbr_countycode", referencedColumnName="code")
    private County countyCode;
    
    @Expose
    @ManyToOne(fetch=FetchType.EAGER )
    @JoinColumn(name="mbr_ethinic_code", referencedColumnName="code")
    private Ethinicity ethinicCode;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mbr")
    private Set<ReferenceContact> refMbrContacts = new HashSet<ReferenceContact>();
    
    @Expose 
    @OneToMany(fetch = FetchType.LAZY, mappedBy= "mbr")
    private List<MembershipProvider> mbrProviderList;

    @Expose
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mbr")
    private List<MembershipInsurance> mbrInsuranceList;
    
    
    @Expose
    @Column(name="mbr_dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    
    @Expose
    @Column(name="mbr_medicaidno")
    private String medicaidNo;
    
    @Expose
    @Column(name="mbr_medicareno")
    private String medicareNo;
    
    @Expose
    @Column(name="file_id")
    private Integer fileId;
    
    @Column(name="created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Column(name="updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    
    @Column(name="created_by")
    private String createdBy;
    
    @Column(name="updated_by")
    private String updatedBy;
    
    @Expose
    @Column(name="active_ind",insertable=false)
    private Character activeInd;
    
    @Expose
    @OneToOne
    @JoinColumn(name="mbr_status", referencedColumnName="code")
    private MembershipStatus status;

  //  @Expose
    @OneToMany( mappedBy= "mbr", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MembershipHedisMeasure> mbrHedisMeasureList;
  
    
    public Membership()
    {
    }

    public Membership(final Integer id)
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
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
	 * @return the countyCode
	 */
	public County getCountyCode() {
		return countyCode;
	}

	/**
	 * @param countyCode the countyCode to set
	 */
	public void setCountyCode(final County countyCode) {
		this.countyCode = countyCode;
	}

	/**
	 * @return the mbrProviderList
	 */
	public List<MembershipProvider> getMbrProviderList() {
		return mbrProviderList;
	}

	/**
	 * @param mbrProviderList the mbrProviderList to set
	 */
	public void setMbrProviderList(List<MembershipProvider> mbrProviderList) {
		this.mbrProviderList = mbrProviderList;
	}

	/**
	 * @return the mbrInsuranceList
	 */
	public List<MembershipInsurance> getMbrInsuranceList() {
		return mbrInsuranceList;
	}

	/**
	 * @param mbrInsuranceList the mbrInsuranceList to set
	 */
	public void setMbrInsuranceList(List<MembershipInsurance> mbrInsuranceList) {
		this.mbrInsuranceList = mbrInsuranceList;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the ethinicCode
	 */
	public Ethinicity getEthinicCode() {
		return ethinicCode;
	}

	/**
	 * @param ethinicCode the ethinicCode to set
	 */
	public void setEthinicCode(final Ethinicity ethinicCode) {
		this.ethinicCode = ethinicCode;
	}

	/**
	 * @return the status
	 */
	public MembershipStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(final MembershipStatus status) {
		this.status = status;
	}

	/**
	 * @return the medicaidNo
	 */
	public String getMedicaidNo() {
		return medicaidNo;
	}

	/**
	 * @param medicaidNo the medicaidNo to set
	 */
	public void setMedicaidNo(final String medicaidNo) {
		this.medicaidNo = medicaidNo;
	}

	/**
	 * @return the medicareNo
	 */
	public String getMedicareNo() {
		return medicareNo;
	}

	/**
	 * @param medicareNo the medicareNo to set
	 */
	public void setMedicareNo(final String medicareNo) {
		this.medicareNo = medicareNo;
	}

	/**
	 * @return the fileId
	 */
	public Integer getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(final Integer fileId) {
		this.fileId = fileId;
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
	
	/**
	 * @return the refContacts
	 */
	public Set<ReferenceContact> getRefContacts() {
		return refMbrContacts;
	}

	/**
	 * @param refContacts the refContacts to set
	 */
	public void setRefContacts(Set<ReferenceContact> refContacts) {
		this.refMbrContacts = refContacts;
	}
	
	
	/**
	 * @return the refMbrContacts
	 */
	public Set<ReferenceContact> getRefMbrContacts() {
		return refMbrContacts;
	}

	/**
	 * @param refMbrContacts the refMbrContacts to set
	 */
	public void setRefMbrContacts(Set<ReferenceContact> refMbrContacts) {
		this.refMbrContacts = refMbrContacts;
	}

	/**
	 * @return the mbrHedisMeasureList
	 */
	public List<MembershipHedisMeasure> getMbrHedisMeasureList() {
		return mbrHedisMeasureList;
	}

	/**
	 * @param mbrHedisMeasureList the mbrHedisMeasureList to set
	 */
	public void setMbrHedisMeasureList(List<MembershipHedisMeasure> mbrHedisMeasureList) {
		this.mbrHedisMeasureList = mbrHedisMeasureList;
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
        if (!(object instanceof Membership))
        {
            return false;
        }
        Membership other = (Membership) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.pfchoice.core.entity.Membership[ id=" + id + " ]";
    }

}
