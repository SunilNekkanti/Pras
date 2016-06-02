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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Mohanasundharam
 */
@Entity
@Table(name = "membership_problems")
public class MembershipProblem extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "mbr_pbm_id", nullable = false)
	private Integer id;

	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mbr_id", nullable = false, referencedColumnName = "mbr_id")
	private Membership mbr;
	
	@Expose
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pbm_id", nullable = false, referencedColumnName = "pbm_id")
	private Problem pbm;
	
	@Expose
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Expose
	@Column(name = "resolved_date")
	@Temporal(TemporalType.DATE)
	private Date resolvedDate;
	
	/**
	 * 
	 */
	public MembershipProblem() {
		super();
	}

	/**
	 * @param id
	 */
	public MembershipProblem(final Integer id) {
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
	 * @return the pbm
	 */
	public Problem getPbm() {
		return pbm;
	}

	/**
	 * @param pbm the pbm to set
	 */
	public void setPbm(Problem pbm) {
		this.pbm = pbm;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the resolvedDate
	 */
	public Date getResolvedDate() {
		return resolvedDate;
	}

	/**
	 * @param resolvedDate the resolvedDate to set
	 */
	public void setResolvedDate(Date resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MembershipProblem)) {
			return false;
		}
		MembershipProblem other = (MembershipProblem) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.core.entity.MembershipProblem[ id=" + id + " ]";
	}

}
