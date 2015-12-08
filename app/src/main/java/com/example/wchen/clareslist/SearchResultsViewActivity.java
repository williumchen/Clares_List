package com.example.wchen.clareslist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class SearchResultsViewActivity extends Activity {

    PostAdapter adapter;
    RecyclerView recList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_view);

        // intent has the key phrase that the user typed and the category to search through
        Intent intent = getIntent();
        String text = intent.getStringExtra("key");
        String category = intent .getStringExtra("category");

        // Initialize the recycler view
        recList = (RecyclerView) findViewById(R.id.cardList);

        List<Posts> result = Search.doSearch(text,category);
        // If there are no posts in result, then give user appropriate message
        if (result.size() == 0)
        {
            // Code from:
            // http://developer.android.com/guide/topics/ui/notifiers/toasts.html
            Context context = getApplicationContext();
            CharSequence message = "No posts contain the key phrase";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();
        }
        // Connect adapter
        adapter = new PostAdapter(result);
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
