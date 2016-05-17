package com.pfchoice.core.entity;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Mohanasundharam
 */
@Entity
@Table(name = "emails")
public class Email extends RecordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "email_id", nullable = false)
	private Integer id;

	@Expose
	@Column(name = "email_to")
	private String emailTo;
	
	@Expose
	@Column(name = "email_from")
	private String emailFrom;
	
	@Expose
	@Column(name = "email_cc")
	private String emailCc;
	
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject", nullable = false, referencedColumnName = "email_temp_id")
	private EmailTemplate emailTemplate;
	
	@Expose
	@Column(name = "body", length = 65535)
	private String body;

	@Expose
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "emails_files_upload", joinColumns = {
			@JoinColumn(name = "email_id", referencedColumnName = "email_id") }, inverseJoinColumns = {
					@JoinColumn(name = "files_upload_id", referencedColumnName = "file_upload_id") })
	private Set<FilesUpload> filesUpload;

	
	/**
	 * 
	 */
	public Email() {
		super();
	}

	/**
	 * @param id
	 */
	public Email(final Integer id) {
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
	 * @return the emailTo
	 */
	public String getEmailTo() {
		return emailTo;
	}

	/**
	 * @param emailTo the emailTo to set
	 */
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	/**
	 * @return the emailFrom
	 */
	public String getEmailFrom() {
		return emailFrom;
	}

	/**
	 * @param emailFrom the emailFrom to set
	 */
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	/**
	 * @return the emailCc
	 */
	public String getEmailCc() {
		return emailCc;
	}

	/**
	 * @param emailCc the emailCc to set
	 */
	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}

	/**
	 * @return the emailTemplate
	 */
	public EmailTemplate getEmailTemplate() {
		return emailTemplate;
	}

	/**
	 * @param emailTemplate the emailTemplate to set
	 */
	public void setEmailTemplate(EmailTemplate emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the filesUpload
	 */
	public Set<FilesUpload> getFilesUpload() {
		return filesUpload;
	}

	/**
	 * @param filesUpload the filesUpload to set
	 */
	public void setFilesUpload(Set<FilesUpload> filesUpload) {
		this.filesUpload = filesUpload;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Email)) {
			return false;
		}
		Email other = (Email) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.pfchoice.core.entity.Emails[ id=" + id + " ]";
	}

}
