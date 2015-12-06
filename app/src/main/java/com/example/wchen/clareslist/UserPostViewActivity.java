package com.example.wchen.clareslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
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
        //ImageView postImg = (ImageView) findViewById(R.id.postImg);
        // Fetch intent and obtain item and description strings
        Intent intent = getIntent();
        final String item = intent.getStringExtra("item");
        final String description = intent.getStringExtra("description");
        final String contact = intent.getStringExtra("contact");
        //final String category = intent.getStringExtra("category");

        final String postID = intent.getStringExtra("id");
        //final byte[] image = intent.getByteArrayExtra("image");

        // Set item, description, and contact view objects to their strings
        txtItem.setText(item);
        txtDescription.setText(description);
        txtContact.setText(contact);
//        if (image != null) {
//            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
//            postImg.setImageBitmap(bmp);
//        }

        final Button editBtn = (Button) findViewById(R.id.edit_button);
        final Button deleteBtn = (Button) findViewById(R.id.delete_button);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                parse.deletePost(postID);
                Intent nextScreen = new Intent(v.getContext(), CategoryActivity.class);
                v.getContext().startActivity(nextScreen);
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(v.getContext(), SubmitViewActivity.class);
                nextScreen.putExtra("item", item);
                nextScreen.putExtra("description", description);
                nextScreen.putExtra("contact", contact);
                //nextScreen.putExtra("category", category);
                //nextScreen.putExtra("image", image);
                // 1 means that this intent is from pressing the edit button
                nextScreen.putExtra("edit", 1);
                parse.deletePost(postID);
                v.getContext().startActivity(nextScreen);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_post_view, menu);
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
