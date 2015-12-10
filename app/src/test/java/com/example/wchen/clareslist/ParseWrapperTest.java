package com.example.wchen.clareslist;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class ParseWrapperTest {
    ParseWrapper parse = new ParseWrapper();
    
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testgetPostsInCategory() throws Exception {
        int size = parse.getPostsInCategory("Furniture").size();
        assertEquals(true, size > 0);
    }

    @Test
    public void testPushPost() throws Exception {
        int size = parse.getPostsInCategory("Furniture").size();
        Posts test = new Posts("test", "test", "Furniture");
        parse.pushPost(test);
        int testSize = parse.getPostsInCategory("Furniture").size();
        assertEquals(testSize, size+1);
    }

    @Test
    public void testDeletePost() throws Exception {
        int size = parse.getPostsInCategory("Furniture").size();
        Posts test = new Posts("test", "test", "Furniture");
        parse.pushPost(test);
        parse.deletePost(test);
        int testSize = parse.getPostsInCategory("Furniture").size();
        assertEquals(size, testSize);
    }

    @Test
    public void testgetPostsWithKey() throws Exception {
        List<Posts> temp = new ArrayList<>();
        temp = parse.getPostsWithKey("hi", "Furniture");
        assertEquals(true, temp.size() > 0);
    }
}