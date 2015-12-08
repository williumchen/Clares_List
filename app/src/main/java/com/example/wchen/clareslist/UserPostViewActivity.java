package com.example.wchen.clareslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserPostViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_post_view);

        final ParseWrapper parse = new ParseWrapper();
        // Initialize the item and description view objects
        TextView txtItem = (TextView) findViewById(R.id.txtItem);
        TextView txtDescription = (TextView) findViewById(R.id.txtDescription);
        TextView txtContact = (TextView) findViewById(R.id.txtContact);
        // Fetch intent and obtain item, description, contact, and id strings
        Intent intent = getIntent();
        final String item = intent.getStringExtra("item");
        final String description = intent.getStringExtra("description");
        final String contact = intent.getStringExtra("contact");
        // have postID so that we can do edit and delete
        final String postID = intent.getStringExtra("id");

        // Set item, description, and contact view objects to their strings
        txtItem.setText(item);
        txtDescription.setText(description);
        txtContact.setText(contact);

        final Button editBtn = (Button) findViewById(R.id.edit_button);
        final Button deleteBtn = (Button) findViewById(R.id.delete_button);

        // uses deletePost function from ParseWrapper with input postID
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                parse.deletePost(postID);
                Intent nextScreen = new Intent(v.getContext(), UserPostsListActivity.class);
                v.getContext().startActivity(nextScreen);
                finish();
            }
        });

        // deletes the post and then allows user to edit it by prefilling texts
        // in the next activity
        editBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(v.getContext(), EditSubmitViewActivity.class);
                // does not carry over the image or the category
                nextScreen.putExtra("item", item);
                nextScreen.putExtra("description", description);
                nextScreen.putExtra("contact", contact);
                parse.deletePost(postID);
                v.getContext().startActivity(nextScreen);
                finish();
            }
        });
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
