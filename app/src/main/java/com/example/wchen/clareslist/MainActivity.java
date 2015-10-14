package com.example.wchen.clareslist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.Parse;

public class MainActivity extends Activity {

    PostAdapter adapter;
    RecyclerView recList;
    ParseWrapper pw;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "vH9SzZSDGnse8Sub1eF4ZF8L3J30YGHxkwNYBiKd", "u6WXDTEzRs2pLXnhas3Oi8BSqhpnhZMJuCT7bgY1");
        ParseWrapper pw = new ParseWrapper();
        // Initialize the recycler view
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        // Connect adapter
        PostAdapter adapter = new PostAdapter(pw.getPostsInCategory("Bikes"));
        recList.setAdapter(adapter);
        recList.setLayoutManager(new LinearLayoutManager(this));
        // Initialize swipe to refresh layout
        SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        // Setting up floating action button onclicklistener
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.plus);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent submitScreen = new Intent(v.getContext(), SubmitViewActivity.class);
                v.getContext().startActivity(submitScreen);
                //Toast.makeText(getBaseContext(), "FAB clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });
    }

    void refreshItems() {
        pw = new ParseWrapper();
        adapter = new PostAdapter(pw.getPostsInCategory("Bikes"));
        recList = (RecyclerView) findViewById(R.id.cardList);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        recList.setAdapter(adapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}