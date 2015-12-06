package com.example.wchen.clareslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.parse.ParseUser;

public class UserPostsListActivity extends AppCompatActivity {

    PostAdapter adapter;
    RecyclerView recList;
    ParseWrapper pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_posts_list);

        pw = new ParseWrapper();
        // Initialize the recycler view
        recList = (RecyclerView) findViewById(R.id.cardList);
        String userID = ParseUser.getCurrentUser().getObjectId();
        adapter = new PostAdapter(pw.getPostsWithOwner(userID));
        recList.setAdapter(adapter);
        recList.setLayoutManager(new LinearLayoutManager(this));
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Get the back button to work
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
