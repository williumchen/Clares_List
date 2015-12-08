package com.example.wchen.clareslist;

import com.parse.ParseUser;

import com.parse.ParseUser;

import java.util.List;

/**
 * Created by emilyfirst on 10/14/15.
 */
public class Search {
    // mText is the phrase to be searched
    protected String mText;
    // mCategory is the category that the user is performing the search in
    protected String mCategory;

    // Search constructor takes in a text to be searched and a category
    public Search(String text, String category)
    {
        // make non case sensitive
        String lower = text.toLowerCase();
        mText = lower;
        mCategory = category;

    }

    // Returns the key phrase to be searched
    public String getKeyText()
    {
        return mText;
    }

    // Returns the category that the search is to be performed in
    public String getCategory()
    {
        return mCategory;
    }

    // Uses the ParseWrapper to get a list of Posts from doing a search
    public static List<Posts> doSearch(String text, String category)
    {
        // Create new search object
        Search dSearch = new Search(text, category);
        ParseWrapper pw = new ParseWrapper();
        ParseUser.getCurrentUser();
        // Uses ParseWrapper function getPostsWithKey to get result, which is the list of Posts
        // that matches the search.
        List<Posts> result  = pw.getPostsWithKey(dSearch.getKeyText(), dSearch.getCategory());
        return result;
    }

    
}
