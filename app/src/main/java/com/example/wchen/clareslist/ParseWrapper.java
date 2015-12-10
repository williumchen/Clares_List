package com.example.wchen.clareslist;

import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 10/1/15.
 */
public class ParseWrapper {
    protected static String userID;
    protected static ParseUser currentUser;

    public ParseWrapper() {
    }

    public void setCurrentUser() {
        currentUser = ParseUser.getCurrentUser();
        userID = currentUser.getObjectId();
    }

    /*
    *
    * Email must be verified BEFORE making this call.
    *
    *
     */
    public void maybeCreateUser(String email, String password) {
        ParseUser user = new ParseUser();

        // Add email verification regex here

        user.setUsername(email);
        user.setPassword(password);
        // email is username for convenience
        //user.setEmail(email);

        // other fields can be set just like with ParseObject
        // user.put("phone", "650-253-0000");

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.

                    // retrieve the object ID
                    currentUser = ParseUser.getCurrentUser();
                    userID = currentUser.getObjectId();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.d("debugging user", "log-in failed");
                }
            }
        });
    }

    public void maybeLogInUser(String email, String password) {
        try {
            ParseUser.logIn(email, password);
            currentUser = ParseUser.getCurrentUser();
            userID = currentUser.getObjectId();
        }
        catch (ParseException e) {
            Log.d("debugging user", "log-in failed");
            Log.d("debugging user", e.getMessage());
        }
    }

    public ParseObject getCategory(String category) {
        ParseQuery<ParseObject> categoryQuery = ParseQuery.getQuery("Category");
        Log.d("debugging GGGGGGGG", category);
        categoryQuery.whereEqualTo("category", category);
        try {
            return categoryQuery.getFirst();
        }
        catch (ParseException e) {
            // some error
            Log.d("debugging", "FAILED GET CATEGORY");
            Log.d("debugging", e.getMessage());
            return new ParseObject("Category");
        }

    }

    public void subscribeUser(String category, boolean add) {
        ParseObject myCategory = getCategory(category);
        ParseUser mCurrentUser = ParseUser.getCurrentUser();
        ParseRelation<ParseObject> relation = mCurrentUser.getRelation("categories");
        //final boolean[] subscribe = {checkSubscription(category)};

        ParseQuery<ParseObject> subscriptionsQuery = relation.getQuery();

        subscriptionsQuery.whereEqualTo("category", category);
        List<ParseObject> subscriptions;
        boolean subscribed;
        try {
            subscriptions = subscriptionsQuery.find();
        }
        catch (ParseException e) {
            //
            Log.d("debugging", "didn't work");
            return;
        }
        subscribed = subscriptions.size() != 0;
        if(add && !subscribed) {
            relation.add(myCategory);
            ParsePush.subscribeInBackground("Giants");
        }
        else if (!add && subscribed) {
            Log.d("debugging", "attempted to remove");
            relation.remove(myCategory);
            ParsePush.unsubscribeInBackground("Giants");
        }
        Log.d("debugging", mCurrentUser.getObjectId());
        try {
            mCurrentUser.save();
            return;
        }
        catch (ParseException e) {
            Log.d("debugging", "save failed, attempt background");
            Log.d("debugging", e.getMessage());
            mCurrentUser.saveInBackground();
        }
    }

    public Boolean checkSubscription(String category) {
        ParseRelation<ParseObject> relation = ParseUser.getCurrentUser().getRelation("categories");
        //final boolean[] subscribe = {checkSubscription(category)};

        ParseQuery<ParseObject> subscriptionsQuery = relation.getQuery();

        subscriptionsQuery.whereEqualTo("category", category);
        List<ParseObject> subscriptions = new ArrayList<>();
        try {
            subscriptions = subscriptionsQuery.find();
        }
        catch (ParseException e) {
            Log.d("debugging", "some error on checking subscription");
            Log.d("debugging", e.getMessage());
            // some error
        }

        return subscriptions.size() != 0;

//        ParseUser currentUser = ParseUser.getCurrentUser();
//        ParseRelation relation = currentUser.getRelation("categories");
//        ParseObject myCategory = new ParseObject("Category");
//        myCategory.put("category", category);
//        relation.add(myCategory);
//        try {
//            relation.getQuery().find();
//        }
//        catch (ParseException e) {
//             // do something with this exception
//        }
//        return true;


//        final boolean[] subscribe = {false};
//        relation.getQuery().findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> results, ParseException e) {
//                if (e == null) {
//                    Log.d("debugging", "test");
//                    subscribe[0] = true;
//                } else {
//                    // no subscriptions
//                }
//            }
//        });
        //return subscribe[0];
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
        // ParseObject to hold fields of post
        final ParseObject parsePost = new ParseObject("ParsePosts");
        // put each field of post into the parse object
        parsePost.put("item", post.getItem());
        parsePost.put("description", post.getDescription());
        parsePost.put("category", post.getCategory());
        parsePost.put("image", post.getImage());
        parsePost.put("contact", post.getContact());
        parsePost.put("userID", ParseUser.getCurrentUser().getObjectId());

        //Security settings for post objects, public read/private write
        ParseACL postsACL = new ParseACL(ParseUser.getCurrentUser());
        postsACL.setPublicReadAccess(true);
        parsePost.setACL(postsACL);
        // try to save the ParseObject
        try {
            parsePost.save();
        }
        catch (ParseException e) {
            Log.d("debugging", "save failed, attempt background");
            Log.d("debugging", e.getMessage());
            parsePost.saveInBackground();
        }
        // Create new notification
        JSONObject data = null;
        try {
            data = new JSONObject(
                    "{\"alert\": \"A new item is available!\", " +
                    "\"title\": \"Check out Clare's List!\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Push the notification
        ParsePush push = new ParsePush();
        push.setChannel(post.getCategory());
        push.setData(data);
        push.sendInBackground();

        // Set post's ID
        post.setmID(parsePost.getObjectId());
    }

    public void deletePost(String postID) {
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
                Posts p = new Posts(parsePost.getString("item"), parsePost.getString("description"),
                        parsePost.getString("category"), parsePost.getBytes("image"), parsePost.getString("contact"));
                p.setmID(parsePost.getObjectId());
                postsList.add(p);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Posts post : postsList) {
            Log.v(post.getItem(), post.getDescription());
        }
        return postsList;
    }

    public List<Posts> getPostsWithOwner(String userID) {
        final ArrayList<Posts> postsList = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ParsePosts");
        query.whereEqualTo("userID", userID);
        query.orderByDescending("createdAt");
        try {
            for (ParseObject parsePost : query.find()) {
                Posts p = new Posts(parsePost.getString("item"), parsePost.getString("description"),
                        parsePost.getString("category"), parsePost.getBytes("image"), parsePost.getString("contact"));
                p.setmID(parsePost.getObjectId());
                postsList.add(p);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Posts post : postsList) {
            Log.v(post.getItem(), post.getDescription());
        }
        Log.d("user", String.valueOf(postsList.size()));
        return postsList;
    }


    public List<Posts> getPostsWithKey(String key, String category) {
        final List<Posts> completePosts = getPostsInCategory(category);
        final List<Posts> postsList = new ArrayList<>();

        for (Posts post : completePosts) {
            String lowDescription = post.getDescription().toLowerCase();
            String lowItem = post.getItem().toLowerCase();
            if (lowDescription.contains(key) || lowItem.contains(key)) {
                postsList.add(new Posts(post.getItem(), post.getDescription(), post.getCategory(), post.getImage(), post.getContact()));
            }
        }

        return postsList;
    }


    public String getUserID() { return userID; }

}
