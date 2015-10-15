package com.example.wchen.clareslist;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emilyfirst on 10/14/15.
 */
public class Search {
    protected List<String> mText;
    protected List<Posts> mPosts;


    public Search(String text)
    {
        String temp = "";
        for (int i = 0; i < text.length(); i++)
        {
            if (Character.isLetter(text.charAt(i)))
            {
                temp += Character.toLowerCase(text.charAt(i));
            }
            if (Character.isSpaceChar(text.charAt(i))) {
                mText.add(temp);
                temp = "";
            }
        }

    }

    public List<String> getTextList()
    {
        return mText;
    }

    public List<Posts> getPostsWithText()
    {
        final ArrayList<Posts> postsList = new ArrayList<>();
        String word;
        for (int i = 0; i < mText.size(); i++)
        {
            word = mText.get(i);
            ParseQuery<ParseObject> query = ParseQuery.getQuery("ParsePost");
            query.whereEqualTo("item", word);

            try {
                for (ParseObject parsePost : query.find()) {
                    postsList.add(new Posts(parsePost.getString("item"), parsePost.getString("description")));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }



    }




}
