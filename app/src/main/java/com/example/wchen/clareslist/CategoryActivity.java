package com.example.wchen.clareslist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;

// Class for displaying the categories

public class CategoryActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "vH9SzZSDGnse8Sub1eF4ZF8L3J30YGHxkwNYBiKd", "u6WXDTEzRs2pLXnhas3Oi8BSqhpnhZMJuCT7bgY1");
    }

    public void onClick(View v) {
        Button b = (Button) v;
        // Set up intent for post view
        Intent categorySelected = new Intent(v.getContext(), MainActivity.class);
        categorySelected.putExtra("category", b.getText().toString());
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

