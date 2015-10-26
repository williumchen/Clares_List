package com.example.wchen.clareslist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.Parse;

// Class for displaying the categories

public class CategoryActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_category);
        RecyclerView rvCategory = (RecyclerView) findViewById(R.id.rvCategory);
        // Create adapter passing in the sample user data
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        rvCategory.addItemDecoration(itemDecoration);
        CategoryAdapter adapter = new CategoryAdapter(CategoryAdapter.getCategory());
        // Attach the adapter to the recyclerview to populate items
        rvCategory.setAdapter(adapter);
        // Set layout manager to position the items
        rvCategory.setLayoutManager(new LinearLayoutManager(this));

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "vH9SzZSDGnse8Sub1eF4ZF8L3J30YGHxkwNYBiKd", "u6WXDTEzRs2pLXnhas3Oi8BSqhpnhZMJuCT7bgY1");
    }

    public void onClick(View v) {
        TextView textCategory = (TextView) v;
        // Set up intent for post view
        Intent categorySelected = new Intent(v.getContext(), MainActivity.class);
        categorySelected.putExtra("category", textCategory.getText().toString());
        v.getContext().startActivity(categorySelected);
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

