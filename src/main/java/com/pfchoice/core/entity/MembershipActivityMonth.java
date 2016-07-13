package com.pfchoice.core.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Mohanasundharam
 */
@Entity
@Table(name = "membership_activity_month")
public class MembershipActivityMonth extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "mbr_act_mnth_id", nullable = false)
	private Integer id;

	@Expose
	@Column(name = "activity_month")
	private Integer activityMonth;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "mbr_id", nullable = false, referencedColumnName = "mbr_id")
	private Membership mbr;

	@Expose
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prvdr_id", nullable = false, referencedColumnName = "prvdr_id")
	private Provider prvdr;

	@Expose
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ins_id", nullable = false, referencedColumnName = "insurance_id")
	private Insurance ins;

	@Expose
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "file_id", nullable = false, referencedColumnName = "file_id")
	private File file;

	/**
	 * 
	 */
	public MembershipActivityMonth() {
		super();
	}

	/**
	 * @param id
	 */
	public MembershipActivityMonth(final Integer id) {
		super();
		this.id = id;
	}

	/**
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return the activityMonth
	 */
	public Integer getActivityMonth() {
		return activityMonth;
	}

	/**
	 * @param activityMonth
	 *            the activityMonth to set
	 */
	public void setActivityMonth(Integer activityMonth) {
		this.activityMonth = activityMonth;
	}

	/**
	 * @return the mbr
	 */
	public Membership getMbr() {
		return mbr;
	}

	/**
	 * @param mbr
	 *            the mbr to set
	 */
	public void setMbr(Membership mbr) {
		this.mbr = mbr;
	}

	/**
	 * @return the prvdr
	 */
	public Provider getPrvdr() {
		return prvdr;
	}

	/**
	 * @param prvdr
	 *            the prvdr to set
	 */
	public void setPrvdr(Provider prvdr) {
		this.prvdr = prvdr;
	}

	/**
	 * @return the insId
	 */
	public Insurance getIns() {
		return ins;
	}

	/**
	 * @param insId
	 *            the insId to set
	 */
	public void setIns(Insurance ins) {
		this.ins = ins;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MembershipActivityMonth)) {
			return false;
		}
		MembershipActivityMonth other = (MembershipActivityMonth) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.core.entity.MembershipActivityMonth[ id=" + id + " ]";
	}

}
