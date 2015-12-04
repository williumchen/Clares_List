package com.example.wchen.clareslist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

public class SearchResultsViewActivity extends Activity {

    PostAdapter adapter;
    RecyclerView recList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_view);

        Intent intent = getIntent();
        String text = intent.getStringExtra("key");
        String category = intent .getStringExtra("category");
        Log.d("debugging search", text);

        // Initialize the recycler view
        recList = (RecyclerView) findViewById(R.id.cardList);
        // Connect adapter

        adapter = new PostAdapter(Search.doSearch(text,category));
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
