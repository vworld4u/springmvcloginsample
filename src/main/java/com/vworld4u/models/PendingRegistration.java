package com.vworld4u.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PendingRegistration {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@OneToOne
	private User user;
	private String email;
	private String verificationCode;
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date validUpto;
	@Temporal(TemporalType.TIMESTAMP)
	private Date verifiedOn;
	@Temporal(TemporalType.TIMESTAMP)
	private Date emailSentOn;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getValidUpto() {
		return validUpto;
	}
	public void setValidUpto(Date validUpto) {
		this.validUpto = validUpto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getVerifiedOn() {
		return verifiedOn;
	}
	public void setVerifiedOn(Date verifiedOn) {
		this.verifiedOn = verifiedOn;
	}
	public Date getEmailSentOn() {
		return emailSentOn;
	}
	public void setEmailSentOn(Date emailSentOn) {
		this.emailSentOn = emailSentOn;
	}
	@Override
	public String toString() {
		return "PendingRegistration [id=" + id + ", user=" + user + ", email=" + email + ", verificationCode="
				+ verificationCode + ", creationTime=" + creationTime + ", validUpto=" + validUpto + ", verifiedOn="
				+ verifiedOn + ", emailSentOn=" + emailSentOn + "]";
	}
}
