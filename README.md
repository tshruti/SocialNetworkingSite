# HSBC Code Challenge: Social networking site

Social networking site APIs to post a message, see wall, follow user and see timeline

##API details:

POSTING: 
- User is able to post the message with length ranging between 1 - 140.
- Posting message on wall creates user in inmemory DB with user ID provided and name is optional.
- Posting message on wall also creates entry in Tweets table. User can post n number of posts.

```
URL: http://localhost:8080/socialNetworkingSite/post
Method: POST
Headers: Content-type application/json
BODY:
{
	"userID" : "user1",
	"description" : "Post message to my wall"
}
````

WALL:
- User is able to see the posts they have posted in reverse chronological order
- User ID is provided in the url of API.
- User should have at least one post to see the wall. User is given with "User doesn't exist" message if there are no posts available for that user.

```
URL: http://localhost:8080/socialNetworkingSite/wall/{userID}
Method: GET

````

Following:
- User is able to follow another user, provided both users exist otherwise user is given with "User doesn't exist". If successful user is give a success message "User user1 is now a follower of user2".
- 'followUserPK' in below API is the primary key for FollowUser table. Which is candidate key consists of 'followerID' and 'followedID'. 'followedID' is followed user ID and 'followerID' is follower user ID.
- "Already following the user" message is given if user is already following another user.

```
URL: http://localhost:8080/socialNetworkingSite/follow
Method: POST
Headers: Content-type application/json
BODY:
{
	"followUserPK" :
	{
		"followedID" : "user1",
		"followerID" : "user2"
	}
}
````

Timeline:
- User is able to see the list of posts from people they follow in reverse chronological order.
- User ID is provided in the url of API.
- Created custom query to get the results for timeline request. Above all APIs use JpaRepository implementation

```
URL: http://localhost:8080/socialNetworkingSite/timeline/{userID}
Method: GET

````
Testing:

- Written Unit tests and thoroughly tested in postman, please find the attached postman collection for all the APIs written. Import in postman is at top left corner. Hope this collection helps.
- Both /wall and /timeline displays posts in reverse chronological order
- When exception thrown API replies back with proper message, please try below test case to get those exceptions

	Test case 1: hit /wall or /timeline or /follow API without posting any message on respective user.
	Test case 2: hit /follow twice.



