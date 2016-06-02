package com.pfchoice.core.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Mohanasundharam
 */
@Entity(name = "lu_ethinicity")
public class Ethinicity extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "code", nullable = false)
	private Byte id;

	@Expose
	@Column(name = "description")
	private String description;

	/**
	 * 
	 */
	public Ethinicity() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public Ethinicity(final Byte id) {
		super();
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public Byte getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(final Byte id) {
		this.id = id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Ethinicity)) {
			return false;
		}
		Ethinicity other = (Ethinicity) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.core.entity.Ethinicity[ id=" + id + " ]";
	}

}
