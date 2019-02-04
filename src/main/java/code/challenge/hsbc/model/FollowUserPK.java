package code.challenge.hsbc.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class FollowUserPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FollowUserPK() {

	}

	String followedID;

	String followerID;

	public String getFollowedID() {
		return followedID;
	}

	public void setFollowedID(String followedID) {
		this.followedID = followedID;
	}

	public String getFollowerID() {
		return followerID;
	}

	public void setFollowerID(String followerID) {
		this.followerID = followerID;
	}

	public int hashcode() {
		return (int) (followedID.hashCode() + followerID.hashCode());
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof FollowUserPK))
			return false;
		FollowUserPK followUserPk = (FollowUserPK) obj;
		return followUserPk.followedID.equals(this.followedID) && followUserPk.followerID.equals(this.followerID);
	}
}
