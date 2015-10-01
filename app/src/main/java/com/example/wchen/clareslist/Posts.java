package com.example.wchen.clareslist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wchen on 9/24/15.
 */
public class Posts {
    protected String mItem;
    protected String mDescription;
    protected String mCategory;
    protected String mID;

    public Posts(String item, String description) {
        mItem = item;
        mDescription = description;
    }

    public String getItem() {
        return mItem;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getID() {
        return mID;
    }

    public String getCategory() { return mCategory; }

    public void setmID(String myID) {
        mID = myID;
    }

    private static int uniquePostId = 0;

    public static List<Posts> createPostsList(int numPosts) {
        List<Posts> posts = new ArrayList<>();
        Posts dbPost;
        ParseWrapper pw = new ParseWrapper();

        pw.maybeCreateUser("mjeong@hmc.edu", "password");

        for (int i=1; i<=numPosts; i++) {
            dbPost = new Posts("Item" + ++uniquePostId, "Description" + uniquePostId);
            dbPost.mCategory = "Bikes";
            pw.pushPost(dbPost);
        }
        return pw.getPostsInCategory("Bikes");
    }
}
