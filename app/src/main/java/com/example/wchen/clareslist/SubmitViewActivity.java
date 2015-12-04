package com.example.wchen.clareslist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.Spinner;
=======
import android.widget.Toast;
>>>>>>> 36b3e95ba9d65e256f3bbf4829ed1c9f4caa4098

/**
 * Created by wchen on 10/5/15.
 */
public class SubmitViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_view);
        // Grab intent on click of floating action button
        Intent intent = getIntent();
        final String category = intent.getStringExtra("category");
        // Initialize parse db, and grab item / desc from view
        final ParseWrapper parse = new ParseWrapper();
        final EditText newItem = (EditText) findViewById(R.id.submit_item);
        final EditText newDesc = (EditText) findViewById(R.id.submit_description);
        // Change edittext to drop down menu later
        final Spinner newCategory = (Spinner) findViewById(R.id.submit_category);

        // this should be changed to an enumeration in the constants class
        if (category.equals("Furniture")) newCategory.setSelection(0);
        if (category.equals("Appliances")) newCategory.setSelection(1);
        if (category.equals("Books")) newCategory.setSelection(2);
        if (category.equals("Electronics")) newCategory.setSelection(3);
        if (category.equals("Clothes")) newCategory.setSelection(4);
        if (category.equals("Shoes")) newCategory.setSelection(5);
        if (category.equals("Tickets")) newCategory.setSelection(6);
        if (category.equals("Misc")) newCategory.setSelection(7);

        final Button submitBtn = (Button) findViewById(R.id.submit_button);
        // Submit button pushes posts to db

        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Convert edittext to strings
                String itemString = newItem.getText().toString();
                String descString = newDesc.getText().toString();
                String categoryString = newCategory.getSelectedItem().toString();

                // Construct new post using item and desc
                // Add more to this (maybe image?)
                Posts newPost = new Posts(itemString, descString, categoryString);
                // Push post to db
                parse.pushPost(newPost);
                Toast.makeText(getBaseContext(), "Post successfully added", Toast.LENGTH_SHORT).show();
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
