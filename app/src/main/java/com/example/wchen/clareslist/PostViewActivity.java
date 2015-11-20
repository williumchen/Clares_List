package com.example.wchen.clareslist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

// Class for more detailed view of the post

public class PostViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);
        // Initialize the item and description view objects
        TextView txtItem = (TextView) findViewById(R.id.txtItem);
        TextView txtDescription = (TextView) findViewById(R.id.txtDescription);
        TextView txtContact = (TextView) findViewById(R.id.txtContact);
        // Fetch intent and obtain item and description strings
        Intent intent = getIntent();
        String item = intent.getStringExtra("item");
        String description = intent.getStringExtra("description");
        String contact = intent.getStringExtra("contact");

        // Set item and description view objects to their strings
        txtItem.setText(item);
        txtDescription.setText(description);
        txtContact.setText(contact);
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
