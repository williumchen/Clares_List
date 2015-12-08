package com.example.wchen.clareslist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wchen on 9/24/15.
 */
public class Posts {
    // Each post will contain an item, description, and optionally an image
    protected String mItem;
    protected String mDescription;
    protected String mCategory;
    protected String mID;
    protected byte[] mImage;
    protected String mContact;

    // Post constructor takes in item and description (add image later)
    public Posts(String item, String description, String category, byte[] image, String contact) {
        mItem = item;
        mDescription = description;
        mCategory = category;
        mImage = image;
        mContact = contact;
    }

    // Returns the item of post
    public String getItem() {
        return mItem;
    }

    // Returns description of post
    public String getDescription() {
        return mDescription;
    }

    public String getID() {
        return mID;
    }

    public String getCategory() { return mCategory; }

    public String getContact() { return mContact; }

    public byte[] getImage() { return mImage; }

    public void setmID(String myID) {
        mID = myID;
    }

    private static int uniquePostId = 0;

    // For testing purposes only: populates an array list with posts
    public static List<Posts> createPostsList(int numPosts) {
        List<Posts> posts = new ArrayList<>();
        Posts dbPost;
        ParseWrapper pw = new ParseWrapper();
        pw.maybeCreateUser("mjeong+10@hmc.edu", "password");

        for (int i=1; i<=numPosts; i++) {
            //dbPost = new Posts("Item" + ++uniquePostId, "Description" + uniquePostId, "Bikes", "someImage.png", "My number");
            //pw.pushPost(dbPost);
        }
        return pw.getPostsInCategory("Bikes");
    }
}
