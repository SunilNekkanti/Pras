package com.pfchoice.core.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

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
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author sarath
 */
@Entity
@Table(name = "membership") 
public class Membership implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="mbr_id", nullable = false)
    private Integer id;

    @Column(name="mbr_firstname")
    private String firstName;
    
    @Column(name="mbr_lastname")
    private String lastName;

    @ManyToOne(fetch=FetchType.EAGER )
    @JoinColumn(name="mbr_genderid", referencedColumnName="gender_id")
    private Gender genderId;
    
    @ManyToOne(fetch=FetchType.EAGER )
    @JoinColumn(name="mbr_countycode", referencedColumnName="code")
    private County countyCode;
    
    @OneToOne( mappedBy= "mbr", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name="mbr_id", referencedColumnName="mbr_id")
    private MembershipInsurance mbrInsurance;
    
    @Column(name="mbr_dob")
    private Date dob;
    
    @Column(name="mbr_medicaidno")
    private String medicaidNo;
    
    @Column(name="mbr_medicareno")
    private String medicareNo;
    
    @Column(name="file_id")
    private Integer fileId;
    
    @Column(name="created_date")
    private Timestamp createdDate;
    
    @Column(name="updated_date")
    private Timestamp updatedDate;
    
    @Column(name="created_by")
    private String createdBy;
    
    @Column(name="updated_by")
    private String updatedBy;
    
    @Column(name="active_ind")
    private char activeInd;
    
    @OneToOne(cascade=CascadeType.DETACH )
    @JoinColumn(name="mbr_status", referencedColumnName="code")
    private MembershipStatus status;

    
    @Column(name="Mbr_ethinic_code", columnDefinition = "TINYINT(1)")
    private Byte ethinicCode;
    
    public Membership()
    {
    }

    public Membership(Integer id)
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
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
	public void setLastName(String lastName) {
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
	public void setGenderId(Gender genderId) {
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
	public void setCountyCode(County countyCode) {
		this.countyCode = countyCode;
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
	public Byte getEthinicCode() {
		return ethinicCode;
	}

	/**
	 * @param ethinicCode the ethinicCode to set
	 */
	public void setEthinicCode(Byte ethinicCode) {
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
	public void setStatus(MembershipStatus status) {
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
	public void setMedicaidNo(String medicaidNo) {
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
	public void setMedicareNo(String medicareNo) {
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
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
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
	public void setCreatedDate(Timestamp createdDate) {
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
	public void setUpdatedDate(Timestamp updatedDate) {
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

	
	/**
	 * @return the mbrInsurance
	 */
	public MembershipInsurance getMbrInsurance() {
		return mbrInsurance;
	}

	/**
	 * @param mbrInsurance the mbrInsurance to set
	 */
	public void setMbrInsurance(MembershipInsurance mbrInsurance) {
		this.mbrInsurance = mbrInsurance;
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
