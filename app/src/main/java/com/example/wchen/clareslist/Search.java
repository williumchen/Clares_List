package com.example.wchen.clareslist;

import android.util.Pair;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
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

    public List<Pair<String,String>> getPostInfo()
    {
        List<Pair<String, String>> pairList= new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ParsePost");
        query.orderByDescending("createdAt");
        query.setLimit(50);

        try {
            for (ParseObject parsePost : query.find()) {
                pairList.add(Pair.create(parsePost.getString("description"), parsePost.getObjectId()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return pairList;

    }

    public List<Posts> getPostsWithKey()
    {
        return null;
    }



}
