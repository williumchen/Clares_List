package com.example.wchen.clareslist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

// Class for displaying the categories

public class CategoryActivity extends Activity {
    ParseWrapper pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_rc);

        pw = new ParseWrapper();
        pw.maybeLogInUser("mjeong+10@hmc.edu", "password");

        RecyclerView rvCategory = (RecyclerView) findViewById(R.id.rvCategory);
        // Create adapter passing in the sample user data
        CategoryAdapter adapter = new CategoryAdapter(CategoryAdapter.getCategory());
        // Attach the adapter to the RecyclerView to populate items
        rvCategory.setAdapter(adapter);
        // Set layout manager to position the items
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        rvCategory.addItemDecoration(itemDecoration);
//        findViewById(R.id.category).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView textCategory = (TextView) v;
//                // Set up intent for post view
//                Intent categorySelected = new Intent(v.getContext(), MainActivity.class);
//                categorySelected.putExtra("category", textCategory.getText().toString());
//                v.getContext().startActivity(categorySelected);
//            }
//        })
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
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

