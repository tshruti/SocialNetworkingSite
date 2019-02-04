package code.challenge.hsbc.util;

public class DBUtils {

	public static final String QUERY_FOR_USER_TIMELINE = "select t.description, t.dateTime  FROM Tweets t WHERE t.userID IN "+
			"(select f.followUserPK.followedID FROM FollowUser f WHERE f.followUserPK.followerID = '%s') order by t.dateTime desc";
	
}
