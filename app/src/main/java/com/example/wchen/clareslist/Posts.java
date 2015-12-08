package com.example.wchen.clareslist;

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

    // Post constructor takes in item, description, category, image, and contact info
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

    // Returns the object id, given by parse, of the post
    public String getID() {
        return mID;
    }

    // Returns the category of the post
    public String getCategory() { return mCategory; }

    // Returns the contact info of the post
    public String getContact() { return mContact; }

    // Returns the byte array of the image of the post
    public byte[] getImage() { return mImage; }

    // Sets the post id
    public void setmID(String myID) {
        mID = myID;
    }
}
