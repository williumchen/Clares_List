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
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    PostAdapter adapter;
    RecyclerView recList;
    ParseWrapper pw;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get intent from CategoryActivity and determine category
        Intent categoryIntent = getIntent();
        final String category = categoryIntent.getStringExtra("category");


        // Initialize the recycler view
        recList = (RecyclerView) findViewById(R.id.cardList);
        // Connect adapter
        pw = new ParseWrapper();
        PostAdapter adapter = new PostAdapter(pw.getPostsInCategory(category));
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
                submitScreen.putExtra("category",category);
                v.getContext().startActivity(submitScreen);
                //Toast.makeText(getBaseContext(), "FAB clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        final EditText newKey = (EditText) findViewById(R.id.submit_key);
        final Button searchBtn = (Button) findViewById(R.id.search_button);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent searchScreen = new Intent(v.getContext(), SearchResultsViewActivity.class);
                // Convert edittext to strings
                searchScreen.putExtra("key", newKey.getText().toString());
                searchScreen.putExtra("category", category);
                v.getContext().startActivity(searchScreen);

                finish();
            }
        });
//        recList.setHasFixedSize(true);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        recList.setLayoutManager(llm);
//
//        PostAdapter pa = new PostAdapter(createList(30));
//        recList.setAdapter(pa);

        // Swipe to refresh widget
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });
    }
    // Reload the posts from parse and reload recyclerview
    // There is definitely a better way to do this
    //
    void refreshItems() {
        pw = new ParseWrapper();
        Intent categoryIntent = getIntent();
        String category = categoryIntent.getStringExtra("category");
        adapter = new PostAdapter(pw.getPostsInCategory(category));
        recList = (RecyclerView) findViewById(R.id.cardList);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        recList.setAdapter(adapter);
        recList.setLayoutManager(new LinearLayoutManager(this));
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
        // Get the back button to work
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}