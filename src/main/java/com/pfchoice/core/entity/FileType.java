package com.pfchoice.core.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gson.annotations.Expose;

/**
 *
 * @author sarath
 */
@Entity(name = "file_type")
public class FileType extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "code", nullable = false)
	private Integer code;

	@Expose
	@NotNull
	@Size(min = 5, max = 150, message = "The description must be between {min} and {max} characters long")
	@Column(name = "description")
	private String description;
	
	@Expose
	@Size(min = 5, max = 100, message = "The table name must be between {min} and {max} characters long")
	@Column(name = "tables_name")
	private String tablesName;
	
	@Expose
	@Size(min = 2, max = 25, message = "The sql_name_substring must be between {min} and {max} characters long")
	@Column(name = "sql_name_substring")
	private String sqlNameSubstring;
	

	/**
	 * 
	 */
	public FileType() {
		super();
	}

	/**
	 * 
	 * @param code
	 */
	public FileType(final Integer code) {
		super();
		this.code = code;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCode(final Integer code) {
		this.code = code;
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

	/**
	 * @return
	 */
	public String getTablesName() {
		return tablesName;
	}

	/**
	 * @param tablesName
	 */
	public void setTablesName(String tablesName) {
		this.tablesName = tablesName;
	}

	/**
	 * @return
	 */
	public String getSqlNameSubstring() {
		return sqlNameSubstring;
	}

	/**
	 * @param sqlNameSubstring
	 */
	public void setSqlNameSubstring(String sqlNameSubstring) {
		this.sqlNameSubstring = sqlNameSubstring;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (code != null ? code.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof FileType)) {
			return false;
		}
		FileType other = (FileType) object;
		if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.core.entity.County[ code=" + code + " ]";
	}

}
