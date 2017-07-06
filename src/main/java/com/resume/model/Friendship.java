package com.resume.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "followerId", "followedId" }))
public class Friendship {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long friendshipID;

	@ManyToOne
	@JoinColumn(name = "followerId")
	private User follower;

	@ManyToOne
	@JoinColumn(name = "followedId")
	private User followed;

	private Date date;

	private boolean confirmed;
	
	public Long getFriendshipID() {
		return friendshipID;
	}

	public void setFriendshipID(Long friendshipID) {
		this.friendshipID = friendshipID;
	}

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}

	public User getFollowed() {
		return followed;
	}

	public void setFollowed(User followed) {
		this.followed = followed;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	

}

