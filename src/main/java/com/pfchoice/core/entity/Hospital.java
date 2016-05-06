package com.pfchoice.core.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 *
 * @author sarath
 */
@Entity
@Table(name = "hospital")
public class Hospital extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "hos_id", nullable = false)
	private Integer id;

	@Expose
	@Column(name = "code")
	private String code;
	
	@Expose
	@Column(name = "name")
	private String name;

	@Expose
	@Column(name = "file_id", nullable = false)
	private Integer file_id;
	

	/**
	 * 
	 */
	public Hospital() {
		super();
	}

	/**
	 * 
	 * @param code
	 */
	public Hospital(final Integer id) {
		super();
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
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the file_id
	 */
	public Integer getFile_id() {
		return file_id;
	}

	/**
	 * @param file_id the file_id to set
	 */
	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	/**
	 * @param object
	 *   	the object to compare
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Hospital)) {
			return false;
		}
		Hospital other = (Hospital) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "com.pfchoice.core.entity.Hospital[ code=" + id + " ]";
	}

}
