package com.example.wchen.clareslist;

import android.util.Log;

import java.util.List;

/**
 * Created by emilyfirst on 10/14/15.
 */
public class Search {
    protected String mText;
    //Do I need this?
    protected List<Posts> mPosts;


    public Search(String text)
    {
        mText = text;

    }

    public String getKeyText()
    {
        return mText;
    }

    public static List<Posts> doSearch(String text)
    {
        Search dSearch = new Search(text);
        ParseWrapper wrap = new ParseWrapper();
        List<Posts> result  = wrap.getPostsWithKey(dSearch.mText);
        if (result != null)
        {
            Log.d("debugging search", Integer.toString(result.size()));

        }
        return result;
    }

    
}
