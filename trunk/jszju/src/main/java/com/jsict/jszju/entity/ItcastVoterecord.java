package com.jsict.jszju.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ItcastVoterecord entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_voterecord")
public class ItcastVoterecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private ItcastUser itcastUser;
	private Date voteTime;
	private String ipAddr;
	private Set<ItcastVoteitemsvoterecords> itcastVoteitemsvoterecordses = new HashSet<ItcastVoteitemsvoterecords>(
			0);

	// Constructors

	/** default constructor */
	public ItcastVoterecord() {
	}

	/** full constructor */
	public ItcastVoterecord(ItcastUser itcastUser, Date voteTime,
			String ipAddr,
			Set<ItcastVoteitemsvoterecords> itcastVoteitemsvoterecordses) {
		this.itcastUser = itcastUser;
		this.voteTime = voteTime;
		this.ipAddr = ipAddr;
		this.itcastVoteitemsvoterecordses = itcastVoteitemsvoterecordses;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id_", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voterId_")
	public ItcastUser getItcastUser() {
		return this.itcastUser;
	}

	public void setItcastUser(ItcastUser itcastUser) {
		this.itcastUser = itcastUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "voteTime_", length = 19)
	public Date getVoteTime() {
		return this.voteTime;
	}

	public void setVoteTime(Date voteTime) {
		this.voteTime = voteTime;
	}

	@Column(name = "ipAddr_")
	public String getIpAddr() {
		return this.ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastVoterecord")
	public Set<ItcastVoteitemsvoterecords> getItcastVoteitemsvoterecordses() {
		return this.itcastVoteitemsvoterecordses;
	}

	public void setItcastVoteitemsvoterecordses(
			Set<ItcastVoteitemsvoterecords> itcastVoteitemsvoterecordses) {
		this.itcastVoteitemsvoterecordses = itcastVoteitemsvoterecordses;
	}

}