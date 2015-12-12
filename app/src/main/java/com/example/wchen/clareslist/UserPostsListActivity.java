package com.example.wchen.clareslist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;

public class UserPostsListActivity extends Activity {

    PostAdapter adapter;
    RecyclerView recList;
    ParseWrapper pw;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_posts_list);

        pw = new ParseWrapper();
        // Initialize the recycler view
        recList = (RecyclerView) findViewById(R.id.cardList);
        // get the current user id
        String userID = ParseUser.getCurrentUser().getObjectId();
        // Connect adapter
        adapter = new PostAdapter(pw.getPostsWithOwner(userID));
        recList.setAdapter(adapter);
        recList.setLayoutManager(new LinearLayoutManager(this));
        SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        // Swipe to refresh widget
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });
    }

    // Reload the posts from parse and reload recyclerview
    void refreshItems() {
        pw = new ParseWrapper();
        recList = (RecyclerView) findViewById(R.id.cardList);
        String userID = ParseUser.getCurrentUser().getObjectId();
        adapter = new PostAdapter(pw.getPostsWithOwner(userID));
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        recList.setAdapter(adapter);
        recList.setLayoutManager(new LinearLayoutManager(this));
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_posts_list, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Get the back button to work
        // There is a bug where, after you delete an object and refresh,
        // when you hit the back button, the old state of the activity
        // comes back
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
