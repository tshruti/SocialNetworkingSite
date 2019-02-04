package code.challenge.hsbc.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class FollowUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	FollowUserPK followUserPK;

	@EmbeddedId
	public FollowUserPK getFollowUserPK() {
		return this.followUserPK;
	}

	public void setFollowUserPK(FollowUserPK pk) {
		this.followUserPK = pk;
	}

}
