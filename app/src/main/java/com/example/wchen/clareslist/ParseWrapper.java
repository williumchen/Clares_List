package com.example.wchen.clareslist;

import android.util.Log;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 10/1/15.
 */
public class ParseWrapper {
    protected String userID;

    public ParseWrapper() {
        userID = null;
    }

    public void maybeGetCurrentUser() {
        userID = ParseUser.getCurrentUser().getObjectId();
    }

    public void maybeCreateUser(String email, String password) {
        ParseUser user = new ParseUser();

        // Add email verification regex here

        user.setUsername(email);
        user.setPassword(password);
        // email is username for convenience
        //user.setEmail(email);

        // other fields can be set just like with ParseObject
        // user.put("phone", "650-253-0000");g

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.

                    // retrieve the object ID
                    userID = ParseUser.getCurrentUser().getObjectId();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
    }

    public void maybeLogInUser(String email, String password) {
        ParseUser.logInInBackground(email, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    userID = user.getObjectId();
                    // Hooray! The user is logged in.
                } else {
                    // Sign up failed. Look at the ParseException to see what happened.
                }
            }
        });
    }

    public Boolean checkEmailVerification(String email) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ParseUser");
        final Boolean[] verified = {false};
        query.getInBackground(userID, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    // object is the user object
                    verified[0] = object.getBoolean("emailVerified");
                } else {
                    // something went wrong
                }
            }
        });
        return verified[0];
    }

    public void pushPost(Posts post) {
        ParseObject parsePost = new ParseObject("ParsePosts");

        parsePost.put("item", post.mItem);
        parsePost.put("description", post.mDescription);
        parsePost.put("category", post.mCategory);

//        For future post images
//        ParseFile picture = new ParseFile("image.png", post.picture);
//        parsePost.put("picture", picture);

//        Security settings for post objects, public read/private write
        ParseACL postsACL = new ParseACL(ParseUser.getCurrentUser());
        postsACL.setPublicReadAccess(true);
        parsePost.setACL(postsACL);

        parsePost.saveInBackground();

        // Set post's ID
        post.setmID(parsePost.getObjectId());
    }

    public void deletePost(Posts post) {
        String postID = post.getID();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("ParsePosts");
        query.getInBackground(postID, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.deleteInBackground();
                } else {
                    // something went wrong
                }
            }
        });

    }

    public List<Posts> getPostsInCategory(String category) {
        final ArrayList<Posts> postsList = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ParsePosts");
        query.whereEqualTo("category", category);
        query.orderByDescending("createdAt");
        query.setLimit(10);
        try {
            for (ParseObject parsePost : query.find()) {
                postsList.add(new Posts(parsePost.getString("item"), parsePost.getString("description"), parsePost.getString("category")));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        query.findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> tempPostsList, ParseException e) {
//                if (e == null) {
//                    for (ParseObject post : tempPostsList) {
//                        postsList.add(new Posts(post.getString("item"), post.getString("description")));
//                    }
//                    //Log.d("score", "Retrieved " + scoreList.size() + " scores");
//                } else {
//                    //Log.d("score", "Error: " + e.getMessage());
//                }
//            }
//        });
        for (Posts post : postsList) {
            Log.v(post.getItem(), post.getDescription());
        }
        return postsList;
    }

    public String getUserID() { return userID; }

}
