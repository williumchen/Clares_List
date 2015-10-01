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
        for (int i=1; i<=numPosts; i++) {
            posts.add(new Posts("Item" + ++uniquePostId, "Description" + uniquePostId));
        }
        return posts;
    }
}
