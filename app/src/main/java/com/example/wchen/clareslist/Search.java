package com.example.wchen.clareslist;

import android.util.Log;

import java.util.List;

/**
 * Created by emilyfirst on 10/14/15.
 */
public class Search {
    protected String mText;
    protected String mCategory;
    //Do I need this?
    protected List<Posts> mPosts;


    public Search(String text, String category)
    {
        String lower = text.toLowerCase();
        mText = lower;
        mCategory = category;

    }

    public String getKeyText()
    {
        return mText;
    }

    public static List<Posts> doSearch(String text, String category)
    {
        Search dSearch = new Search(text, category);
        ParseWrapper wrap = new ParseWrapper();
        wrap.maybeGetCurrentUser();
        List<Posts> result  = wrap.getPostsWithKey(dSearch.mText, dSearch.mCategory);
        if (result != null)
        {
            Log.d("debugging search", Integer.toString(result.size()));

        }
        return result;
    }

    
}
