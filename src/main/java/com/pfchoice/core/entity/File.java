package com.pfchoice.core.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.gson.annotations.Expose;

/**
 *
 * @author sarath
 */
@Entity(name = "file")
public class File extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "file_id", nullable = false)
	private Integer id;

	@Expose
	@Column(name = "file_name")
	private String fileName;

	@Expose
	@Column(name = "file_type_code")
	private Integer fileTypeCode;

	/**
	 * 
	 */
	public File() {
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public File(final Integer id) {
		super();
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileTypeCode
	 */
	public Integer getFileTypeCode() {
		return fileTypeCode;
	}

	/**
	 * @param fileTypeCode
	 *            the fileTypeCode to set
	 */
	public void setFileTypeCode(final Integer fileTypeCode) {
		this.fileTypeCode = fileTypeCode;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof File)) {
			return false;
		}
		File other = (File) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.core.entity.Membership[ id=" + id + " ]";
	}

}
